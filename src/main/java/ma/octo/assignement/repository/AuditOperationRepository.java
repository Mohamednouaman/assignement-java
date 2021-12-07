package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.domain.AuditOperation;

public interface AuditOperationRepository extends JpaRepository<AuditOperation, Long>{

}
