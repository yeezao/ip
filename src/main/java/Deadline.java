public class Deadline extends Task {

    String byDateTime;

    public Deadline(String description) {
        super(description);
    }

    public String getByDateTime() {
        return byDateTime;
    }

    public void setByDateTime(String byDateTime) {
        this.byDateTime = byDateTime;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String getAdditionalInfo() {
        return "(by: " + byDateTime + ")";
    }
}