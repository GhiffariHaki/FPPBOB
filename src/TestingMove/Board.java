package TestingMove;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Player1 p1;
    private Player2 p2;
    private final int DELAY = 10;

    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        p1 = new Player1(100,60);
        p2 = new Player2(300,60);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(p1.getImage(), p1.getX(), 
            p1.getY(), this);
        g2d.drawImage(p2.getImage(), p2.getX(),
        	p2.getY(), this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        step();
    }
    
    private void step() {
        
        p1.move();
        p2.move();
        
        repaint(p1.getX()-1, p1.getY()-1, 
                p1.getWidth()+2, p1.getHeight()+2);
        repaint(p2.getX()-1, p2.getY()-1, 
                p2.getWidth()+2, p2.getHeight()+2);    
    }    

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
            p2.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            p1.keyPressed(e);
            p2.keyPressed(e);
        }
    }
}