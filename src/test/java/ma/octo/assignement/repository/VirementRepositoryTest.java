package ma.octo.assignement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.octo.assignement.domain.Virement;


@SpringBootTest
@Transactional
public class VirementRepositoryTest {

  @Autowired
  private VirementRepository virementRepository;
  

@Test
  public void shouldFindVirementById() {
	  
	  Virement virement=virementRepository.getById(5L);
	   
	  assertThat(virement.getId()).isEqualTo(5L);
	  
	  
  }

  @Test
  public void findAll_success() {
	  
	  List<Virement>  virements=virementRepository.findAll();
	  assertThat(virements.size()).isGreaterThanOrEqualTo(1);

  }

  @Test
  public void save_virement_success() {
            
	        
	        
			Virement v = new Virement();
			v.setMontantOperation(BigDecimal.TEN);
			v.setCompteBeneficiaire(null);
			v.setCompteEmetteur(null);
			v.setDateExecution(new Date());
			v.setMotifOperation("Assignment 2021");
		    Virement savedVirement=	virementRepository.save(v);
		    assertThat(savedVirement.getCompteEmetteur()).isNull();
             
 
  }

  @Test
  public void delete_virement_success() {
	  
  }
}