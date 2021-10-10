package cinema.controllers;

import cinema.domain.*;
import cinema.exception.AlreadyPurchasedException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CinemaController {

    private final Ticket[][] tickets;
    private List<TokenizedTicket> tokenizedTickets = new ArrayList<>();
    private Stats statistic = new Stats(0, 81, 0);

    {
        tickets = new Ticket[9][9];
        byte price;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                price = 8;
                if (i <= 4) {
                    price = 10;
                }
                tickets[i][j] = new Ticket(i, j, price, false);
            }
        }
    }

    @GetMapping("/seats")
    public @ResponseBody Cinema getSeats() {
        return new Cinema(9, 9);
    }

    @PostMapping("/purchase")
    public @ResponseBody TokenizedTicket bookSeat(@RequestBody Seat seat) {
        Ticket currentTicket;
        try {
             currentTicket = tickets[seat.getRow()][seat.getColumn()];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SeatOutOfBoundsException();
        }

        if (currentTicket.isBooked()) {
            throw new AlreadyPurchasedException();
        } else {
            currentTicket.setBooked(true);
        }
        tickets[seat.getRow()][seat.getColumn()] = currentTicket;
        TokenizedTicket tokenizedTicket = new TokenizedTicket(UUID.randomUUID().toString(), currentTicket);
        tokenizedTickets.add(tokenizedTicket);
        statistic.changeStatisticAfterBook(currentTicket.getPrice());
        return tokenizedTicket;
    }

    @PostMapping("/return")
    public @ResponseBody ReturnedTicket returnTicket(@RequestBody TokenizedTicket tokenizedTicket) {
        ReturnedTicket returnedTicket;
        Ticket currentTicket;
        if (tokenizedTicket != null) {
            currentTicket = findTicketByToken(tokenizedTicket);
            if (currentTicket != null) {
                returnedTicket = new ReturnedTicket(currentTicket);
                currentTicket.setBooked(false);
                tickets[currentTicket.getRow()][currentTicket.getColumn()] = currentTicket;
                statistic.changeStatisticAfterReturn(currentTicket.getPrice());
                return returnedTicket;
            } else {
                throw new WrongTokenException();
            }
        }
        return null;
    }

    private Ticket findTicketByToken(TokenizedTicket tokenizedTicket) {
        for (TokenizedTicket ticket :
                tokenizedTickets) {
            if (ticket.equals(tokenizedTicket)) {
                return ticket.getTicket();
            }
        }
        return null;
    }

    @PostMapping("/stats")
    public @ResponseBody Stats obtainStatistic(@RequestParam(value = "password", required = false) String password) {
        if (password == null) {
            throw new WrongPasswordException();
        } else {
            return statistic;
        }
    }
}
