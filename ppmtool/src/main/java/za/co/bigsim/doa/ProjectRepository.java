package za.co.bigsim.doa;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import za.co.bigsim.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	 Project findByProjectIdentifier(String projectId);
}
