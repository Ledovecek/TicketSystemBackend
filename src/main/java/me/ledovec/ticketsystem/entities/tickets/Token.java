package me.ledovec.ticketsystem.entities.tickets;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Token {

    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
    @Column private String token;
    @Column(name = "user_uuid") private String userUUID;

}
