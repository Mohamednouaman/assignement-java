package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.domain.Operation;

public interface OperationRepository<T extends Operation> extends JpaRepository<T, Long>{
	
	       
	
	

}
