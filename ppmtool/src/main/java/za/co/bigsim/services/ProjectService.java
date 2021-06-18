package za.co.bigsim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.bigsim.doa.BacklogRepository;
import za.co.bigsim.doa.ProjectRepository;
import za.co.bigsim.doa.UserRepository;
import za.co.bigsim.domain.Backlog;
import za.co.bigsim.domain.Project;
import za.co.bigsim.domain.User;
import za.co.bigsim.exceptions.ProjectIdException;
import za.co.bigsim.exceptions.ProjectNotFoundException;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Project saveOrUpdateProject(Project project, String uname) {
        try{
        	
        	User user = userRepository.findByUsername(uname);        	
        	project.setUser(user);
        	project.setProjectLeader(user.getUsername());
        	project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
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
	
	public Project findProjectByIdentifier(String projectId, String username){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }

        if(!project.getProjectLeader().equals(username)) {
        	throw new ProjectNotFoundException("Project not found in your account");
        }
        
        return project;
    }
	

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }
    
    public void deleteProjectByIdentifier(String projectid, String username) { 	
    	projectRepository.delete(findProjectByIdentifier(projectid, username));
    }
}
