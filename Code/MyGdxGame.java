package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter implements ControllerListener {

    Music epicMusic;

    Player player1 = new Player(new Character(), 0, 0);
    Player player2 = new Player(new Character(), 0, 0);
    Player player3 = new Player(new Character(), 0, 0);
    Player player4 = new Player(new Character(), 0, 0);
    
    GStage stage = new GStage();
    Player[] Pe = {player1, player2, player3, player4};
    //
    SpriteBatch spriteBatch;
    BitmapFont font;
    public World World = new World(Pe, stage, this);
    SpriteBatch batch;
    Texture img;
    //
    Texture circle;
    Sprite hitbox;
    Texture Backroud;
    Texture selectionscreen;
    //
    Texture floor;
    TextureRegion[] animationFrames;
    Animation animation;
    //
    public int gamestate = 0;
    int[] characters = new int[18];
    CharacterSelect t;
    public Hitbox[] CharactersHitboxes = new Hitbox[18];
    //
    private int controllerindex;
    public int numOfPlayers = 1;
    private Object fontstock;
    
    @Override
    public void create() {
        Backroud = new Texture("Mario_Bros_Map.jpg");
        epicMusic = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));
        epicMusic.setLooping(true);
        epicMusic.play();
        epicMusic.setVolume(50);
        batch = new SpriteBatch();
        // img = new Texture("penguinsolo.png");
        floor = new Texture("brick.png");
        circle = new Texture("hitbox.png");
        selectionscreen = new Texture("IMG.png");
        hitbox = new Sprite(circle);
        font = new BitmapFont();
        font.getData().setScale(5);
        
        t = new CharacterSelect();
        // controller listener
        ControllerListener listener = this;
        Controllers.addListener(listener);
        // make character select screen hitboxes
        for (int j = 0; j < 3; j++) {
            for (int i = 0 + (j * 6); i < this.CharactersHitboxes.length ; i++) {
                this.CharactersHitboxes[i] = new Hitbox(360 + ((i % 6) * 160), 460 - (j * 80), 40, 0, 0);
            }

        }
        // player settings
        
        // player1.addController(Controllers.getControllers().get(0));
        // player2.addController(Controllers.getControllers().get(1));
        // player3.addController(Controllers.getControllers().get(2));
        // player4.addController(Controllers.getControllers().get(3));
        //

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        if(this.numOfPlayers == 1){
            player1.addController(Controllers.getControllers().get(0));
        // 
        }
        if(this.numOfPlayers == 2){
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));
        
        }
        if(this.numOfPlayers == 3){
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));
            player3.addController(Controllers.getControllers().get(2));
        }
        if(this.numOfPlayers == 4){
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));
            player3.addController(Controllers.getControllers().get(2));
            player4.addController(Controllers.getControllers().get(3));
        }
        if (this.gamestate == 0) {
            this.numOfPlayers = getNumOfPlayers();
            batch.draw(selectionscreen, 0, 0, 1600, 800);
            for (Player p : Pe) {
                p.inputUpdate();
                t.selectMovement(p);
                if (p.input[13] == 1) {
                    this.World = new World(numOfPlayersArr(), stage, this);
                    for(Player p2: Pe){
                        for(int index = 0; index < this.CharactersHitboxes.length; index++){
                            if(new Hitbox(p2.x, p2.y, 30, 0, 0, p2, 0).hitboxCollision(this.CharactersHitboxes[index]) != null){
                                if(index == 0){                 
                                    p2.character = new Quincer();
                                }
                                if(index == 1){
                                    p2.character = new Odell();
                                }
                                if(index == 2){
                                    p2.character = new Dillon();
                                }
                                if(index == 3){
                                    p2.character = new Cole();
                                }
                                if(index == 4){
                                    p2.character = new Santi();
                                }
                                if(index == 5){
                                    p2.character = new Bradley();
                                }
                                if(index == 6){
                                    p2.character = new Arjun();
                                }
                                if(index == 7){
                                    p2.character = new Gavin();
                                }
                                if(index == 8){
                                    p2.character = new Noah();
                                }
                                if(index == 9){
                                    p2.character = new Matthew();
                                }
                                if(index == 10){
                                    p2.character = new Becca();
                                }
                                if(index == 11){
                                    p2.character = new Hunter();
                                }
                                if(index == 12){
                                    p2.character = new Navdip();
                                }
                                if(index == 13){
                                    p2.character = new Nickzod();
                                }
                                if(index == 14){
                                    p2.character = new Nima();
                                }
                                if(index == 15){
                                    p2.character = new MatthewC();
                                }
                                if(index == 16){
                                    p2.character = new Salmon();
                                }
                                if(index == 17){
                                    p2.character = new David();
                                }
                            }
                        }
                        
                    }
                    this.gamestate = 1;
                }
                // draw hitboxes
                for (int i = 0; i < this.CharactersHitboxes.length; i++) {
                    if (this.CharactersHitboxes[i] != null) {
                        batch.draw(hitbox, (int) this.CharactersHitboxes[i].x - (int) this.CharactersHitboxes[i].r, (int) this.CharactersHitboxes[i].y - (int) this.CharactersHitboxes[i].r, (int) this.CharactersHitboxes[i].r * 2, (float) this.CharactersHitboxes[i].r * 2);
                    }
                }
                batch.draw(t.getAnimation(p), (int) p.x, (int) p.y, (int) t.Width, (int) t.Height);
            }
        }
        // batch.draw(Backroud, 0, -50,1920,1080);
        // update current inputs for all players
        if (this.gamestate == 1) {
            World.WorldStep();
            // draw all players
            for (int i = 0; i < World.Stage.Grounds.length; i++) {
                for (int j = 0; j < World.Stage.Grounds[i].W / 67; j++) {
                    batch.draw(floor, (int) World.Stage.Grounds[i].x + (j * 67), (int) World.Stage.Grounds[i].y - 67);
                }
            }
            //draw players
            for (Player Player : World.Players) {
                batch.draw(Player.character.getAnimation(Player.facingRight, Player.currentAction, Player.actionFrame, Player), Math.round(Player.x), Math.round(Player.y), (float) Player.character.Width, (float) Player.character.Height);
            }
            // draw health
            for (int i = 0; i < World.Players.length; i++) {
                font.getData().setScale(5);
                font.setColor(0, 0, 0, 255);
                font.draw(batch, (float) (Math.round(World.Players[i].health * 100f)) + "%" , 100 + 300 * i, 750);
            }
            // draw stock (under health)
            for (int i = 0; i < World.Players.length; i++) {
                font.getData().setScale(3);
                font.setColor(0, 0, 0, 255);
                font.draw(batch, "Lives: " + World.Players[i].stock, 100 + 300 * i, 650);
            }
//            // draw hitboxes
//            for (int i = 0; i < World.MoveHitBoxes.length; i++) {
//                if (World.MoveHitBoxes[i] != null) {
//                    batch.draw(hitbox, (int) World.MoveHitBoxes[i].x - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].y - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].r * 2, (float) World.MoveHitBoxes[i].r * 2);
//                }
//            }
//            for (int i = 0; i < World.GrabHitBoxes.length; i++) {
//                if (World.GrabHitBoxes[i] != null) {
//                    batch.draw(hitbox, (int) World.GrabHitBoxes[i].x - (int) World.GrabHitBoxes[i].r, (int) World.GrabHitBoxes[i].y - (int) World.GrabHitBoxes[i].r, (int) World.GrabHitBoxes[i].r * 2, (float) World.GrabHitBoxes[i].r * 2);
//                }
//            }
//            for (int i = 0; i < World.Stage.Ledges.length; i++) {
//                if (World.Stage.Ledges[i] != null) {
//                    batch.draw(hitbox, (int) World.Stage.Ledges[i].x - (int) World.Stage.Ledges[i].r, (int) World.Stage.Ledges[i].y - (int) World.Stage.Ledges[i].r, (int) World.Stage.Ledges[i].r * 2, (float) World.Stage.Ledges[i].r * 2);
//                }
//            }
//            // draw player hitboxes
//            for (int i = 0; i < World.PlayerHitBoxes.length; i++) {
//                if (World.PlayerHitBoxes[i] != null) {
//                    batch.draw(hitbox, (int) World.PlayerHitBoxes[i].x - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].y - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].r * 2, (float) World.PlayerHitBoxes[i].r * 2);
//                }
//            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        floor.dispose();
        circle.dispose();
        epicMusic.dispose();
        Character.img.dispose();
    }

    public int getNumOfPlayers(){
        int num = 0;
        for(int i = 0; i < Controllers.getControllers().size; i++){
            if(Controllers.getControllers().get(i) != null){
                num++;
            }
        }
        return num;
    }
    public Player[] numOfPlayersArr(){
        Player[] arr = new Player[this.numOfPlayers];
        if(this.numOfPlayers == 1){
            arr[0] = player1;
        }
        if(this.numOfPlayers == 2){
            arr[0] = player1;
            arr[1] = player2;
        }
        if(this.numOfPlayers == 3){
            arr[0] = player1;
            arr[1] = player2;
            arr[2] = player3;
        }
        if(this.numOfPlayers == 4){
            arr[0] = player1;
            arr[1] = player2;
            arr[2] = player3;
            arr[3] = player4;
        }
        return arr;
    }
    
    public void pauseGame(World World) {
        World.runGame = false;
        font.draw(batch, "Please Reconnect Controller" , 800 , 400);
    }

    public void resumeGame(World World) {
        World.runGame = true;
    }
    
    
// ignore garbage below
    @Override
    public void connected(Controller controller) {
    }

    @Override
    public void disconnected(Controller controller) {
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
