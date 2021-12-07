package ma.octo.assignement.service;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;

public interface IServiceVirement {
	
	
  Virement	operationVirement(VirementDto virementDto) throws CompteNonExistantException, TransactionException, SoldeDisponibleInsuffisantException;

}
