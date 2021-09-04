package Tasks;

public abstract class Task {

    private boolean isPending = true;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean isPending) {
        this.isPending = isPending;
    }

    public String getTaskDesc() {
        return description;
    }

    public void setTaskDesc(String taskDesc) {
        this.description = taskDesc;
    }

    public void markAsDone() {
        this.isPending = false;
    }

    public String getType() {
        return "[ ]";
    }

    public String getAdditionalInfo() {
        return "";
    }


}
