package xyz.he00741098.minesweeperthefall;

import lombok.Data;

@Data
public class Move {

    private int x;
    private int y;
    private int playerId;

    public Move(int x, int y, int playerId) {
        this.x = x;
        this.y = y;
        this.playerId = playerId;
    }


}
