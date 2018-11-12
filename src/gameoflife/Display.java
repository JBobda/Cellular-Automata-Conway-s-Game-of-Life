package gameoflife;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Display {
    private final JFrame frame;
    private final Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    
    private final int width;
    private final int height;
    private final String title;
    
    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        canvas = new Canvas();
        init();
    }
    
    private void init(){
        canvas.setSize(new Dimension(width, height));
        frame.setTitle(title); 
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void render(){
        bufferStrategy = canvas.getBufferStrategy();
        if(bufferStrategy == null){
            canvas.createBufferStrategy(3);
            return;
        }
        
        graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
        bufferStrategy.show();
        
    }
    
}
