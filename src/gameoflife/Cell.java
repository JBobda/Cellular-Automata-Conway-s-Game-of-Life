package gameoflife;

import java.awt.*;

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

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
