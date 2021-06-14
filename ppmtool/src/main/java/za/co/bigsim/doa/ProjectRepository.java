package za.co.bigsim.doa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.bigsim.domain.Project;
import za.co.bigsim.domain.ProjectTask;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	 Project findByProjectIdentifier(String projectId);
	 
}
