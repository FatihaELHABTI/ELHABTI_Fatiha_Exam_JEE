package fatiha.elhabti.fatihaelhabti.dtos;

import fatiha.elhabti.fatihaelhabti.enums.TypeRemboursement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypeRemboursement type;
    private Long creditId;
}