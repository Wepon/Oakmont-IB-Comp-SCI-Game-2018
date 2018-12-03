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

    public Sprite getAnimation(boolean facingRight) {
        Texture img = new Texture("penguin.png");
        Sprite sprite = new Sprite(img,20,18);
        if (facingRight == true) {
            sprite.flip(true, false);
        } return sprite;
    }
    
    public Hitbox[] getHitbox(boolean facingRight, double x, double y, int action, int frame,  Player p){
        
            
        
        if(action == 1){
            if(frame < 10){
                Hitbox[] Harr = new Hitbox[1];
                    Harr[0]= new Hitbox(x+(p.character.Width/2),y-40,30,270,30,p);
                    return Harr;
            }
        }
        if(action == 2){
            if(frame < 10){
                
            }
        }
        if(action == 3){
            if(frame < 10){
                
            }
        }
        if(action == 4){
            if(frame < 10){
                
            }
        }
        if(action == 5){
            if(frame < 10){
                
            }
        }
        if(action == 6){
            if(frame < 10){
                
            }
        }
        if(action == 7){
            if(frame < 10){
                
            }
        }
        if(action == 8){
            if(frame < 10){
                
            }
        }
        if(action == 9){
            if(frame < 10){
                
            }
        }
        if(action == 10){
            if(frame < 10){
                
            }
        }
        
        
        return null;
    }
    
    public Character() {
        HitBoxes[0] = new Hitbox(0, 0, 10, 0, 0);
        HitBoxes[1] = new Hitbox(0, 0, 10, 0, 0);
    }
}
