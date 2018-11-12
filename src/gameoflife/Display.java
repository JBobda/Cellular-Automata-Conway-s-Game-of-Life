package gameoflife;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    private final JFrame frame;
    
    private final int width;
    private final int height;
    private final String title;
    
    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        init();
    }
    
    private void init(){
        frame.setTitle(title); 
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
