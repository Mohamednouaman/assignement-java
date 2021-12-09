package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

public class VersementMapper {
	
	
	
    private static VersementDto versementDto;

    public static VersementDto map(Versement versement) {
    	
        versementDto = new VersementDto();
        versementDto.setNom_prenom_emetteur(versement.getNom_prenom_emetteur());
       
        versementDto.setDate(versement.getDateExecution());
        
        versementDto.setMotif(versement.getMotifOperation());
        
        versementDto.setMontantVersement(versement.getMontantOperation());
        
        versementDto.setRibCompteBeneficiaire(versement.getCompteBeneficiaire().getRib());

        return versementDto;
        

    }

}
