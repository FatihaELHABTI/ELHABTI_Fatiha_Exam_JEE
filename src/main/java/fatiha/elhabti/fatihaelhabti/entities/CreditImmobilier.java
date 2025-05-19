package fatiha.elhabti.fatihaelhabti.entities;

import fatiha.elhabti.fatihaelhabti.enums.TypeBien;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;


}