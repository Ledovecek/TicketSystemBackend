package me.ledovec.ticketsystem.services.tickets.messages;

import me.ledovec.ticketsystem.entities.tickets.Message;
import me.ledovec.ticketsystem.repositories.tickets.MessageRepository;
import me.ledovec.ticketsystem.repositories.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5173/", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = "Content-Type", allowCredentials = "true")
@RestController
@RequestMapping("api/messages")
public class Messages {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{id}")
    public List<Message> getTicketMessages(@PathVariable long id) {
        return messageRepository.findMessagesByTicketId(id);
    }

    @PostMapping("message")
    public Message sendMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

}
