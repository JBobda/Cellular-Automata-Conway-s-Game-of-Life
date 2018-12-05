package gameoflife;

import java.awt.*;
import javax.swing.*;

public class Display extends JPanel {
    private final JFrame frame;
    private final GameOfLife game;
    private MouseInput mouseInput;
    private JPanel menuPanel;
    private JButton startButton;
    
    private final int width;
    private final int height;
    private final String title;
    
    public Display(int width, int height, String title, GameOfLife game){
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        this.game = game;
        menuPanel = new JPanel();
        startButton = new JButton("Start");
        mouseInput = new MouseInput(game.getCELLWIDTH(), game.getCELLHEIGHT());
        init();
    }
    
    private void init(){
        menuPanel.setLayout(new CardLayout());

        //Set the play status of the game opposite of what it currently was using a lambda expression
        startButton.addActionListener(e -> {
            game.playing = !game.playing;
            if(game.playing == false)
                startButton.setText("Start");
            else
                startButton.setText("Stop");
        });


        menuPanel.add(startButton);
        menuPanel.setBackground(Color.GRAY);
        this.setLayout(null);
        menuPanel.setBounds(15,height - 75,105,45);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
        this.add(menuPanel);
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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
