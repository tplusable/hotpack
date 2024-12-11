package net.tplusable.hotpack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name="username")
    private String username;

    @Column(nullable = false, name="password")
    private String password;

    @Column(unique = true, nullable = false, name="email")
    private String email;

    @Column(nullable = false, name="name")
    private String name;
}