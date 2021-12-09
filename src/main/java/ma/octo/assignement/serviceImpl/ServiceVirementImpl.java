package ma.octo.assignement.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.IServiceVirement;


@Transactional
@Service
public class ServiceVirementImpl implements IServiceVirement{
	
    public static final int MONTANT_MAXIMAL = 10000;
    
    @Autowired
	private CompteRepository compteRepository;
    @Autowired
    private VirementRepository virementRepository;
    
    public ServiceVirementImpl(VirementRepository virementRepository1,CompteRepository cpCompteRepository) {
		
    	   virementRepository=virementRepository1;
    	   compteRepository=cpCompteRepository;
	}
    
    
	@Override
	public List<Virement> findAllVirements() {
        List<Virement> all = virementRepository.findAll();
        
        System.out.println("appel au lister virements");
        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
	}

	@Override
	public Virement operationVirement(VirementDto virementDto)
			throws CompteNonExistantException, TransactionException, SoldeDisponibleInsuffisantException {

		Compte compteEmetteur     = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
	
		Compte compteBeneficiaire = compteRepository.findByNrCompte(virementDto.getNrCompteBeneficiaire());

		if (compteEmetteur == null || compteBeneficiaire == null) {

			System.out.println("Compte Non existant");

			throw new CompteNonExistantException("Compte Non existant");
		}

		if (virementDto.getMontantVirement()==null || virementDto.getMontantVirement().doubleValue()==0) {
			
			System.out.println("Montant vide");
			
			throw new TransactionException("Montant vide");
			
		}else if (virementDto.getMontantVirement().intValue() < 10) {
			
			System.out.println("Montant minimal de virement non atteint");
			
			throw new TransactionException("Montant minimal de virement non atteint");

		} else if (virementDto.getMontantVirement().doubleValue() > MONTANT_MAXIMAL) {

			System.out.println("Montant maximal de virement dépassé");
			
			throw new TransactionException("Montant maximal de virement dépassé");
		}

		if (virementDto.getMotif().trim().length() == 0) {
			
			System.out.println("Motif vide");
			
			throw new TransactionException("Motif vide");
		}

		if (compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()).intValue() < 0) {
			System.out.println("Solde insuffisant pour l'utilisateur");

			throw new SoldeDisponibleInsuffisantException("Solde insuffisant pour l'utilisateur");
		}
		compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()));
		
		compteRepository.save(compteEmetteur);

		compteBeneficiaire.setSolde(new BigDecimal(
				compteBeneficiaire.getSolde().add( virementDto.getMontantVirement()).doubleValue()));
		compteRepository.save(compteBeneficiaire);

		Virement virement = new Virement();
		virement.setDateExecution(virementDto.getDate());
		virement.setCompteBeneficiaire(compteBeneficiaire);
		virement.setCompteEmetteur(compteEmetteur);
		virement.setMotifOperation(virementDto.getMotif());
		virement.setMontantOperation(virementDto.getMontantVirement());

		virementRepository.save(virement);
         
		return virement;
		
	}






}
