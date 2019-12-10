package BattleArena;

public class MissileP1 extends Player {
    private final int BOARD_WIDTH = 558;
    private final int MISSILE_SPEED = 2;

    public MissileP1(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("src/resources/missile1.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            visible = false;
    }
}
