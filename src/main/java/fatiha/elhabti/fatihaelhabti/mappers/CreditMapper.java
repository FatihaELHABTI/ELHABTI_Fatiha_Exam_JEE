package fatiha.elhabti.fatihaelhabti.mappers;

import fatiha.elhabti.fatihaelhabti.dtos.ClientDTO;
import fatiha.elhabti.fatihaelhabti.dtos.CreditDTO;
import fatiha.elhabti.fatihaelhabti.dtos.RemboursementDTO;
import fatiha.elhabti.fatihaelhabti.entities.*;
import fatiha.elhabti.fatihaelhabti.enums.TypeBien;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    public ClientDTO toClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public Client toClient(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return client;
    }

    public CreditDTO toCreditDTO(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcceptation(credit.getDateAcceptation());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient().getId());

        if (credit instanceof CreditPersonnel) {
            dto.setMotif(((CreditPersonnel) credit).getMotif());
        } else if (credit instanceof CreditImmobilier) {
            dto.setTypeBien(((CreditImmobilier) credit).getTypeBien().toString());
        } else if (credit instanceof CreditProfessionnel) {
            dto.setMotif(((CreditProfessionnel) credit).getMotif());
            dto.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
        }
        return dto;
    }

    public RemboursementDTO toRemboursementDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit().getId());
        return dto;
    }
}