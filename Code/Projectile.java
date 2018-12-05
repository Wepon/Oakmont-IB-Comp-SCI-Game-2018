
package com.mygdx.game;

public class Projectile extends Character{
    
    Hitbox[] hitBoxes;
    int numHitBoxes;
    
    public Projectile(int numHitBoxes, int g) {
        super();
        this.numHitBoxes = numHitBoxes;
        g = 0;
    }
    //general
    @Override
    public Hitbox[] getHitbox(boolean facingRight, double x, double y, int action, int frame, Player p) {
        
        return hitBoxes;
    }
}
