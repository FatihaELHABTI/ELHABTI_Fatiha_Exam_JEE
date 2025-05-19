package fatiha.elhabti.fatihaelhabti;

import fatiha.elhabti.fatihaelhabti.entities.*;
import fatiha.elhabti.fatihaelhabti.enums.StatutCredit;
import fatiha.elhabti.fatihaelhabti.enums.TypeBien;
import fatiha.elhabti.fatihaelhabti.enums.TypeRemboursement;
import fatiha.elhabti.fatihaelhabti.repositories.ClientRepository;
import fatiha.elhabti.fatihaelhabti.repositories.CreditRepository;
import fatiha.elhabti.fatihaelhabti.repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class ExamSpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamSpringAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {
            // Create clients
            Stream.of("Ahmed", "Fatima", "Mohammed").forEach(name -> {
                Client client = new Client();
                client.setNom(name);
                client.setEmail(name.toLowerCase() + "@gmail.com");
                clientRepository.save(client);
            });

            // Create credits for each client
            clientRepository.findAll().forEach(client -> {
                // Credit Personnel
                CreditPersonnel creditPersonnel = new CreditPersonnel();
                creditPersonnel.setClient(client);
                creditPersonnel.setDateDemande(LocalDate.now().minusMonths(2));
                creditPersonnel.setStatut(StatutCredit.ACCEPTE);
                creditPersonnel.setDateAcceptation(LocalDate.now().minusMonths(1));
                creditPersonnel.setMontant(5000.0);
                creditPersonnel.setDureeRemboursement(24);
                creditPersonnel.setTauxInteret(5.5);
                creditPersonnel.setMotif("Achat voiture");
                creditRepository.save(creditPersonnel);

                // Credit Immobilier
                CreditImmobilier creditImmobilier = new CreditImmobilier();
                creditImmobilier.setClient(client);
                creditImmobilier.setDateDemande(LocalDate.now().minusMonths(3));
                creditImmobilier.setStatut(StatutCredit.EN_COURS);
                creditImmobilier.setMontant(150000.0);
                creditImmobilier.setDureeRemboursement(240);
                creditImmobilier.setTauxInteret(3.2);
                creditImmobilier.setTypeBien(TypeBien.MAISON);
                creditRepository.save(creditImmobilier);

                // Credit Professionnel
                CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
                creditProfessionnel.setClient(client);
                creditProfessionnel.setDateDemande(LocalDate.now().minusMonths(1));
                creditProfessionnel.setStatut(StatutCredit.REJETE);
                creditProfessionnel.setMontant(25000.0);
                creditProfessionnel.setDureeRemboursement(60);
                creditProfessionnel.setTauxInteret(4.8);
                creditProfessionnel.setMotif("Expansion entreprise");
                creditProfessionnel.setRaisonSociale("Entreprise " + client.getNom());
                creditRepository.save(creditProfessionnel);
            });

            // Create remboursements for accepted credits
            creditRepository.findAll().stream()
                    .filter(credit -> credit.getStatut() == StatutCredit.ACCEPTE)
                    .forEach(credit -> {
                        for (int i = 0; i < 3; i++) {
                            Remboursement remboursement = new Remboursement();
                            remboursement.setCredit(credit);
                            remboursement.setDate(LocalDate.now().minusMonths(i));
                            remboursement.setMontant(credit.getMontant() / credit.getDureeRemboursement());
                            remboursement.setType(TypeRemboursement.MENSUALITE);
                            remboursementRepository.save(remboursement);
                        }
                    });
        };
    }

}
