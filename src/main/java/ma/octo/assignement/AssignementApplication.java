package ma.octo.assignement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.hibernate.graph.CannotBecomeEntityGraphException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.OperationRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.IAutiService;
import ma.octo.assignement.service.IServiceCompte;
import ma.octo.assignement.service.IServiceUtilisateur;
import ma.octo.assignement.service.IServiceVersement;
import ma.octo.assignement.service.IServiceVirement;

@SpringBootApplication
public class AssignementApplication implements CommandLineRunner {
	@Autowired
	private IServiceCompte serviceCompte;
	@Autowired
	private IServiceUtilisateur serviceUtilisateur;
	@Autowired
	private IServiceVirement serviceVirement;
	@Autowired
	private IServiceVersement serviceVersement;
	@Autowired
	private IAutiService autiService;


	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");

		serviceUtilisateur.saveUtilisateur(utilisateur1);

		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");

		serviceUtilisateur.saveUtilisateur(utilisateur2);

		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(333333330L));
		compte1.setUtilisateur(utilisateur1);

		serviceCompte.saveCompte(compte1);

		Compte compte2 = new Compte();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		serviceCompte.saveCompte(compte2);

//		Virement v = new Virement();
//		v.setMontantOperation(BigDecimal.TEN);
//		v.setCompteBeneficiaire(compte2);
//		v.setCompteEmetteur(compte1);
//		v.setDateExecution(new Date());
//		v.setMotifOperation("Assignment 2021");

     
       //Effectuer un virement 

			VirementDto virementDto = new VirementDto();
			virementDto.setNrCompteBeneficiaire("010000B025001000");
			virementDto.setNrCompteEmetteur("010000A000001000");
			virementDto.setMontantVirement(new BigDecimal(3655.4));
			virementDto.setMotif("Motif virement");
			virementDto.setDate(new Date());

			serviceVirement.operationVirement(virementDto);
			
	        autiService.auditOperation("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
	                   .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement().doubleValue()
	                   ,EventType.VIREMENT);
		
         //Effectuer un versement 
        
		VersementDto versementDto = new VersementDto();
		versementDto.setDate(new Date());
		versementDto.setMontantVersement(new BigDecimal(100));
		versementDto.setMotif("Motif versement");
		versementDto.setNom_prenom_emetteur("Mohamed NOUAMAN");
		versementDto.setRibCompteBeneficiaire("RIB1");

		
		serviceVersement.operationVersement(versementDto);
		
        autiService.auditOperation("Versement depuis " + versementDto.getNom_prenom_emetteur() + " vers " + versementDto
                   .getRibCompteBeneficiaire() + " d'un montant de " + versementDto.getMontantVersement().doubleValue()
                   ,EventType.VERSEMENT);
        
        

	}
}
