package cinema.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

    private int row;
    private int column;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @JsonProperty("row")
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @JsonProperty("column")
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
