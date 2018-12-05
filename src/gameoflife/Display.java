package gameoflife;

import java.awt.*;
import javax.swing.*;

public class Display extends JPanel {
    private final JFrame frame;
    private final GameOfLife game;
    private MouseInput mouseInput;
    
    private final int width;
    private final int height;
    private final String title;
    
    public Display(int width, int height, String title, GameOfLife game){
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        this.game = game;
        mouseInput = new MouseInput(game.getCELLWIDTH(), game.getCELLHEIGHT());
        init();
    }
    
    private void init(){
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public void setMouseInput(MouseInput mouseInput) {
        this.mouseInput = mouseInput;
    }
}
