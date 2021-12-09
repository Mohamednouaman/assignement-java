package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.domain.Utilisateur;

public interface IServiceUtilisateur {
	
	 List<Utilisateur> findAllUtilisateurs();
	
	  Utilisateur saveUtilisateur(Utilisateur utilisateur);
	

}
