package fatiha.elhabti.fatihaelhabti.dtos;

import fatiha.elhabti.fatihaelhabti.enums.StatutCredit;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private Long clientId;
    // Specific fields for subclasses
    private String motif; // For CreditPersonnel and CreditProfessionnel
    private String typeBien; // For CreditImmobilier
    private String raisonSociale; // For CreditProfessionnel
}