public class TurnManager {
    private boolean Completed;

    public TurnManager(boolean TurnComplete){this.Completed=TurnComplete;}

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean TurnComplete) {
        Completed = TurnComplete;
    }
}


