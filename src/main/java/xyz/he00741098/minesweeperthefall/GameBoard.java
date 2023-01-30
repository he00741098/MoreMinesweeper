package xyz.he00741098.minesweeperthefall;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class GameBoard {

    private int[][] board;
    private int[][] boardState;
    private int startX, startY;

    public void move(int x, int y){
boardState[y][x] = 0;

    }
    public boolean isComplete(){
        for(int i = 0; i<boardState.length; i++){
            for(int j = 0; j<boardState[i].length; j++){
                if(boardState[i][j]==1){
                    return false;
                }
            }
        }
        return true;
    }



    public GameBoard(int boardWidth, int boardHeight, int mineCount){
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        board = new int[boardHeight][boardWidth];
        boardState = new int[boardHeight][boardWidth];
        for(int i = 0; i<mineCount; i++){
            int randX = (threadLocalRandom.nextInt(boardWidth));
            int randY = threadLocalRandom.nextInt(boardHeight);
            if(board[randY][randX]!=9){
                board[randY][randX] = 9;
                //increment stuff
                boolean xLeftInBound = true;
                boolean xRightInBound = true;
                boolean yTopInBound = true;
                boolean yBotInBound = true;
                if(randY+1>=boardHeight){
                    yTopInBound = false;

                }
                if(randY-1<0){
                    yBotInBound = false;

                }
                if(randX+1>=boardWidth){
                    xRightInBound = false;

                }

                if(randX-1<0){
                    xLeftInBound = false;

                }

                //top right
                if(yTopInBound&&xRightInBound){
                    if(board[randY+1][randX+1]!=9){
                        board[randY+1][randX+1]++;
                    }
                }


                //bot right
                if(yBotInBound&&xRightInBound){
                    if(board[randY-1][randX+1]!=9){
                        board[randY-1][randX+1]++;
                    }

                }


                //mid right

                if(xRightInBound){
                    if(board[randY][randX+1]!=9){
                        board[randY][randX+1]++;
                    }

                }

                //mid top

                if(yTopInBound){
                    if(board[randY+1][randX]!=9){
                        board[randY+1][randX]++;
                    }

                }

                //mid bot

                if(yBotInBound){
                    if(board[randY-1][randX]!=9){
                        board[randY-1][randX]++;
                    }

                }

                //top left

                if(yTopInBound&&xLeftInBound){
                    if(board[randY+1][randX-1]!=9){
                        board[randY+1][randX-1]++;
                    }

                }
                //bot left

                if(yBotInBound&&xLeftInBound){
                    if(board[randY-1][randX-1]!=9){
                        board[randY-1][randX-1]++;
                    }

                }

                //mid left
                if(xLeftInBound){
                    if(board[randY][randX-1]!=9){
                        board[randY][randX-1]++;
                    }

                }

            }else{i++;}

        }
        //find a group of zeros and set startX and startY to that
        boolean found = false;
        for(int y = 0; y<boardHeight; y++){
            if(found){
                break;
            }
            for(int x = 0; x<boardWidth; x++){
                if(board[y][x]==0){
                    startX = x;
                    startY = y;
                    found = true;
                    break;
                }
            }

        }
//set up board state - for every 0, on board, set to one
        for(int y = 0; y<boardHeight; y++){
            for(int x = 0; x<boardWidth; x++){
                if(board[y][x]==0){
                    boardState[y][x] = 1;
                }
            }

        }




    }
}
