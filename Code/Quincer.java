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

    private TextureRegion[][] tmp;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> forwardAirAnimation;
    private Animation<TextureRegion> downAirAnimation;
    private Animation<TextureRegion> backAirAnimation;
    private Animation<TextureRegion> neutralAirAnimation;
    private Animation<TextureRegion> forwardSmashAnimation;
    private Animation<TextureRegion> backSmashAnimation;
    private Animation<TextureRegion> upSmashAnimation;
    private Animation<TextureRegion> downSmashAnimation;
    private Animation<TextureRegion> jabAnimation;
    
    public Quincer() {
        this.name = "Quincer";
        this.Height = 64;
        this.Width = 50;
    }
    
    @Override
    public Sprite getAnimation(boolean facingRight, int action, float stateTime, Player p) {
        batch = new SpriteBatch();
        img = new Texture(this.name + ".png");
        //
        tmp = TextureRegion.split(img, img.getWidth() / 25, img.getHeight());
        TextureRegion[] walk = new TextureRegion[18];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 18; j++) {
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
        walkAnimation = new Animation<TextureRegion>(0.05f, walk);
        jumpAnimation = new Animation<TextureRegion>(0.03f, jump);
        idleAnimation = new Animation<TextureRegion>(0.185f, idle);
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
}
