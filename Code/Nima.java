/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static com.mygdx.game.Character.backAirAnimation;
import static com.mygdx.game.Character.backSmashAnimation;
import static com.mygdx.game.Character.downAirAnimation;
import static com.mygdx.game.Character.downSmashAnimation;
import static com.mygdx.game.Character.forwardAirAnimation;
import static com.mygdx.game.Character.forwardSmashAnimation;
import static com.mygdx.game.Character.idleAnimation;
import static com.mygdx.game.Character.img;
import static com.mygdx.game.Character.jabAnimation;
import static com.mygdx.game.Character.jumpAnimation;
import static com.mygdx.game.Character.ledgeAnimation;
import static com.mygdx.game.Character.neutralAirAnimation;
import static com.mygdx.game.Character.tmp;
import static com.mygdx.game.Character.upAirAnimation;
import static com.mygdx.game.Character.upSmashAnimation;
import static com.mygdx.game.Character.walkAnimation;

/**
 *
 * @author User
 */
public class Nima extends Character {

    public Nima() {
        this.name = "Nima";
        this.imgWidth = 32;
        this.imgHeight = 42;
    }

    @Override
    public Sprite getAnimation(boolean facingRight, int action, float stateTime, Player p) {
        img = new Texture("sprites/" + this.name + ".png");
        //
        try {
            tmp = TextureRegion.split(img, this.imgWidth, this.imgHeight - 6); // all players are levitating 6 pixels above ground

            TextureRegion[] walk = new TextureRegion[8];
            int index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 4; j < 12; j++) {
                    walk[index++] = tmp[i][j];
                }
            }
            TextureRegion[] jump = new TextureRegion[8];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 20; j < 28; j++) {
                    jump[index++] = tmp[i][j];
                }
            }
            TextureRegion[] idle = new TextureRegion[4];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 4; j++) {
                    idle[index++] = tmp[i][j];
                }
            }
            TextureRegion[] forwardAir = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 33; j < 34; j++) {
                    forwardAir[index++] = tmp[i][j];
                }
            }
            TextureRegion[] downAir = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 35; j < 36; j++) {
                    downAir[index++] = tmp[i][j];
                }
            }
            TextureRegion[] neutralAir = new TextureRegion[10];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 36; j < 46; j++) {
                    neutralAir[index++] = tmp[i][j];
                }
            }
            TextureRegion[] upAir = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 32; j < 33; j++) {
                    upAir[index++] = tmp[i][j];
                }
            }
            TextureRegion[] backAir = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 34; j < 35; j++) {
                    backAir[index++] = tmp[i][j];
                }
            }
            TextureRegion[] forwardSmash = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 29; j < 30; j++) {
                    forwardSmash[index++] = tmp[i][j];
                }
            }
            TextureRegion[] backSmash = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 34; j < 35; j++) {
                    backSmash[index++] = tmp[i][j];
                }
            }
            TextureRegion[] upSmash = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 31; j < 32; j++) {
                    upSmash[index++] = tmp[i][j];
                }
            }
            TextureRegion[] downSmash = new TextureRegion[1];
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 30; j < 31; j++) {
                    downSmash[index++] = tmp[i][j];
                }
            }
            TextureRegion[] jab = new TextureRegion[1]; // needs to be fixed
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 29; j < 30; j++) {
                    jab[index++] = tmp[i][j];
                }
            }
            TextureRegion[] ledge = new TextureRegion[1]; // needs to be fixed
            index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 50; j < 51; j++) {
                    ledge[index++] = tmp[i][j];
                }
            }
            // interpret current animation being used
            walkAnimation = new Animation<TextureRegion>((float) Math.abs(.1f / p.vx), walk);
            jumpAnimation = new Animation<TextureRegion>(0.75f, jump);
            idleAnimation = new Animation<TextureRegion>(0.425f, idle);
            downAirAnimation = new Animation<TextureRegion>(0.425f, downAir);
            upAirAnimation = new Animation<TextureRegion>(0.425f, upAir);
            neutralAirAnimation = new Animation<TextureRegion>(0.425f, neutralAir);
            forwardSmashAnimation = new Animation<TextureRegion>(0.425f, forwardSmash);
            backSmashAnimation = new Animation<TextureRegion>(0.425f, backSmash);
            upSmashAnimation = new Animation<TextureRegion>(0.425f, upSmash);
            downSmashAnimation = new Animation<TextureRegion>(0.425f, downSmash);
            jabAnimation = new Animation<TextureRegion>(0.425f, jab);
            forwardAirAnimation = new Animation<TextureRegion>(0.425f, forwardAir);
            backAirAnimation = new Animation<TextureRegion>(0.425f, backAir);
            ledgeAnimation = new Animation<TextureRegion>(0.425f, ledge);
            //default sprite
            sprite = new Sprite(walkAnimation.getKeyFrame(0));

            if (Math.abs(p.ax) > .1 && p.jumpsLeft == p.Jumps) {
                sprite = new Sprite(walkAnimation.getKeyFrame(stateTime, true));
            }
            if (Math.abs(p.vy) > 0 && action == 0) {
                sprite = new Sprite(jumpAnimation.getKeyFrame(stateTime, false));
            }
            if (Math.abs(p.ax) <= .1 && Math.abs(p.vy) == 0) {
                sprite = new Sprite(idleAnimation.getKeyFrame(stateTime, true));
            }
            if (action == 1) {
                sprite = new Sprite(downAirAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 2) {
                sprite = new Sprite(forwardAirAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 3) {
                sprite = new Sprite(backAirAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 4) {
                sprite = new Sprite(upAirAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 5) {
                sprite = new Sprite(neutralAirAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 6) {
                sprite = new Sprite(downSmashAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 7) {
                sprite = new Sprite(forwardSmashAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 8) {
                sprite = new Sprite(backSmashAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 9) {
                sprite = new Sprite(upSmashAnimation.getKeyFrame(stateTime, false));
            }
            if (action == 10) {
                sprite = new Sprite(jabAnimation.getKeyFrame(stateTime, false));
            }
            if (p.onLedge) {
                sprite = new Sprite(ledgeAnimation.getKeyFrame(stateTime, true));
            }
            //interpret current player frame
            // auto flip sprite in correct direction

            sprite.flip(true, false); // enable if sprite initially faces right : disable if sprite initially faces left
            if (facingRight == true) {
                sprite.flip(true, false);
            }
        } catch (Exception e) {
            return sprite = new Sprite(img);
        }
        return sprite;
    }
}
