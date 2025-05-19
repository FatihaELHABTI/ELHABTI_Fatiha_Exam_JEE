package fatiha.elhabti.fatihaelhabti.services;

import fatiha.elhabti.fatihaelhabti.dtos.ClientDTO;
import fatiha.elhabti.fatihaelhabti.dtos.CreditDTO;
import fatiha.elhabti.fatihaelhabti.dtos.RemboursementDTO;

import java.util.List;

public interface CreditService {
    ClientDTO createClient(ClientDTO clientDTO);
    CreditDTO createCredit(CreditDTO creditDTO);
    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);
    List<CreditDTO> getCreditsByClient(Long clientId);
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
}