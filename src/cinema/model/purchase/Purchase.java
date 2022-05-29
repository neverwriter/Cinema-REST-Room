package cinema.model.purchase;

import cinema.model.Seat;

import java.util.UUID;

/**
 * Class representing one purchase of ticket
 */
public class Purchase {

    private String token;
    private Seat ticket;

    public Purchase(Seat seat) {
        this.token = UUID.randomUUID().toString();
        this.ticket = seat ;
    }

    public Purchase() {
    }



    // Getters and setters


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
