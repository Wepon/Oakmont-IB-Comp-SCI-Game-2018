package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Hitbox;

/**
 *
 * @author colem_000
 */
public class _CharacterTemplate {
    
    String name = "Unknown";
    int Jumps = 2;
    double MaxFall = -40;
    double MaxRun = 10;
    double ObjA = 4;
    double weight = 3;
    double g = -1.4;

    double Height = 50;
    double Width = 50;
    public Hitbox[] HitBoxes = new Hitbox[2];
    //
    SpriteBatch batch;
    public static Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    Sprite sprite;
    
    boolean facedRight = false;

    public Sprite getAnimation(boolean facingRight, int action, float stateTime, Player p) {
        batch = new SpriteBatch();
        img = new Texture("penguin.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(img, 20, 18);
        if(Math.abs(p.ax) > .1 && p.jumpsLeft == p.Jumps){
            sprite = new Sprite(tmpFrames[0][0]);
        }
        else {
            sprite = new Sprite(tmpFrames[0][0]);
        }
        //interpret current player frame
        // auto flip sprite in correct direction
        // sprite.flip(true, false); // enable if sprite initially faces right : disable if sprite initially faces left
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
            if (frame < 10) {
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
            if (frame < 10) {
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
            if (frame < 20 && frame >= 10) {
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
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[2];
                Harr[0] = new Hitbox(x + (p.character.Width / 2) + 30, y + 10, 20, 45, 30, p);
                Harr[1] = new Hitbox(x + (p.character.Width / 2) - 30, y + 10, 20, 135, 30, p);
                return Harr;
            }
        }
        if (action == 7) {
            if (frame < 10) {
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
            if (frame < 10) {
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
            if (frame < 10) {
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
        Hitbox[] hb = new Hitbox[2];
        hb[0] = new Hitbox(p.x + (p.character.Width / 2), p.y + 10, 20, 0, 0, p);
        hb[1] = new Hitbox(p.x + (p.character.Width / 2), p.y + 30, 15, 0, 0, p);
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
