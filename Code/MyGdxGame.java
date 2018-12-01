package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Object;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGdxGame extends ApplicationAdapter implements ControllerListener {
    Player player1 = new Player(new Character(),500,200);
    GStage stage = new GStage();
    // Player player2 = new Player();
    // Player player3 = new Player();
    // Player player4 = new Player();
    Player[] Pe = {player1};
    
    public World World  = new World(Pe , stage);
    SpriteBatch batch;
    Texture img;
    Texture floor;
    TextureRegion[] animationFrames;
    Animation animation;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        // img = new Texture("penguinsolo.png");
        floor = new Texture("brick.png");
        // controller listener
        ControllerListener listener = this;
        Controllers.addListener(listener);
        // player settings
        player1.addController(Controllers.getControllers().get(0));
//        player2.addController(Controllers.getControllers().get(1));
//        player3.addController(Controllers.getControllers().get(2));
//        player4.addController(Controllers.getControllers().get(3));
        //
        
        
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // update current inputs for all players
        World.WorldStep();
        // draw all players
        for(int i = 0; i < World.Stage.Grounds.length; i++){
            for(int j = 0; j < World.Stage.Grounds[i].W / 67; j++){
                batch.draw(floor, (int)World.Stage.Grounds[i].x + (j * 67), (int)World.Stage.Grounds[i].y - 67);
            }
        }
        //batch.draw(floor, (int)World.Stage.Grounds[0].x, (int)World.Stage.Grounds[0].y);
        
        // Sprite sprite = new Sprite(img);
        // sprite.flip(false, false);
        System.out.println(World.PlayerHitBoxes[0].x);
        System.out.println(World.PlayerHitBoxes[0].y);
        batch.draw(World.Players[0].character.getAnimation(World.Players[0].facingRight), Math.round(World.Players[0].x), Math.round(World.Players[0].y), 50,50);
        Hitbox h = new Hitbox(650,72,100,90,50);
        World.MoveHitBoxes[0] = h;
        // System.out.println(World.Players[0].vy);
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

// ignore garbage below
    @Override
    public void connected(Controller controller) {
        return;
    }

    @Override
    public void disconnected(Controller controller) {
        return;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    //   @Override
    //    public void resize(int width, int height) {
     //           viewport.update(width, height, true);
     //           batch.setProjectionMatrix(camera.combined);
//}
}
