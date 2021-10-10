package cinema.domain;

import java.util.Objects;

public class TokenizedTicket {

    private String token;
    private Ticket ticket;

    public TokenizedTicket() {
    }

    public TokenizedTicket(String token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        String token = ((TokenizedTicket) o).getToken();
        return token.equals(this.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
