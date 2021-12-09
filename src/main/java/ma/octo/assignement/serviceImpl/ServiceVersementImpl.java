package ma.octo.assignement.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.OperationRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.IServiceVersement;

@Transactional
@Service
public class ServiceVersementImpl implements IServiceVersement {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private VersementRepository versementRepository;
	
	public ServiceVersementImpl(CompteRepository compteRepository,VersementRepository versementRepository) {
		
			
		     this.compteRepository=compteRepository;
		     this.versementRepository=versementRepository;
	}
	@Override
	public List<Versement> findAllVersement() {
		
        List<Versement> all = versementRepository.findAll();
        
        System.out.println("appel au lister virements");
        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
	}
    
	@Override
	public  Versement operationVersement(VersementDto versementDto) throws CompteNonExistantException, TransactionException {

		
		Compte compteBeneficiaire = compteRepository.findByRib(versementDto.getRibCompteBeneficiaire());

		if (compteBeneficiaire == null) {
			System.out.println("Compte non existant");
			throw new CompteNonExistantException("Compte non existant");

		}
		if (versementDto.getMontantVersement()==null  || versementDto.getMontantVersement().doubleValue() ==0) {

			System.out.println("Montant vide");

			throw new TransactionException("Montant vide");

		} else if (versementDto.getMontantVersement().intValue() < 10) {

			System.out.println("Montant minimal de versement non atteint");

			throw new TransactionException("Montant minimal de versement non atteint");

		}
		if (versementDto.getMotif().trim().length() == 0) {

			System.out.println("Motif vide");

			throw new TransactionException("Motif vide");
		}
	
		
			compteBeneficiaire.setSolde(
					new BigDecimal(compteBeneficiaire.getSolde().add(versementDto.getMontantVersement()).doubleValue()));

			compteRepository.save(compteBeneficiaire);
	


		Versement versement = new Versement();
		versement.setCompteBeneficiaire(compteBeneficiaire);
		versement.setDateExecution(new Date());
		versement.setMontantOperation(versementDto.getMontantVersement());
		versement.setMotifOperation(versementDto.getMotif());
		versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());

		versementRepository.save(versement);    
		
		return versement;
		
	 }

	

 }
