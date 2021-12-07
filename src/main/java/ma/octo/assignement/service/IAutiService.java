package ma.octo.assignement.service;

import ma.octo.assignement.domain.util.EventType;

public interface IAutiService {
      
	void auditOperation(String message,EventType eventType);
	
	

}
