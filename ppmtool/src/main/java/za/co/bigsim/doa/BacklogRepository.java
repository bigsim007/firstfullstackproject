package za.co.bigsim.doa;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import za.co.bigsim.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{

	Backlog findByProjectIdentifier(String Identifier);
}
