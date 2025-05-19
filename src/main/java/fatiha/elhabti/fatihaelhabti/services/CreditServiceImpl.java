package fatiha.elhabti.fatihaelhabti.services;

import fatiha.elhabti.fatihaelhabti.dtos.ClientDTO;
import fatiha.elhabti.fatihaelhabti.dtos.CreditDTO;
import fatiha.elhabti.fatihaelhabti.dtos.RemboursementDTO;
import fatiha.elhabti.fatihaelhabti.entities.*;
import fatiha.elhabti.fatihaelhabti.enums.TypeBien;
import fatiha.elhabti.fatihaelhabti.mappers.CreditMapper;
import fatiha.elhabti.fatihaelhabti.repositories.ClientRepository;
import fatiha.elhabti.fatihaelhabti.repositories.CreditRepository;
import fatiha.elhabti.fatihaelhabti.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(ClientRepository clientRepository,
                             CreditRepository creditRepository,
                             RemboursementRepository remboursementRepository,
                             CreditMapper creditMapper) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.remboursementRepository = remboursementRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = creditMapper.toClient(clientDTO);
        client = clientRepository.save(client);
        return creditMapper.toClientDTO(client);
    }

    @Override
    public CreditDTO createCredit(CreditDTO creditDTO) {
        Credit credit;
        if (creditDTO.getTypeBien() != null) {
            credit = new CreditImmobilier();
            ((CreditImmobilier) credit).setTypeBien(TypeBien.valueOf(creditDTO.getTypeBien()));
        } else if (creditDTO.getRaisonSociale() != null) {
            credit = new CreditProfessionnel();
            ((CreditProfessionnel) credit).setMotif(creditDTO.getMotif());
            ((CreditProfessionnel) credit).setRaisonSociale(creditDTO.getRaisonSociale());
        } else {
            credit = new CreditPersonnel();
            ((CreditPersonnel) credit).setMotif(creditDTO.getMotif());
        }

        credit.setDateDemande(creditDTO.getDateDemande());
        credit.setStatut(creditDTO.getStatut());
        credit.setDateAcceptation(creditDTO.getDateAcceptation());
        credit.setMontant(creditDTO.getMontant());
        credit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        credit.setTauxInteret(creditDTO.getTauxInteret());
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        credit.setClient(client);

        credit = creditRepository.save(credit);
        return creditMapper.toCreditDTO(credit);
    }

    @Override
    public RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = new Remboursement();
        remboursement.setDate(remboursementDTO.getDate());
        remboursement.setMontant(remboursementDTO.getMontant());
        remboursement.setType(remboursementDTO.getType());
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        remboursement.setCredit(credit);

        remboursement = remboursementRepository.save(remboursement);
        return creditMapper.toRemboursementDTO(remboursement);
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        return creditRepository.findAll().stream()
                .filter(credit -> credit.getClient().getId().equals(clientId))
                .map(creditMapper::toCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findAll().stream()
                .filter(remboursement -> remboursement.getCredit().getId().equals(creditId))
                .map(creditMapper::toRemboursementDTO)
                .collect(Collectors.toList());
    }
}