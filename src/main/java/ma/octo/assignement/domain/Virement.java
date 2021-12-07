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
@DiscriminatorValue(value = "VIR")
public class Virement extends Operation{

  @ManyToOne
  private Compte compteEmetteur;



}
