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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import static javafx.scene.text.Font.font;

public class MyGdxGame extends ApplicationAdapter implements ControllerListener {

    Player player1 = new Player(new Character(), 500, 200);
    Player player2 = new Player(new Character(), 700, 200);
    GStage stage = new GStage();
    Player[] Pe = {player1};
    //
    SpriteBatch spriteBatch;
    BitmapFont font;


    
    public World World = new World(Pe, stage);
    SpriteBatch batch;
    Texture img;
    //
    Texture circle;
    Sprite hitbox;
    //
    Texture floor;
    TextureRegion[] animationFrames;
    Animation animation;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // img = new Texture("penguinsolo.png");
        floor = new Texture("brick.png");
        circle = new Texture("hitbox.png");
        hitbox = new Sprite(circle);
        font = new BitmapFont();
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
        for (int i = 0; i < World.Stage.Grounds.length; i++) {
            for (int j = 0; j < World.Stage.Grounds[i].W / 67; j++) {
                batch.draw(floor, (int) World.Stage.Grounds[i].x + (j * 67), (int) World.Stage.Grounds[i].y - 67);
            }
        }
        //draw players
        batch.draw(World.Players[0].character.getAnimation(World.Players[0].facingRight, World.Players[0].currentAction, World.Players[0].actionFrame, World.Players[0]), Math.round(World.Players[0].x), Math.round(World.Players[0].y), (float) World.Players[0].character.Width, (float) World.Players[0].character.Height);
        // draw health
        for(int i = 0; i < World.Players.length; i++){
            font.draw(batch, "" + (float)(Math.round(World.Players[i].health*100f)), 67 + 67*i, 30);
        }
        // draw hitboxes
        for (int i = 0; i < World.MoveHitBoxes.length; i++) {
            if (World.MoveHitBoxes[i] != null) {
                batch.draw(hitbox, (int) World.MoveHitBoxes[i].x - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].y - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].r * 2, (float) World.MoveHitBoxes[i].r * 2);
            }
        }
        // draw player hitboxes
        for (int i = 0; i < World.PlayerHitBoxes.length; i++) {
            if (World.PlayerHitBoxes[i] != null) {
                batch.draw(hitbox, (int) World.PlayerHitBoxes[i].x - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].y - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].r * 2, (float) World.PlayerHitBoxes[i].r * 2);
            }
        }
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
