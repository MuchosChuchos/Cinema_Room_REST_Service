package cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ticket extends Seat{

    private byte price;
    private boolean booked;

    public Ticket() {
        super();
    }

    public Ticket(int row, int column, byte price, boolean booked) {
        super(row, column);
        this.price = price;
        this.booked = booked;
    }

    public byte getPrice() {
        return price;
    }

    public void setPrice(byte price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
