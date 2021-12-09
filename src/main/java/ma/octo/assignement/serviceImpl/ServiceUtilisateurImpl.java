package ma.octo.assignement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.IServiceUtilisateur;
@Service
@Transactional
public class ServiceUtilisateurImpl implements IServiceUtilisateur{
	
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		 List<Utilisateur> all = utilisateurRepository.findAll();

	        if (CollectionUtils.isEmpty(all)) {
	            return null;
	        } else {
	            return all;
	        }
		
	
	}

	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		Utilisateur savedUtilisateur=utilisateurRepository.save(utilisateur);
		return savedUtilisateur;
	}
	
	

}
