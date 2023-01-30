package xyz.he00741098.minesweeperthefall;

//import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class Game extends Thread{
    private int maxSizeX = 50;
    private int maxSizeY = 50;
    private int defaultMineCount = 10;
    private int defaultBoardSizeX = 10;
    private int defaultBoardSizeY = 10;

    private int defaultMoveQueueSize = 100;
    //10 attack = 1 line
    private int defaultAttack = 1;

    private BlockingQueue<Move> moveQueue;

    private boolean gameRunning = true;



    private List<Player> players = new ArrayList<>();

public Game(int playerCount){

    GameBoard startBoard = new GameBoard(defaultBoardSizeX, defaultBoardSizeY, defaultMineCount);
for(int i = 0; i<playerCount; i++){
    players.add(new Player());
    players.get(i).setBoardXsize(defaultBoardSizeX);
    players.get(i).setBoardYsize(defaultBoardSizeY);
    players.get(i).setMineCount(defaultMineCount);
    players.get(i).setCurrentGameBoard(startBoard);
}
moveQueue = new ArrayBlockingQueue<>(defaultMoveQueueSize);

}

public void addMove(Move move){
    try {
        moveQueue.put(move);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}

public void run(){
    int currentBoardSizeX = defaultBoardSizeX;
    int currentBoardSizeY = defaultBoardSizeY;
    int currentMineCount = defaultMineCount;


    while(gameRunning){
        try {
            Move move = moveQueue.take();
            proccess(move);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}


    public void endGame(){
        gameRunning = false;
    }

    public void proccess(@NotNull Move move){
    int attack;
        Player player = players.get(move.getPlayerId());
        if(player.getCurrentGameBoard().getBoard()[move.getY()][move.getX()] == 9){
            player.setBoardXsize(player.getBoardXsize()+1);
            player.setBoardYsize(player.getBoardYsize()+1);
            player.setBackToBack(0);
            player.setCombo(0);
        }
        else{
            if(player.getCurrentGameBoard().isComplete()){
                player.setBackToBack(player.getBackToBack()+1);
                player.setCurrentGameBoard(new GameBoard(player.getBoardXsize(), player.getBoardYsize(), player.getMineCount()));
            }


            player.setCombo(player.getCombo()+1);
            attack = player.getCombo()*defaultAttack* (2*player.getBackToBack());
            if(player.getDamage().get()==0){
            player.getTarget().attack(attack);}
            else{
                player.getDamage().addAndGet((int) (-attack/0.8));
            }

        }



    }

}
