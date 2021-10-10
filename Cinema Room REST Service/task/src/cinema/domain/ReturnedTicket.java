package cinema.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {

    @JsonProperty("returned_ticket")
    private Ticket returnedTicket;

    public ReturnedTicket() {
    }

    public ReturnedTicket(Ticket returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public Ticket getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Ticket returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
