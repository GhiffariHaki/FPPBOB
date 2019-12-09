package BattleArena;

public class MissileP2 extends Player {
    private final int MISSILE_SPEED = 2;
    protected int dummy;

    public MissileP2(int x, int y) {
        super(x, y);
        dummy = x;
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("src/resources/missile2.png");
        getImageDimensions();        
    }

    public void move() {
        
        x -= MISSILE_SPEED;
        
        if (x < 0)
            visible = false;
    }
}
