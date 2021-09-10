package Tasks;

public class Event extends Task {

    private String atDateTime;

    public Event(String description) {
        super(description);
    }


    public String getAtDateTime() {
        return atDateTime;
    }

    public void setAtDateTime(String atDateTime) {
        this.atDateTime = atDateTime;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String getAdditionalInfo() {
        return "(at: " + atDateTime + ")";
    }

    @Override
    public String getAdditionalInfoSave() {
        return atDateTime;
    }

}
