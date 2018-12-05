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
    // Player player2 = new Player(new Character(), 700, 200);
    GStage stage = new GStage();
    Player[] Pe = {player1};//, player2};
    //
    SpriteBatch spriteBatch;
    BitmapFont font;

    public World World = new World(Pe, stage);
    SpriteBatch batch;
    Texture img;
    //
    Texture circle;
    Sprite hitbox;
    Texture Backroud;
    //
    Texture floor;
    TextureRegion[] animationFrames;
    Animation animation;

    @Override
    public void create() {
        // Backroud = new Texture("Mario_Bros_Map.jpg");
        batch = new SpriteBatch();
        // img = new Texture("penguinsolo.png");
        floor = new Texture("brick.png");
        circle = new Texture("hitbox.png");
        hitbox = new Sprite(circle);
        font = new BitmapFont();
        font.getData().setScale(5);
        // controller listener
        ControllerListener listener = this;
        Controllers.addListener(listener);
        // player settings
        player1.addController(Controllers.getControllers().get(0));
        // player2.addController(Controllers.getControllers().get(1));
//        player3.addController(Controllers.getControllers().get(2));
//        player4.addController(Controllers.getControllers().get(3));
        //

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // batch.draw(Backroud, 0, -50,1920,1080);
        // update current inputs for all players
        World.WorldStep();
        // draw all players
        for (int i = 0; i < World.Stage.Grounds.length; i++) {
            for (int j = 0; j < World.Stage.Grounds[i].W / 67; j++) {
                batch.draw(floor, (int) World.Stage.Grounds[i].x + (j * 67), (int) World.Stage.Grounds[i].y - 67);
            }
        }
        //draw players
        for (int i = 0; i < World.Players.length; i++) {
            batch.draw(World.Players[i].character.getAnimation(World.Players[i].facingRight, World.Players[i].currentAction, World.Players[i].actionFrame, World.Players[i]), Math.round(World.Players[i].x), Math.round(World.Players[i].y), (float) World.Players[i].character.Width, (float) World.Players[i].character.Height);
        }
        // draw health
        for (int i = 0; i < World.Players.length; i++) {
            font.draw(batch, "" + (float) (Math.round(World.Players[i].health * 100f)), 100 + 150 * i, 800);
        }
        // draw hitboxes
        for (int i = 0; i < World.MoveHitBoxes.length; i++) {
            if (World.MoveHitBoxes[i] != null) {
                batch.draw(hitbox, (int) World.MoveHitBoxes[i].x - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].y - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].r * 2, (float) World.MoveHitBoxes[i].r * 2);
            }
        }
//        for (int i = 0; i < World.GrabHitBoxes.length; i++) {
//            if (World.GrabHitBoxes[i] != null) {
//                batch.draw(hitbox, (int) World.GrabHitBoxes[i].x - (int) World.GrabHitBoxes[i].r, (int) World.GrabHitBoxes[i].y - (int) World.GrabHitBoxes[i].r, (int) World.GrabHitBoxes[i].r * 2, (float) World.GrabHitBoxes[i].r * 2);
//            }
//        }
//        for (int i = 0; i < World.Stage.Ledges.length; i++) {
//            if (World.Stage.Ledges[i] != null) {
//                batch.draw(hitbox, (int) World.Stage.Ledges[i].x - (int) World.Stage.Ledges[i].r, (int) World.Stage.Ledges[i].y - (int) World.Stage.Ledges[i].r, (int) World.Stage.Ledges[i].r * 2, (float) World.Stage.Ledges[i].r * 2);
//            }
//        }
//        // draw player hitboxes
//        for (int i = 0; i < World.PlayerHitBoxes.length; i++) {
//            if (World.PlayerHitBoxes[i] != null) {
//                batch.draw(hitbox, (int) World.PlayerHitBoxes[i].x - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].y - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].r * 2, (float) World.PlayerHitBoxes[i].r * 2);
//            }
//        }
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
