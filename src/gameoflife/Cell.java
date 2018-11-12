package gameoflife;

public class Cell {
    private int row;
    private int column;
    
    private Status status;
    
    public Cell(int row, int column, Status status){
        this.row = row;
        this.column = column;
        this.status = status;
    }
}
