package BattleArena;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player1 extends Player{
    private int dx;
    private int dy;
    private List<MissileP1> missiles;


    public Player1(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
    	missiles = new ArrayList<>();
        loadImage("src/resources/player1.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }
    
    public List<MissileP1> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E) {
            fire();
        }
        
        if (key == KeyEvent.VK_A) {
            dx = -1;
        }

        if (key == KeyEvent.VK_D) {
            dx = 1;
        }

        if (key == KeyEvent.VK_W) {
            dy = -1;
        }

        if (key == KeyEvent.VK_S) {
            dy = 1;
        }
    }

    public void fire() {
        missiles.add(new MissileP1(x + width, y + height / 2));
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_S) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}
