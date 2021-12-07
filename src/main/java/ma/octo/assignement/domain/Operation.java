package ma.octo.assignement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPERATION",length = 3)
@Setter
@Getter
public class Operation {
	

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;

	  @Column(precision = 16, scale = 2, nullable = false)
	  private BigDecimal montantOperation;

	  @Column
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateExecution;

	  @ManyToOne
	  private Compte compteBeneficiaire;

	  @Column(length = 200)
	  private String motifOperation;






	
	

}
