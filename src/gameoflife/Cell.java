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
        graphics.drawRect(getColumn()*cellWidth, getRow()*cellHeight, cellWidth, cellHeight);
        if(status == Status.ALIVE)
            graphics.fillRect(getColumn()*cellWidth,getRow()*cellHeight, cellWidth, cellHeight);
    }
    
    /**
     * @return the status 
     */
    public Status getStatus(){
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }
}
