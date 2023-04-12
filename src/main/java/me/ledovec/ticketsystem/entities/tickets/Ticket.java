package me.ledovec.ticketsystem.entities.tickets;

import jakarta.persistence.*;
import lombok.Getter;
import me.ledovec.ticketsystem.enums.State;

@Getter @Entity @Table(name = "tickets")
public class Ticket {

    @Column @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column private String founder;
    @Column private String header;
    @Column private String tag;
    @Column private long createdAt;
    @Column private State state;

}
