package ma.octo.assignement.serviceImpl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.AuditOperation;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditOperationRepository;
import ma.octo.assignement.service.IAutiService;

@Service
@Transactional
public class AutiService implements IAutiService{
	
    Logger LOGGER = LoggerFactory.getLogger(IAutiService.class);

//  @Autowired
//  private AuditVirementRepository auditVirementRepository;
  
  @Autowired
  private AuditOperationRepository  auditOperationRepository;

  public void auditOperation(String message,EventType eventType) {

      LOGGER.info("Audit de l'événement {}", eventType);

      AuditOperation audit = new AuditOperation();
      audit.setEventType(eventType);
      audit.setMessage(message);
      auditOperationRepository.save(audit);
  }

//
//  public void auditVersement(String message) {
//
//      LOGGER.info("Audit de l'événement {}", EventType.VERSEMENT);
//
//      AuditOperation audit = new AuditOperation();
//      audit.setEventType(EventType.VERSEMENT);
//      audit.setMessage(message);
//      auditOperationRepository.save(audit);
//  }

}
