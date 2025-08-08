package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Data
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double balance;
    
    @Enumerated(EnumType.STRING)
    private AccountType type;


<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.EAGER)
=======
    @ManyToOne(fetch = FetchType.LAZY)
>>>>>>> cf83aff6a78db13d4b57b430b69755b3012088e5
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
