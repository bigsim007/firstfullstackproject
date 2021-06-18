package za.co.bigsim.doa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.bigsim.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	 Project findByProjectIdentifier(String projectId);
	 
	 Iterable<Project> findAllByProjectLeader(String username);
	 
}
