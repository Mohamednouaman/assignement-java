package ma.octo.assignement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.octo.assignement.domain.Utilisateur;
@SpringBootTest
@Transactional
class UtilisateurRepositoryTest {
    
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Test
	void testFindAll() {
		
		List<Utilisateur>  listeUtilisateurs=utilisateurRepository.findAll();
		assertThat(listeUtilisateurs.size()).isGreaterThanOrEqualTo(1);
		
	}

	@Test
	void testGetUtilisateurById() {
		
		Utilisateur utilisateur=utilisateurRepository.getById(1L);
		            assertThat(utilisateur.getId()).isEqualTo(1L);
		
		
	}

	@Test
	void testSaveUtilisateur() {
		Utilisateur utilisateur=new Utilisateur();
		            utilisateur.setFirstname("Firstname");
		            utilisateur.setLastname("lastName");
		            utilisateur.setGender("Male");
		            utilisateur.setUsername("username");
	   Utilisateur  utilisateur2=utilisateurRepository.save(utilisateur); 
	   
	                assertThat(utilisateur2.getUsername()).isEqualTo("username");
		            
		
	}

}
