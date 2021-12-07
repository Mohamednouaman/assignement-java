package ma.octo.assignement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.octo.assignement.domain.util.EventType;
@Entity
@Setter
@Getter
public class AuditOperation {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;

	  @Column
	  private String message;

	  @Enumerated(EnumType.STRING)
	  @Column(length = 20)
	  private EventType eventType;

}
