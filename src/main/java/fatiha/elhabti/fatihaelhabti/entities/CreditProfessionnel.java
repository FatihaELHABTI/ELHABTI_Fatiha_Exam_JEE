package fatiha.elhabti.fatihaelhabti.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}