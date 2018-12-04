package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author colem_000
 */
public class Character {

    int Jumps = 2;
    double MaxFall = -40;
    double MaxRun = 10;
    double ObjA = 4;
    double weight = 3;
    double g = -2;

    double Height = 50;
    double Width = 50;
    public Hitbox[] HitBoxes = new Hitbox[2];

    boolean facedRight = false;

    public Sprite getAnimation(boolean facingRight, int action, int frame, Player p) {
        Texture img = new Texture("penguin.png");
        Sprite sprite = new Sprite(img, 20, 18);
        //interpret current player frame
        // auto flip sprite in correct direction
        if (facingRight == true) {
            sprite.flip(true, false);
        }
        return sprite;
    }

    public Hitbox[] getHitbox(boolean facingRight, double x, double y, int action, int frame, Player p) {
        if (action == 1) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y - 40, 30, 270, 30, p);
                return Harr;
            }
        }
        if (action == 2) {
            if (frame < 10) {
                if(facingRight){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) + 45, y + (p.character.Height / 2), 30, 0, 30, p);
                    return Harr;
                }
                if(facingRight == false){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) - 45, y + (p.character.Height / 2), 30, 180, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 3) {
            if (frame < 10) {
                if(facingRight == false){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) + 45, y + (p.character.Height / 2), 30, 0, 30, p);
                    return Harr;
                }
                if(facingRight){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) - 45, y + (p.character.Height / 2), 30, 180, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 4) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + p.character.Height + 30, 30, 90, 30, p);
                return Harr;
            }
        }
        if (action == 5) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 100, 270, 130, p);
                return Harr;
            }
            if(frame < 20 && frame >= 10){
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 200, 270, 130, p);
                return Harr;
            }
            if(frame < 30 && frame >= 20){
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 300, 270, 130, p);
                return Harr;
            }
        }
        if (action == 6) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[2];
                Harr[0] = new Hitbox(x + (p.character.Width / 2) + 30, y + 10, 20, 45, 20, p);
                Harr[1] = new Hitbox(x + (p.character.Width / 2) - 30, y + 10, 20, 135, 20, p);
                return Harr;
            }
        }
        if (action == 7) {
            if (frame < 10) {
                if(facingRight){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) + 45, y + (p.character.Height / 2), 30, 0, 30, p);
                    return Harr;
                }
                if(facingRight == false){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) - 45, y + (p.character.Height / 2), 30, 180, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 8) {
            if (frame < 10) {
                if(facingRight == false){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) + 45, y + (p.character.Height / 2), 30, 0, 30, p);
                    return Harr;
                }
                if(facingRight){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) - 45, y + (p.character.Height / 2), 30, 180, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 9) {
            if (frame < 5) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + p.character.Height + 20, 25, 90, 30, p);
                return Harr;
            }
        }
        if (action == 10) {
            if (frame < 10) {
                if(facingRight){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) + 35, y + (p.character.Height / 2), 20, 0, 10, p);
                    return Harr;
                }
                if(facingRight == false){
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width/2) - 35, y + (p.character.Height / 2), 20, 180, 10, p);
                    return Harr;
                }
            }
        }
        p.resetFrameNum();
        return null;
    }

    public Hitbox[] playerHitbox(Player p){
        Hitbox[] hb = new Hitbox[2];
        hb[0] = new Hitbox(p.x + (p.character.Width / 2), p.y + 10, 20, 0, 0, p);
        hb[1] = new Hitbox(p.x + (p.character.Width / 2), p.y + 30, 15, 0, 0, p);
        return hb;
    }
}
