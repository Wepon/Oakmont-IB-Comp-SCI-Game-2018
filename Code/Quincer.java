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
import static com.mygdx.game.Character.img;

/**
 *
 * @author User
 */
public class Quincer extends Character {

//    private TextureRegion[][] tmp;
//    private Animation<TextureRegion> walkAnimation;
//    private Animation<TextureRegion> jumpAnimation;
//    private Animation<TextureRegion> idleAnimation;
//    private Animation<TextureRegion> forwardAirAnimation;
//    private Animation<TextureRegion> downAirAnimation;
//    private Animation<TextureRegion> backAirAnimation;
//    private Animation<TextureRegion> neutralAirAnimation;
//    private Animation<TextureRegion> forwardSmashAnimation;
//    private Animation<TextureRegion> backSmashAnimation;
//    private Animation<TextureRegion> upSmashAnimation;
//    private Animation<TextureRegion> downSmashAnimation;
//    private Animation<TextureRegion> jabAnimation;
    
    public Quincer() {
        this.name = "Quincer";
        this.Jumps = 3;
        this.jumpEnergy = 13;
        this.g = -.4;
        this.ObjA = 3;
    }
}
