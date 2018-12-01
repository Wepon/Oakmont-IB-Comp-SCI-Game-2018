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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Object;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGdxGame extends ApplicationAdapter implements ControllerListener {
    Player player1 = new Player(new Character(),80,90);
    GStage stage = new GStage();
    // Player player2 = new Player();
    // Player player3 = new Player();
    // Player player4 = new Player();
    Player[] Pe = {player1};
   
    World World  = new World(Pe , stage);
    SpriteBatch batch;
    Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Character.png");
        // controller listener
        ControllerListener listener = this;
        Controllers.addListener(listener);
        // player settings
        player1.addController(Controllers.getControllers().get(0));
//        player2.addController(Controllers.getControllers().get(1));
//        player3.addController(Controllers.getControllers().get(2));
//        player4.addController(Controllers.getControllers().get(3));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // update current inputs for all players
        World.WorldStep();
        // draw all players
        batch.draw(img, Math.round(World.Players[0].x), Math.round(World.Players[0].y), 50,50);
        System.out.println(World.Players[0].vy);
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
