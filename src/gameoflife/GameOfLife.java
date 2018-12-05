package gameoflife;

import java.awt.*;

public class GameOfLife implements Runnable{
    //Static game info
    private static final String TITLE = "Conway's Game of Life";
    private static final int WIDTH = 750;
    private static final int HEIGHT = WIDTH*9/16 ;
    private static final int CELLWIDTH = 15;
    private static final int CELLHEIGHT = 15;
    
    private Display display;
    
    private Cell[][] gameBoard;
    private Thread thread;

    //Makes sure the program is running
    private boolean running;

    //Makes sure that the player has started the seed
    public boolean playing;
    
    public GameOfLife(){
        display = new Display(WIDTH, HEIGHT, TITLE, this);
        gameBoard = new Cell[HEIGHT/CELLHEIGHT][WIDTH/CELLWIDTH];
        running = true;
        playing = false;
    }

    @Override
    public void run(){
        init();
        while(running){
            update();
            display.repaint();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        stop();
    }
    
    public void update(){
        if(!playing){
            gameBoard[display.getMouseInput().getCellRow()][display.getMouseInput().getCellCol()].setStatus(display.getMouseInput().getStatus());
            return;
        }


        Cell[][] tempBoard = cloneBoard(gameBoard);

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                int aliveCount = 0;
                
                if((i != 0) && (j != 0) && (j < gameBoard[i].length-1) && (i < gameBoard.length-1)){
                    //Check the surrounding cells
                    if(gameBoard[i-1][j].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    
                    //Diagonals
                    
                    if(gameBoard[i-1][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j-1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i+1][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                    if(gameBoard[i-1][j+1].getStatus() == Status.ALIVE)
                        aliveCount++;
                       
                }
                
                if(gameBoard[i][j].getStatus() == Status.ALIVE){
                    //Death by isolation: Each live cell with one or fewer live neighbors will die in the next generation.
                    if(aliveCount < 2)
                        tempBoard[i][j].setStatus(Status.DEAD);
                    //Death by overcrowding: Each live cell with four or more live neighbors will die in the next generation.
                    else if(aliveCount > 3)
                        tempBoard[i][j].setStatus(Status.DEAD);
                    //Survival: Each live cell with either two or three live neighbors will remain alive for the next generation.
                    else if(aliveCount == 2 || aliveCount == 3)
                        tempBoard[i][j].setStatus(Status.ALIVE);
                }else if(gameBoard[i][j].getStatus() == Status.DEAD){
                    //Births: Each dead cell adjacent to exactly three live neighbors will become live in the next generation.
                    if(aliveCount == 3)
                        tempBoard[i][j].setStatus(Status.ALIVE);
                }
            }
        }

        gameBoard = cloneBoard(tempBoard);
    }
    
    public void render(Graphics g){
        for (Cell[] gameBoard1 : gameBoard) {
            for (Cell gameBoard11 : gameBoard1) {
                gameBoard11.render(g, CELLWIDTH, CELLHEIGHT);
            }
        }
    }
    
    public synchronized void start(){
        if(!running) running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void init(){
        //Loading game board with the different cells
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                Status stat = Status.DEAD;                
                gameBoard[i][j] = new Cell(i, j, stat);
            }   
        }
        
        //Glider
        gameBoard[2][3].setStatus(Status.ALIVE);
        gameBoard[3][3].setStatus(Status.ALIVE);
        gameBoard[4][3].setStatus(Status.ALIVE);
        gameBoard[4][2].setStatus(Status.ALIVE);
        gameBoard[3][1].setStatus(Status.ALIVE);
        
    }
    
    private Cell[][] cloneBoard(Cell[][] old){
        Cell[][] copy = new Cell[old.length][old[0].length];
        
        for (int i = 0; i < old.length; i++) {
            for (int j = 0; j < old[i].length; j++) {
                copy[i][j] = new Cell(i, j, old[i][j].getStatus());
            }
        }  
        return copy;
    }

    public static int getCELLWIDTH() {
        return CELLWIDTH;
    }

    public static int getCELLHEIGHT() {
        return CELLHEIGHT;
    }
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.start();
    }

}
