package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author colem_000
 */
public class CharacterSelect {

    double Height = 50;
    double Width = 50;
    //
    public static Texture img;
    Sprite sprite;
    
    public Sprite getAnimation(Player p) {
        img = new Texture("selectcoin.png");
        sprite = new Sprite(img);
        return sprite;
    }
    
    public void selectMovement(Player p){
        if(Math.abs(p.input[0])  > .1){ 
            p.x += p.input[0] * 10;
        }
        if(Math.abs(p.input[1]) > .1){
            p.y -= p.input[1] * 10;
        }
    }
}
