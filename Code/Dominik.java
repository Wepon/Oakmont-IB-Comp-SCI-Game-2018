/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author cole.marquard
 */
public class Dominik extends Character {
    
    public Dominik(){
        this.name = "Dominik";
    }
    int Jumps = 2;
    double MaxFall = -40;
    double MaxRun = 10;
    double ObjA = 4;
    double weight = 3;
    double g = -1.4;

    double Height = 50;
    double Width = 50;
    public Hitbox[] HitBoxes = new Hitbox[2];
    
    SpriteBatch batch;
    Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    Sprite sprite;
    
    @Override
    public Hitbox[] getHitbox(boolean facingRight, double x, double y, int action, int frame, Player p) {
        if (action == 1) {
            if (frame < 10) {
                Hitbox[] Harr = new Hitbox[1];
                Harr[0] = new Hitbox(x + (p.character.Width / 2), y - 40, 30, 270, 0, p);
                return Harr;
            }
            if (frame < 20 && frame >=10) {
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

    
}
