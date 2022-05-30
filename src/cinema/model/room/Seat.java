package cinema.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class describing a seat in cinema room
 * @author Patryk Lewczuk
 */
public class Seat {

    private int row;

    private int column;

    private int price;

    @JsonIgnore
    private boolean sold;

    /**
     * Default constructor
     */
    public Seat() {
    }

    /**
     * Full constructor
     */
    public Seat(int row, int column, int price, boolean sold) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.sold = sold;
    }

    // Getters and setters

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
