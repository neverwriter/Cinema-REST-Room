package cinema.model.error;

public enum Error {

    TICKET_ALREADY_PURCHASED ("The ticket has been already purchased!"),
    OUT_OF_BOUND ("The number of a row or a column is out of bounds!"),
    WRONG_TOKEN("Wrong token!"),
    WRONG_PASSWORD("The password is wrong!"),
    TICKET_NOT_RETURN("Internal server error, unable to return ticket!");



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
