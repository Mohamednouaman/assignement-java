package ma.octo.assignement.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.IAutiService;
import ma.octo.assignement.service.IServiceCompte;
import ma.octo.assignement.service.IServiceUtilisateur;
import ma.octo.assignement.service.IServiceVersement;

@RestController
@RequestMapping("/versements")
public class VersementController {
	
    Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    private IServiceVersement   serviceVersement;    
    @Autowired
    private IAutiService autiService; 
    @Autowired
    private IServiceCompte serviceCompte;    
    @Autowired
    private IServiceUtilisateur serviceUtilisateur;
  

    @GetMapping("/lister_versements")
    List<Versement> loadAll() {
        List<Versement> listeVersement = serviceVersement.findAllVersement();
        
      return listeVersement;
        
    }

    @GetMapping("lister_comptes")
    List<Compte> loadAllCompte() {
        List<Compte> listeComptes = serviceCompte.findAllComptes();

        return listeComptes;
    }

    @GetMapping("lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> listeUtilisateurs = serviceUtilisateur.findAllUtilisateurs();

           return listeUtilisateurs;
    }

    @PostMapping("/executerVersements")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody VersementDto versementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
    	
    	     serviceVersement.operationVersement(versementDto);     
    	
             autiService.auditOperation("Versement depuis " + versementDto.getNom_prenom_emetteur() + " vers " + versementDto
                        .getRibCompteBeneficiaire() + " d'un montant de " + versementDto.getMontantVersement()
                        .doubleValue(),EventType.VERSEMENT);
             
    }
	
	
	
}
