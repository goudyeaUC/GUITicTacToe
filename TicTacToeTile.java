
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class TicTacToeTile extends JButton {
    private String player;
    private int row;
    private int col;


    public TicTacToeTile(int row, int col, String player) {
        super();
        this.row = row;
        this.col = col;
        this.player = player;
        this.setText(this.player);

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
        this.setText(this.player);
    }


}