package gameoflife;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private int mouseX;
    private int mouseY;
    private int cellCol;
    private int cellRow;
    private int CELLWIDTH;
    private int CELLHEIGHT;
    private Status status;

    public MouseInput(int cellWidth, int cellHeight){
        mouseX = -10;
        mouseY = -10;
        CELLWIDTH = cellWidth;
        CELLHEIGHT = cellHeight;
        status = Status.DEAD;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(e.getButton() == 1)
            status = Status.ALIVE;
        else if(e.getButton() == 3)
            status = Status.DEAD;

        calculateCurrentCell();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = -1;
        mouseY = -1;
        calculateCurrentCell();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(e.getButton() == 1)
            status = Status.ALIVE;
        else if(e.getButton() == 3)
            status = Status.DEAD;

        calculateCurrentCell();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void calculateCurrentCell(){
        cellCol = mouseX/CELLWIDTH;
        cellRow = mouseY/CELLHEIGHT;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getCellCol() {
        return cellCol;
    }

    public void setCellCol(int cellCol) {
        this.cellCol = cellCol;
    }

    public int getCellRow() {
        return cellRow;
    }

    public void setCellRow(int cellRow) {
        this.cellRow = cellRow;
    }

    public int getCELLWIDTH() {
        return CELLWIDTH;
    }

    public void setCELLWIDTH(int CELLWIDTH) {
        this.CELLWIDTH = CELLWIDTH;
    }

    public int getCELLHEIGHT() {
        return CELLHEIGHT;
    }

    public void setCELLHEIGHT(int CELLHEIGHT) {
        this.CELLHEIGHT = CELLHEIGHT;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
