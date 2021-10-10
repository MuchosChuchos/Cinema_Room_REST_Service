package cinema.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int totalRows;
    private int totalColumns;
    private final List<Ticket> availableSeats = new ArrayList<>();

    public Cinema() {
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        byte price;

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                price = 8;
                if (i <= 4) {
                    price = 10;
                }
                availableSeats.add(new Ticket(i, j, price, false));
            }
        }
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Ticket> getAvailableSeats() {
        return availableSeats;
    }
}
