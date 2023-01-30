package xyz.he00741098.minesweeperthefall;

import java.util.ArrayList;
import java.util.List;

public class manager{

private List<Game> games = new ArrayList<>();

    public manager(){


    }

public synchronized void addGame(Game game) {
    games.add(game);
    game.run();
}


}
