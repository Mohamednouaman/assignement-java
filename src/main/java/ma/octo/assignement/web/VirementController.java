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
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.IAutiService;
import ma.octo.assignement.service.IServiceCompte;
import ma.octo.assignement.service.IServiceUtilisateur;
import ma.octo.assignement.service.IServiceVirement;

@RestController
@RequestMapping("/virements")
class VirementController {

    Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    private IAutiService autiService;
    @Autowired
    private IServiceVirement  serviceVirement;
    @Autowired
    private IServiceCompte serviceCompte;    
    @Autowired
    private IServiceUtilisateur serviceUtilisateur;
  

    @GetMapping("/lister_virements")
    List<Virement> loadAll() {
    	
        List<Virement> listeVirements =serviceVirement.findAllVirements();
        return listeVirements;
    }

    @GetMapping("/lister_comptes")
    List<Compte> loadAllCompte() {
    	
    	List<Compte> listeComptes=serviceCompte.findAllComptes();
    	
    	return listeComptes;
    
    }

    @GetMapping("/lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
    	
    	List<Utilisateur>  listeUtilisateurs=serviceUtilisateur.findAllUtilisateurs();
    	
    	return listeUtilisateurs;
       
    }

    @PostMapping("/executerVirements")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

    	     serviceVirement.operationVirement(virementDto);     
    	
             autiService.auditOperation("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                        .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
                        .doubleValue(),EventType.VIREMENT);
   
    }

}
