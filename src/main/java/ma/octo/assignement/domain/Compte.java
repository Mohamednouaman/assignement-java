package ma.octo.assignement.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
//@Setter
//@Getter
public class Compte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 16, unique = true)
  private String nrCompte;
  @Column(unique = true)
  private String rib;

  @Column(precision = 16, scale = 2)
  private BigDecimal solde;


  @JoinColumn(name = "utilisateur_id")
  @ManyToOne 
  private Utilisateur utilisateur;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getNrCompte() {
	return nrCompte;
}


public void setNrCompte(String nrCompte) {
	this.nrCompte = nrCompte;
}


public String getRib() {
	return rib;
}


public void setRib(String rib) {
	this.rib = rib;
}


public BigDecimal getSolde() {
	return solde;
}


public void setSolde(BigDecimal solde) {
	this.solde = solde;
}


public Utilisateur getUtilisateur() {
	return utilisateur;
}


public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}
  
 
}
