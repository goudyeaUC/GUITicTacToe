
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wulft
 * modifications and extensions by
 * @author goudyea
 */
public class TicTacToe
{  //private static boolean ClickedValidMove=false;

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
   //private static JOptionPane badMove = new JOptionPane("Invaid Move!");
   private static TicTacToeTile Tile1 = new TicTacToeTile(0,0," ");
   private static TicTacToeTile Tile2 = new TicTacToeTile(0,1," ");
   private static TicTacToeTile Tile3 = new TicTacToeTile(0,2," ");
   private static TicTacToeTile Tile4 = new TicTacToeTile(1,0," ");
   private static TicTacToeTile Tile5 = new TicTacToeTile(1,1," ");
  private static   TicTacToeTile Tile6 = new TicTacToeTile(1,2," ");
   private static TicTacToeTile Tile7 = new TicTacToeTile(2,0," ");
   private static TicTacToeTile Tile8 = new TicTacToeTile(2,1," ");
  private static   TicTacToeTile Tile9 = new TicTacToeTile(2,2," ");
   private static TicTacToeWindow GameWindow = new TicTacToeWindow("Tic-Tac-Toe");
  private static  JPanel GameBoard = new JPanel(new GridLayout(3,3));

  private static ActionListener BadMove = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         showMessageDialog(GameBoard,"Invalid move!");

      }
  };

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {GameWindow.add(GameBoard);
        GameBoard.add(Tile1);
        GameBoard.add(Tile2);
        GameBoard.add(Tile3);
        GameBoard.add(Tile4);
        GameBoard.add(Tile5);
        GameBoard.add(Tile6);
        GameBoard.add(Tile7);
        GameBoard.add(Tile8);
        GameBoard.add(Tile9);
       GameWindow.show();
       TurnManager GotAMove = new TurnManager(false);
       boolean finished = false;
       boolean playing = true;
       Scanner in = new Scanner(System.in);
       PlayerManager GlobalPlayer = new PlayerManager("X");
       String player = "X";
       int moveCnt = 0;
       int row = -1;
       int col = -1;
       final int MOVES_FOR_WIN = 5;
       final int MOVES_FOR_TIE = 7;
       do // program loop
       {
           //begin a game
           GlobalPlayer.setPlayer("X");
           //player = "X";
           playing = true;
           moveCnt = 0;
           clearBoard();
           do  // game loop
           {
              // get the move
              do 
              {

                  ActionListener TileListener = new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent TileClicked) {
                    TicTacToeTile SourceTile = (TicTacToeTile) TileClicked.getSource();
                    if (SourceTile.getPlayer().equals(" "))
                    {
                        //board[SourceTile.getRow()-1][SourceTile.getCol()-1]=GlobalPlayer.getPlayer();
                        SourceTile.setPlayer(GlobalPlayer.getPlayer());
                        SourceTile.setText(GlobalPlayer.getPlayer());
                        SourceTile.addActionListener(BadMove);
                        GotAMove.setCompleted(true);
                        board[SourceTile.getRow()][SourceTile.getCol()]=GlobalPlayer.getPlayer()  ;
                    }



                  }
              };
                Tile1.addActionListener(TileListener);
                Tile2.addActionListener(TileListener);
                Tile3.addActionListener(TileListener);
                Tile4.addActionListener(TileListener);
                Tile5.addActionListener(TileListener);
                Tile6.addActionListener(TileListener);
                Tile7.addActionListener(TileListener);
                Tile8.addActionListener(TileListener);
                Tile9.addActionListener(TileListener);
                //display();

                //System.out.println("Enter move for " + player);
                //row = SafeInput.getRangedInt(in,"Enter row ", 1, 3);
                //col = SafeInput.getRangedInt(in,"Enter col ", 1, 3);
                row--; col--;  
              }while(!GotAMove.isCompleted());
              //Originally: }while (!isValidMove(row, col));
              //board[row][col] = player;
              moveCnt++;
              
              if(moveCnt >= MOVES_FOR_WIN)
              {
                  if(isWin(GlobalPlayer.getPlayer()))
                  { showMessageDialog(GameBoard,"Player" + GlobalPlayer.getPlayer()+ "Wins!");
                      display();
                      System.out.println("Player " + GlobalPlayer.getPlayer() + " wins!");
                      playing = false;
                  }
              }
              if(moveCnt >= MOVES_FOR_TIE)
              {
                  if(isTie())
                  {
                      display();
                      showMessageDialog(GameBoard,"It's a Tie!");
                      System.out.println("It's a Tie!");
                      playing = false;
                  }
              }
              if(GlobalPlayer.getPlayer().equals("X") )
              {
                  //player = "O";
                  GlobalPlayer.setPlayer("O");
              }
              else
              {
                  //player = "X";
                  GlobalPlayer.setPlayer("X");
              }
             GotAMove.setCompleted(false);
           }while(playing);
           int FinishedStatus=JOptionPane.showConfirmDialog(GameBoard,"Keep playing?","Continue",YES_NO_OPTION);
           if (FinishedStatus== NO_OPTION){finished=true;}
          // finished = SafeInput.getYNConfirm(in, "Done Playing? ");
       }while(!finished);
       
        
    }
    
    private static void clearBoard()
    {
       // sets all the board elements to a space
       for(int row=0; row < ROW; row++)
       {
           for(int col=0; col < COL; col++)
           {
               board[row][col] = " ";
           }
       }
       Tile1.setPlayer(" ");
       Tile2.setPlayer(" ");
       Tile3.setPlayer(" ");
       Tile4.setPlayer(" ");
       Tile5.setPlayer(" ");
       Tile6.setPlayer(" ");
       Tile7.setPlayer(" ");
       Tile8.setPlayer(" ");
       Tile9.setPlayer(" ");

       Tile1.setText(" ");
       Tile2.setText(" ");
       Tile3.setText(" ");
       Tile4.setText(" ");
       Tile5.setText(" ");
       Tile6.setText(" ");
       Tile7.setText(" ");
       Tile8.setText(" ");
       Tile9.setText(" ");

       Tile1.removeActionListener(BadMove);
       Tile2.removeActionListener(BadMove);
       Tile3.removeActionListener(BadMove);
       Tile4.removeActionListener(BadMove);
       Tile5.removeActionListener(BadMove);
       Tile6.removeActionListener(BadMove);
       Tile7.removeActionListener(BadMove);
       Tile8.removeActionListener(BadMove);
       Tile9.removeActionListener(BadMove);

    }
    private static void display() 
    {
       // shows the Tic Tac Toe game 
       for(int row=0; row < ROW; row++)
       {
           System.out.print("| ");
           for(int col=0; col < COL; col++)
           {
               System.out.print(board[row][col] + " | ");
           }
           System.out.println();
       }    

    }
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
       if(board[row][col].equals(" "))
           retVal = true;
       
       return retVal;
           
    }
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        
        return false;
    }
    private static boolean isColWin(String player)
    {
       // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
               board[1][col].equals(player) &&     
               board[2][col].equals(player))
            {
                return true;
            }                
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player)
    {
       // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
               board[row][1].equals(player) &&     
               board[row][2].equals(player))
            {
                return true;
            }                
        }
        return false; // no row win        
    }
    private static boolean isDiagnalWin(String player)
    {
       // checks for a diagonal win for the specified player
        
        if(board[0][0].equals(player) &&
           board[1][1].equals(player) &&    
           board[2][2].equals(player) )
        {
            return true;
        } 
        if(board[0][2].equals(player) &&
           board[1][1].equals(player) &&    
           board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }
    
    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so 
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") || 
               board[row][1].equals("X") ||
               board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") || 
               board[row][1].equals("O") ||
               board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            
            xFlag = oFlag = false;
            
        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") || 
               board[1][col].equals("X") ||
               board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") || 
               board[1][col].equals("O") ||
               board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }    
        // Now check for the diagonals
        xFlag = oFlag = false;
        
        if(board[0][0].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][2].equals("X") )
        {
            xFlag = true;
        } 
        if(board[0][0].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][2].equals("O") )
        {
            oFlag = true;
        } 
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        
        xFlag = oFlag = false;        
        
        if(board[0][2].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        

        // Checked every vector so I know I have a tie
        return true;
    }
}
