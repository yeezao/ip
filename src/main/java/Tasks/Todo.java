package Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "[T]";
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo();
    }

    @Override
    public String getAdditionalInfoSave() {
        return super.getAdditionalInfoSave();
    }
}
