package fatiha.elhabti.fatihaelhabti.entities;

import fatiha.elhabti.fatihaelhabti.enums.TypeRemboursement;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;


}