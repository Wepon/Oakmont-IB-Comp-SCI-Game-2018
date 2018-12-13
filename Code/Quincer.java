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
    
    public Quincer() {
        this.name = "Quincer";
        this.Jumps = 3;
        this.jumpEnergy = 13;
        this.g = -.4;
        this.ObjA = 3;
        this.imgHeight = 38;
        this.imgWidth = 26;
    }
}
