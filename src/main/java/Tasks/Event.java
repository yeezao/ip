package Tasks;

import UserInteractions.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime atDateTime;

    public Event(String description, String[] atDateTimeSplit) {
        super(description);
        String eventString = Parser.createRemainingString(atDateTimeSplit);
        atDateTime = Parser.parseToDateTime(eventString);
    }

    public Event(String description, String atDateTimeSaved) {
        super(description);
        atDateTime = Parser.parseToDateTime(atDateTimeSaved);
    }

    public LocalDateTime getAtDateTime() {
        return atDateTime;
    }

    public void setAtDateTime(LocalDateTime atDateTime) {
        this.atDateTime = atDateTime;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String getAdditionalInfo() {
        return "(at: " + atDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String getAdditionalInfoSave() {
        return atDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

}
