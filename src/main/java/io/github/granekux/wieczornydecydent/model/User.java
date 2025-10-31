package io.github.granekux.wieczornydecydent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Używamy auto-inkrementacji z PostgreSQL
    private Long id;

    @Column(nullable = false, unique = true) // Wymagany i unikalny
    private String email;

    @Column(nullable = false)
    private String password; // Będziemy to oczywiście hashować!

    // W przyszłości możesz dodać np. role, datę rejestracji itp.
}
