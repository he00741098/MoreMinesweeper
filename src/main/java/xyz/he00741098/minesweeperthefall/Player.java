package xyz.he00741098.minesweeperthefall;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {
private int boardXsize;
private int boardYsize;

private int mineCount;

private Player target;
private int backToBack;
private int combo;
private volatile AtomicInteger damage;
private volatile AtomicInteger turnsToDamage=new AtomicInteger(3);

private GameBoard currentGameBoard;

private List<GameBoard> pastGameBoards = new ArrayList<>();

    public void setCurrentGameBoard(GameBoard newGameBoard) {
        pastGameBoards.add(currentGameBoard);
        this.currentGameBoard = newGameBoard;
    }
void attack(int damage){
this.damage.addAndGet(damage);
turnsToDamage.addAndGet(-1);
if(turnsToDamage.get()>=0){
    this.boardXsize+=damage/10;
    this.boardYsize+=damage/10;

}
    }


}
