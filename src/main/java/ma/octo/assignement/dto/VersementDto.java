package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VersementDto {
	
	
	  private String nom_prenom_emetteur;
	
	  private String ribCompteBeneficiaire;
	  
	  private String motif;
	  
	  private BigDecimal montantVersement;
	  
	  private Date date;
	

}
