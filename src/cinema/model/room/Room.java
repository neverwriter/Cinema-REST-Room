package cinema.model.room;

import java.util.Queue;

/**
 * Class describing a cinema Room
 * @author Patryk Lewczuk
 */
public class Room {

    private int total_rows;
    private int total_columns;
    private Queue<Seat> available_seats;

    /**
     * Default constructor
     */
    public Room() {
    }

    /**
     * Full constructor
     */
    public Room(int total_rows, int total_columns, Queue<Seat> available_seats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    // Getters ans setters

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public Queue<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Queue<Seat> available_seats) {
        this.available_seats = available_seats;
    }
}
