package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime byDateTime;

    public Deadline(String description) {
        super(description);
    }

    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    public void setByDateTime(LocalDateTime byDateTime) {
        this.byDateTime = byDateTime;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String getAdditionalInfo() {
        return "(by: " + byDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String getAdditionalInfoSave() {
        return byDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
