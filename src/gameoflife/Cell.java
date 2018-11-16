package gameoflife;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int row;
    private int column;
    
    private Status status;
    
    public Cell(int row, int column, Status status){
        this.row = row;
        this.column = column;
        this.status = status;
    }
    
    public void update(){
        
            
    }
    
    public void render(Graphics graphics, int cellWidth, int cellHeight){
        graphics.setColor(Color.BLACK);
        graphics.drawRect(row*cellHeight, column*cellWidth, cellWidth, cellHeight);
        if(status == Status.ALIVE)
            graphics.fillRect(row*cellHeight, column*cellWidth, cellWidth, cellHeight);
    }
}
