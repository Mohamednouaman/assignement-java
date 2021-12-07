package ma.octo.assignement.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
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
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.VersementMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.IServiceVersement;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceVersementImplTest {
	
	  private IServiceVersement serviceVersement;
	  @Mock
	  private VersementRepository versementRepository;
	  @Mock
	  private CompteRepository compteRepository;

	@BeforeEach
	void setUp() throws Exception {
		
		    serviceVersement=new ServiceVersementImpl(compteRepository, versementRepository);
	}

	@Test
	void testOperationVersement() throws CompteNonExistantException, TransactionException {
		
		
		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));

		
		Versement v = new Versement();
		v.setMontantOperation(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte1);
		v.setNom_prenom_emetteur("Mohamed NOUAMAN");
    	v.setMotifOperation("Motif versement");
		v.setDateExecution(new Date());
		
		when(compteRepository.findByRib("RIB1")).thenReturn(compte1);
		
		when(compteRepository.save(compte1)).thenReturn(compte1);
	
		when(versementRepository.save(v)).thenReturn(v);

        VersementDto versementDto=		VersementMapper.map(v);
	

        Versement v2=serviceVersement.operationVersement(versementDto);
        
        assertThat(v2.getMotifOperation()).isEqualTo("Motif versement");
		
	}

}
