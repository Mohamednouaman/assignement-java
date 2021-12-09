package ma.octo.assignement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.IServiceCompte;
@Service
@Transactional
public class ServiceCompteImpl implements IServiceCompte{
	
	
	@Autowired
	private CompteRepository compteRepository;

	@Override
	public List<Compte> findAllComptes() {
		   
	    List<Compte> all = compteRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
	}

	@Override
	public Compte saveCompte(Compte compte) {
		Compte  savedCompte=compteRepository.save(compte);
		return savedCompte;
	}

}
