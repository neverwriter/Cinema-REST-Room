package cinema.model.room;

public class ReturnedTicket {
    private Seat returned_ticket;

    public ReturnedTicket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public ReturnedTicket() {
    }

    // Getters and setters


    public Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
