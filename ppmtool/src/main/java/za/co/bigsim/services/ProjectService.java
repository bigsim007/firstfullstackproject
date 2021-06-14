package za.co.bigsim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.bigsim.doa.BacklogRepository;
import za.co.bigsim.doa.ProjectRepository;
import za.co.bigsim.domain.Backlog;
import za.co.bigsim.domain.Project;
import za.co.bigsim.exceptions.ProjectIdException;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project) {
        try{
        	String projectIdentifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(projectIdentifier);
            
            if(project.getId() == null) {
            	Backlog bl = new Backlog();
            	project.setBacklog(bl);
            	bl.setProject(project);
            	bl.setProjectIdentifier(projectIdentifier);            	
            	
            }else {
            	project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
            
            
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
	}
	
	public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");

        }


        return project;
    }
	

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    
    public void deleteProjectByIdentifier(String projectid) {
    	
    	Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());
    	
    	if(project == null) {
    		throw new ProjectIdException("Cannot delete Project with ID '"+projectid+"'. This project does not exist");
    	}
    	
    	projectRepository.delete(project);
    }
}
