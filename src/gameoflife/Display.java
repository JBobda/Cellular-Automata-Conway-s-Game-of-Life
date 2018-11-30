package gameoflife;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Display {
    private final JFrame frame;
    private final Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private MouseInput mouseInput;
    
    private final int width;
    private final int height;
    private final String title;
    
    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        canvas = new Canvas();
        mouseInput = new MouseInput();
        init();
    }
    
    private void init(){
        canvas.setSize(new Dimension(getWidth(), getHeight()));
        canvas.addMouseListener(mouseInput);
        frame.setTitle(getTitle()); 
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * @return the bufferstrategy 
     */
    public BufferStrategy getBufferStrategy(){
        return bufferStrategy;
    }
    
    /**
     * @param bufferStrategy the bufferStrategy to set
     */
    public void setBufferStrategy(BufferStrategy bufferStrategy) {
        this.bufferStrategy = bufferStrategy;
    }
    
    /**
     * @return the graphics 
     */
    public Graphics getGraphics(){
        return graphics;
    }
    
    /**
     * @param graphics the graphics to set
     */
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
    
    /**
     * @return the canvas 
     */
    public Canvas getCanvas(){
        return canvas;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
}
