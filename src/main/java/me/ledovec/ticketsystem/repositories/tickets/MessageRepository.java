package me.ledovec.ticketsystem.repositories.tickets;

import me.ledovec.ticketsystem.entities.tickets.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessagesByTicketId(long ticketId);

}
