package ma.octo.assignement.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.VirementMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.IServiceVirement;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceVirementImplTest {
	
	  private IServiceVirement serviceVirement;
	  @Mock
	  private VirementRepository virementRepository;
	  @Mock
	  private CompteRepository compteRepository;
	
	@BeforeEach
	void setUp() {
		   serviceVirement = new ServiceVirementImpl(virementRepository,compteRepository);
	    }

	@Test
	void testOperationVirement() throws CompteNonExistantException, TransactionException, SoldeDisponibleInsuffisantException {
		
		
		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));

		Compte compte2 = new Compte();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		
		Virement v = new Virement();
		v.setMontantOperation(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte1);
		v.setCompteEmetteur(compte2);
    	v.setMotifOperation("Motif virement");
		v.setDateExecution(new Date());
		
		when(compteRepository.findByNrCompte("010000B025001000")).thenReturn(compte2);
		when(compteRepository.findByNrCompte("010000A000001000")).thenReturn(compte1);
		when(compteRepository.save(compte1)).thenReturn(compte1);
		when(compteRepository.save(compte2)).thenReturn(compte2);
		when(virementRepository.save(v)).thenReturn(v);

        VirementDto virementDto=		VirementMapper.map(v);
	

        Virement v2=serviceVirement.operationVirement(virementDto);
        
        assertThat(v2.getMotifOperation()).isEqualTo("Motif virement");
		
		
	}

}
