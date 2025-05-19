package fatiha.elhabti.fatihaelhabti.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Credit> credits;
}