package com.mygdx.game;

/**
 * @author Dillon Cole
 * 
 */
public class Hitbox {
    public Hitbox(int x, int y, int radius, int angle, int force, Player player){
        this.x = x;
        this.y = y;
        this.r = radius;
        this.angle = angle;
        this.force = force;
        this.player = player;
    }
    public Hitbox(int x, int y, int radius, int angle, int force){
        this.x = x;
        this.y = y;
        this.r = radius;
        this.angle = angle;
        this.force = force;
    }
    public void moveHitbox(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    int x;
    int y;
    int r;
    int angle;
    int force;
    Player player;
    
    public void addPlayer(Player p){
        this.player = p;
    }
    
    public Hitbox[] hitboxCollision(Hitbox h){
        double dist = Math.sqrt((this.x - h.x)*(this.x - h.x) + (this.y - h.y)*(this.y - h.y));
        if(dist <= this.r + h.r && h.player != this.player){
            Hitbox[] hArr = new Hitbox[2];
            hArr[0] = this; // player
            hArr[1] = h; // Hitbox obj
            return hArr;         
        } else {
            return null;
        }
    } 
}
