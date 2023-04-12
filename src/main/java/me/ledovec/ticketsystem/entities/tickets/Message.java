package me.ledovec.ticketsystem.entities.tickets;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "ticket_messages")
@Getter
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;
    @Column private String userUuid;
    @Column private long ticketId;
    @Column private String content;
    @Column private long date;

}
