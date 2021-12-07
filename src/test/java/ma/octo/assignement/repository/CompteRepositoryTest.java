package ma.octo.assignement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.octo.assignement.domain.Compte;

@SpringBootTest
class CompteRepositoryTest {
    
	
	@Autowired
	private CompteRepository compteRepository;

	
	@Test
	void testFindCompteByNrCompte() {
		
	    Compte compte=	compteRepository.findByNrCompte("010000A000001000");
	
		   assertThat(compte.getNrCompte()).isEqualTo("010000A000001000");
		
		
		
	}

	@Test
	void testGetCompteById() {
		
		Compte compte=compteRepository.getById(5L);
		
		 assertThat(compte.getId()).isEqualTo(5L);

		
	}

	@Test
	void testFindCompteByRib() {
	    Compte compte=	compteRepository.findByRib("RIB1");
		
		   assertThat(compte.getRib()).isEqualTo("RIB1");
		
	}

	@Test
	void testFindAll() {
		List<Compte> listeComptes=compteRepository.findAll();
		
		assertThat(listeComptes.size()).isGreaterThanOrEqualTo(1);
		 
	}


	@Test
	void testSaveCompte() {

		
 		Compte compte = new Compte();
		compte.setNrCompte("1000000004444");
		compte.setRib("RIB3");
		compte.setSolde(BigDecimal.valueOf(290000L));
				
		Compte compte2 = compteRepository.save(compte);
		assertThat(compte2.getRib()).isEqualTo("RIB3");
		
	}

	@Test
	void testDeleteCompteById() {
		
	}

}
