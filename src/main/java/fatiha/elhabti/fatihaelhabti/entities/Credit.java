package fatiha.elhabti.fatihaelhabti.entities;

import fatiha.elhabti.fatihaelhabti.enums.StatutCredit;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutCredit statut;

    private LocalDate dateAcceptation;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;


}