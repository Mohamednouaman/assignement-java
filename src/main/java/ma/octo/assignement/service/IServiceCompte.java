package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.domain.Compte;

public interface IServiceCompte {
	
	 List<Compte>  findAllComptes();
	 
	 Compte saveCompte(Compte compte);
	

}
