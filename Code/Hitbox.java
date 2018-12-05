package com.mygdx.game;

/**
 * @author Dillon Cole
 *
 */
public class Hitbox {

    public Hitbox(double x, double y, double radius, int angle, int force, Player player) {
        this.x = x;
        this.y = y;
        this.r = radius;
        this.angle = angle;
        this.force = force;
        this.player = player;
    }

    public Hitbox(double x, double y, double radius, int angle, int force) {
        this.x = x;
        this.y = y;
        this.r = radius;
        this.angle = angle;
        this.force = force;
    }

    public Hitbox(double x, double y, double radius, int angle, int force, Player player, int grabDireq) {
        this.x = x;
        this.y = y;
        this.r = radius;
        this.angle = angle;
        this.force = force;
        this.player = player;
        this.grabDireq = grabDireq;
    }

    public void moveHitbox(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double x;
    double y;
    double r;
    int angle;
    int force;
    Player player;
    int grabDireq = 0;

    public void addPlayer(Player p) {
        this.player = p;
    }

    public Hitbox[] hitboxCollision(Hitbox h) {
        if (h.grabDireq == 0) {
            double dist = Math.sqrt((this.x - h.x) * (this.x - h.x) + (this.y - h.y) * (this.y - h.y));
            if (dist <= this.r + h.r && h.player != this.player) {
                Hitbox[] hArr = new Hitbox[2];
                hArr[0] = this; // player
                hArr[1] = h; // Hitbox obj
                return hArr;
            } else {
                return null;
            }
        } else {
            if (this.player.facingRight == false && h.grabDireq == 2) {
                double dist = Math.sqrt((this.x - h.x) * (this.x - h.x) + (this.y - h.y) * (this.y - h.y));
                if (dist <= this.r + h.r && h.player != this.player) {
                    Hitbox[] hArr = new Hitbox[2];
                    hArr[0] = this; // player
                    hArr[1] = h; // Hitbox obj
                    return hArr;
                } else {
                    return null;
                }
            }
            if (this.player.facingRight && h.grabDireq == 1) {
                double dist = Math.sqrt((this.x - h.x) * (this.x - h.x) + (this.y - h.y) * (this.y - h.y));
                if (dist <= this.r + h.r && h.player != this.player) {
                    Hitbox[] hArr = new Hitbox[2];
                    hArr[0] = this; // player
                    hArr[1] = h; // Hitbox obj
                    return hArr;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
