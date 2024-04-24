import java.util.Objects;
public class PlayerManager {

    private String player;

    public PlayerManager(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
