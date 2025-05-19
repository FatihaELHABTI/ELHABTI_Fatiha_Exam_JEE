package fatiha.elhabti.fatihaelhabti.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CreditPersonnel extends Credit {
    private String motif;
}