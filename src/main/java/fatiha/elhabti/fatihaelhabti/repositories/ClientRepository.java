package fatiha.elhabti.fatihaelhabti.repositories;


import fatiha.elhabti.fatihaelhabti.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}