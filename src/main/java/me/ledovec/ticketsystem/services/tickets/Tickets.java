package me.ledovec.ticketsystem.services.tickets;

import me.ledovec.ticketsystem.entities.luckperms.UserGroup;
import me.ledovec.ticketsystem.entities.tickets.Ticket;
import me.ledovec.ticketsystem.entities.tickets.Token;
import me.ledovec.ticketsystem.repositories.luckperms.UserGroupRepository;
import me.ledovec.ticketsystem.repositories.tickets.TicketRepository;
import me.ledovec.ticketsystem.repositories.tickets.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://127.0.0.1:5173/", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = "Content-Type")
public class Tickets {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("tickets/{token}")
    public List<Ticket> getTickets(@PathVariable String token) {
        Token fetchedToken = tokenRepository.findByToken(token);
        if (fetchedToken != null) {
            UserGroup userGroup = userGroupRepository.findByUid(fetchedToken.getUserUUID());
            String primaryGroup = userGroup.getPrimaryGroup();
            List<Ticket> tickets = ticketRepository.findAll();
            if (primaryGroup.equals("owner")) {
                return tickets;
            }
        }
        return null;
    }

    @PostMapping("ticket")
    public void createTicket(@RequestBody Ticket ticket) {
        ticketRepository.save(ticket);
    }

}
