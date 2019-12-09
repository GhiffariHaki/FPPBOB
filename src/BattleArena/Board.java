package BattleArena;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Player1 p1;
    protected int p1life = 3;
    private Player2 p2;
    protected int p2life = 3;
    private boolean ingame;
    private final int DELAY = 15;
    
    public Board() {

        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(500, 500));

        p1 = new Player1(100,200);
        p2 = new Player2(350,200);

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics g) {

        if (p1.isVisible()) {
            g.drawImage(p1.getImage(), p1.getX(), p1.getY(),
                    this);
        }

        List<MissileP1> ms1 = p1.getMissiles();

        for (MissileP1 missile : ms1) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), 
                        missile.getY(), this);
            }
        }
        
        if (p2.isVisible()) {
            g.drawImage(p2.getImage(), p2.getX(), p2.getY(),
                    this);
        }

        List<MissileP2> ms2 = p2.getMissiles();

        for (MissileP2 missile : ms2) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), 
                        missile.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Player 1 Life Left: " + p1life, 5, 15);
        g.drawString("Player 2 Life Left: " + p2life, 400, 15);
    }
    
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (500 - fm.stringWidth(msg)) / 2,
                500 / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updatePlayer();
        updateMissiles1();
        updateMissiles2();

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
    
    private void updatePlayer() {

        if (p1.isVisible()) {
            p1.move();
        }
        if (p2.isVisible()) {
        	p2.move();
        }
    }

    private void updateMissiles1() {
    	
        List<MissileP1> ms = p1.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            MissileP1 m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }
    
    private void updateMissiles2() {
    	
        List<MissileP2> ms = p2.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            MissileP2 m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }
    
    public void checkCollisions() {

        List<MissileP1> ms = p1.getMissiles();

        for (MissileP1 m : ms) {

            Rectangle r1 = m.getBounds();
            Rectangle r2 = p2.getBounds();
            
            if (r1.intersects(r2)) {
                   
            	m.setVisible(false);
            	p2life -= 1;
            	if(p2life == 0) {
            		ingame = false;
            	}
            }
        }
        
        List<MissileP2> mq = p2.getMissiles();

        for (MissileP2 m : mq) {

            Rectangle r1 = m.getBounds();
            Rectangle r2 = p1.getBounds();
            
            if (r1.intersects(r2)) {
                   
            	m.setVisible(false);
            	p1life -= 1;
            	if(p1life == 0) {
            		ingame = false;
            	}
            }
        }
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
