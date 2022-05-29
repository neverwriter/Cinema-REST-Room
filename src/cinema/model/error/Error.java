package cinema.model.error;

public enum Error {

    TICKET_ALREADY_PURCHASED ("The ticket has been already purchased!"),
    OUT_OF_BOUND ("The number of a row or a column is out of bounds!");

    private final String description;

    Error(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
