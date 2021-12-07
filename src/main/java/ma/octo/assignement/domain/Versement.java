package ma.octo.assignement.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter 
@Getter
@DiscriminatorValue(value = "VER")
public class Versement extends Operation{


  @Column(name = "NOM_EMETTEUR",length = 20)
  private String nom_prenom_emetteur;


}
