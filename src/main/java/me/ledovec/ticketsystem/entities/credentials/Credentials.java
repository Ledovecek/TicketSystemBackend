package me.ledovec.ticketsystem.entities.credentials;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "authme")
@Entity
public class Credentials {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;
    @Column(nullable = false, name = "realname") private String realName;
    @Column(nullable = false) private String password;

}
