package cinema.model.error;

/**
 * Class describing an error entity
 * @author Patryk Lewczuk
 */
public class ErrorBean {

    /**
     * Field containing an error cause
     */
    private String error;

    /**
     * Default constructor
     */
    public ErrorBean() {
    }

    // Getters and setters

    public ErrorBean(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
