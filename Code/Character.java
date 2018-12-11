package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Hitbox;

/**
 *
 * @author colem_000
 */
public class Character {
    
    String name = "Odell";
    int Jumps = 2;
    double MaxFall = -40;
    double MaxRun = 10;
    double ObjA = 4;
    double weight = 3;
    double g = -1.4;

    double Height = 92;
    double Width = 75;
    public Hitbox[] HitBoxes = new Hitbox[2];
    
    //
    public static Texture img;
    public static TextureRegion[][] tmp;
    public static Animation<TextureRegion> walkAnimation;
    public static Animation<TextureRegion> jumpAnimation;
    public static Animation<TextureRegion> idleAnimation;
    public static Animation<TextureRegion> forwardAirAnimation;
    public static Animation<TextureRegion> downAirAnimation;
    public static Animation<TextureRegion> backAirAnimation;
    public static Animation<TextureRegion> neutralAirAnimation;
    public static Animation<TextureRegion> forwardSmashAnimation;
    public static Animation<TextureRegion> backSmashAnimation;
    public static Animation<TextureRegion> upSmashAnimation;
    public static Animation<TextureRegion> downSmashAnimation;
    public static Animation<TextureRegion> jabAnimation;
    Sprite sprite;
    
    boolean facedRight = false;

    public Sprite getAnimation(boolean facingRight, int action, float stateTime, Player p) {
        img = new Texture(this.name + ".png");
        //
        tmp = TextureRegion.split(img, img.getWidth() / 25, img.getHeight());
        TextureRegion[] walk = new TextureRegion[14];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 14; j++) {
				walk[index++] = tmp[i][j];
			}
		}
        TextureRegion[] jump = new TextureRegion[7];
		index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 18; j < 25; j++) {
				jump[index++] = tmp[i][j];
			}
		}
        TextureRegion[] idle = new TextureRegion[3];
        index = 0;
        for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 3; j++) {
                        idle[index++] = tmp[i][j];
                }
        }
        // interpret current animation being used
        walkAnimation = new Animation<TextureRegion>((float)Math.abs(.1f/p.vx), walk);
        jumpAnimation = new Animation<TextureRegion>(0.15f, jump);
        idleAnimation = new Animation<TextureRegion>(0.425f, idle);
        sprite = new Sprite(walkAnimation.getKeyFrame(0));
        if(Math.abs(p.ax) > .1 && p.jumpsLeft == p.Jumps){
            sprite = new Sprite(walkAnimation.getKeyFrame(stateTime, true));
        }
        if(Math.abs(p.vy) > 0 && action == 0){
            sprite = new Sprite(jumpAnimation.getKeyFrame(stateTime, true));
        }
        if(Math.abs(p.ax) <= .1 && Math.abs(p.vy) == 0) {
            sprite = new Sprite(idleAnimation.getKeyFrame(stateTime, true));
        }
        //interpret current player frame
        // auto flip sprite in correct direction
        sprite.flip(true, false); // enable if sprite initially faces right : disable if sprite initially faces left
        if (facingRight == true) {
            sprite.flip(true, false);
        }
        return sprite;
    }

    public Hitbox[] getHitbox(boolean facingRight, double x, double y, int action, int frame, Player p) {
        if (action == 1) {
            if(frame < 12){
                return null;
            }
            if (frame < 18) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y - 40, 30, 270, 30, p);
                return Harr;
            }
        }
        if (action == 2) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                if (facingRight) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) + 45, y + (p.character.Height / 2), 30, 20, 30, p);
                    return Harr;
                }
                if (facingRight == false) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) - 45, y + (p.character.Height / 2), 30, 160, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 3) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                if (facingRight == false) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) + 45, y + (p.character.Height / 2), 30, 20, 30, p);
                    return Harr;
                }
                if (facingRight) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) - 45, y + (p.character.Height / 2), 30, 160, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 4) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + p.character.Height + 30, 30, 90, 30, p);
                return Harr;
            }
        }
        if (action == 5) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 30, 90, 30, p);
                return Harr;
            }
            if (frame < 10 && frame >= 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 200, 90, 30, p);
                return Harr;
            }
            if (frame < 30 && frame >= 20) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y + (p.character.Height / 2), 300, 90, 30, p);
                return Harr;
            }
        }
        if (action == 6) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                Hitbox[] Harr = new Hitbox[2];
                Harr[0] = new Hitbox(x + (p.character.Width / 2) + 30, y + 10, 20, 45, 30, p);
                Harr[1] = new Hitbox(x + (p.character.Width / 2) - 30, y + 10, 20, 135, 30, p);
                return Harr;
            }
        }
        if (action == 7) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                
                if (facingRight) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) + 45, y + (p.character.Height / 2), 30, 20, 30, p);
                    return Harr;
                }
                if (facingRight == false) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) - 45, y + (p.character.Height / 2), 30, 160, 30, p);
                    return Harr;
                }
            }
        }
        if (action == 8) {
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                if (facingRight == false) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) + 45, y + (p.character.Height / 2), 30, 20, 30, p);
                    return Harr;
                }
                if (facingRight) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) - 45, y + (p.character.Height / 2), 30, 160, 30, p);
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
            if(frame < 5){
                return null;
            }
            if (frame < 10 && frame >= 5 ) {
                if (facingRight) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) + 35, y + (p.character.Height / 2), 20, 20, 10, p);
                    return Harr;
                }
                if (facingRight == false) {
                    Hitbox[] Harr = new Hitbox[1];
                    Harr[0] = new Hitbox(x + (p.character.Width / 2) - 35, y + (p.character.Height / 2), 20, 160, 10, p);
                    return Harr;
                }
            }
        }
        p.resetFrameNum();
        return null;
    }

    public Hitbox[] playerHitbox(Player p) {
        Hitbox[] hb = new Hitbox[3];
        hb[0] = new Hitbox(p.x + (p.character.Width / 2), p.y + 10, 18, 0, 0, p);
        hb[1] = new Hitbox(p.x + (p.character.Width / 2), p.y + 40, 18, 0, 0, p);
        hb[2] = new Hitbox(p.x + (p.character.Width / 2), p.y + 70, 18, 0, 0, p);
        return hb;
    }

    public Hitbox ledgeHitbox(Player p) {
        if(p.canGrabLedge && (p.Grounded() == null || p.onLedge)){
            if (p.facingRight) {
                return new Hitbox(p.x + p.character.Width, p.y + p.character.Height, 20, 0, 0, p);
            }
            if (p.facingRight != true) {
                return new Hitbox(p.x, p.y + p.character.Height, 20, 0, 0, p);
            }
            return null;
        }
        return null;
    }
}
