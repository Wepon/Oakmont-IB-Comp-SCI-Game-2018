package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import java.awt.Dimension;
import java.awt.Toolkit;

public class MyGdxGame extends ApplicationAdapter implements ControllerListener {

    public static Dimension screenSize;

    Music epicMusic;

    Player player1 = new Player(new Character(), 0, 0, 1);
    Player player2 = new Player(new Character(), 0, 0, 2);
    Player player3 = new Player(new Character(), 0, 0, 3);
    Player player4 = new Player(new Character(), 0, 0, 4);

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
    TextureRegion[][] headsArr;
    Texture heads;
    //
    Texture floor;
    TextureRegion[][] tmp;
    Animation animation;
    //
    static int Keyboard = 0;
    public static int numOfPlayers = 0;
    //
    public int gamestate = 0;
    int[] characters = new int[19];
    CharacterSelect t;
    public Hitbox[] CharactersHitboxes = new Hitbox[19];
    //
    private int controllerindex;

    private Object fontstock;
    public int bgindex = 0;
    //
    public Animation<TextureRegion> bgAnimation;
    public float stateTime;
    public int NUMOFSTOCKS = 3;
    public Music epicSelectionMusic;
    public static AssetManager manager;
    public Texture fireball;
    private Sprite fireballsprite;

    @Override
    public void create() {
        
        
        
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Backroud = new Texture("FirstMap.png");
        epicMusic = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));
        epicSelectionMusic = Gdx.audio.newMusic(Gdx.files.internal("titlescreen.mp3"));
        epicMusic.setLooping(true);
        epicSelectionMusic.setLooping(true);
        manager = new AssetManager();

        manager.load("Punch.mp3", Sound.class);
        manager.load("Jump.mp3", Sound.class);
        manager.load("Jump_Punch.mp3", Sound.class);
        manager.finishLoading();

        batch = new SpriteBatch();
        floor = new Texture("brick.png");
        circle = new Texture("hitbox.png");
        selectionscreen = new Texture("IMG.png");
        hitbox = new Sprite(circle);
        font = new BitmapFont();
        font.getData().setScale(5);
        // Background Animation Frame Start
        tmp = TextureRegion.split(Backroud, Backroud.getWidth() / 2, Backroud.getHeight() / 4);
        
        fireball = new Texture("sprites/fireball.png");
        fireballsprite = new Sprite(fireball);

        TextureRegion[] bgFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                bgFrames[index++] = tmp[i][j];
            }
        }
        bgAnimation = new Animation<TextureRegion>(0.13f, bgFrames);
        stateTime = 0f;
        // Background Animation Frame End
        //Character Select
        t = new CharacterSelect();
        // controller listener
        ControllerListener listener = this;
        Controllers.addListener(listener);
        // make character select screen hitboxes
        this.CharactersHitboxes[0] = new Hitbox(1.75 * (this.screenSize.width / 6), 4.3 * (this.screenSize.height / 5), 100, 0, 0);
        for (int j = 0; j < 3; j++) {
            for (int i = (j * 6); i < this.CharactersHitboxes.length - 1; i++) {
                this.CharactersHitboxes[i + 1] = new Hitbox((2.75 * this.screenSize.width / 12) + ((i % 6) * (1.2 * this.screenSize.width / 12)), (3.5 * this.screenSize.height / 6) - (j * (.65 * this.screenSize.height / 6)), 55, 0, 0);
            }
        }
        heads = new Texture("heads.png");
        headsArr = TextureRegion.split(heads, 12, 12);
        // player settings
        // player1.addController(Controllers.getControllers().get(0));
        // player2.addController(Controllers.getControllers().get(1));
        // player3.addController(Controllers.getControllers().get(2));
        // player4.addController(Controllers.getControllers().get(3));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        epicMusic.pause();
        if (this.numOfPlayers == 1) {
            player1.addController(Controllers.getControllers().get(0));
            // 
        }
        if (this.numOfPlayers == 2) {
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));

        }
        if (this.numOfPlayers == 3) {
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));
            player3.addController(Controllers.getControllers().get(2));
        }
        if (this.numOfPlayers == 4) {
            player1.addController(Controllers.getControllers().get(0));
            player2.addController(Controllers.getControllers().get(1));
            player3.addController(Controllers.getControllers().get(2));
            player4.addController(Controllers.getControllers().get(3));
        }
        if (this.gamestate == 0) {
            epicSelectionMusic.play();
            
            batch.draw(selectionscreen, 0, 0, screenSize.width, screenSize.height);
            int ind = 1;
            for (int i = 1; i < this.CharactersHitboxes.length; i++) {
                batch.draw(headsArr[0][ind++], (float) this.CharactersHitboxes[i].x - 50, (float) this.CharactersHitboxes[i].y - 50, 100, 100);
            }
            int count = 0;
            for (Player p : Pe) {
                if (count++ > numOfPlayersArr().length - 1) {
                    break;
                }

                p.inputUpdate();
                t.selectMovement(p);
                batch.draw(t.getAnimation(p), (int) (p.x - (t.Width / 2)), (int) (p.y - (t.Height / 2)), (int) t.Width, (int) t.Height);
                if (p.input[13] == 1) {
                    // p.input[13] = 0;
                    p.stock = this.NUMOFSTOCKS;
                    this.World = new World(numOfPlayersArr(), stage, this);
                    for (Player p2 : Pe) {
                        for (int index = 0; index < this.CharactersHitboxes.length; index++) {
                            if (new Hitbox(p2.x, p2.y, 30, 0, 0, p2, 0).hitboxCollision(this.CharactersHitboxes[index]) != null) {
                                if (index == 0) {
                                    p2.giveCharacter(new Quincer());
                                    break;
                                }
                                if (index == 1) {
                                    p2.giveCharacter(new Odell());
                                    break;
                                }
                                if (index == 2) {
                                    p2.giveCharacter(new Dillon());
                                    break;
                                }
                                if (index == 3) {
                                    p2.giveCharacter(new Cole());
                                    break;
                                }
                                if (index == 4) {
                                    p2.giveCharacter(new Santi());
                                    break;
                                }
                                if (index == 5) {
                                    p2.giveCharacter(new Bradley());
                                    break;
                                }
                                if (index == 6) {
                                    p2.giveCharacter(new Arjun());
                                    break;
                                }
                                if (index == 7) {
                                    p2.giveCharacter(new Gavin());
                                    break;
                                }
                                if (index == 8) {
                                    p2.giveCharacter(new Noah());
                                    break;
                                }
                                if (index == 9) {
                                    p2.giveCharacter(new Matthew());
                                    break;
                                }
                                if (index == 10) {
                                    p2.giveCharacter(new Becca());
                                    break;
                                }
                                if (index == 11) {
                                    p2.giveCharacter(new Hunter());
                                    break;
                                }
                                if (index == 12) {
                                    p2.giveCharacter(new Navdip());
                                    break;
                                }
                                if (index == 13) {
                                    p2.giveCharacter(new Nickzad());
                                    break;
                                }
                                if (index == 14) {
                                    p2.giveCharacter(new Nima());
                                    break;
                                }
                                if (index == 15) {
                                    p2.giveCharacter(new AndrewC());
                                    break;
                                }
                                if (index == 16) {
                                    p2.giveCharacter(new Salmon());
                                    break;
                                }
                                if (index == 17) {
                                    p2.giveCharacter(new David());
                                    break;
                                }
                                if (index == 18) {
                                    p2.giveCharacter(new Dominik());
                                    break;
                                }
                            }
                        }
                    }
                    this.gamestate = 1;
                    for (Player pl : this.World.Players) {
                        pl.stock = this.NUMOFSTOCKS;
                        pl.x = World.Stage.RespawnLoc[pl.playernum - 1][0];
                        pl.y = World.Stage.RespawnLoc[pl.playernum - 1][1];
                    }
                    epicSelectionMusic.stop();
                }

                // draw hitboxes
//                for (int i = 0; i < this.CharactersHitboxes.length; i++) {
//                    if (this.CharactersHitboxes[i] != null) {
//                        batch.draw(hitbox, (int) this.CharactersHitboxes[i].x - (int) this.CharactersHitboxes[i].r, (int) this.CharactersHitboxes[i].y - (int) this.CharactersHitboxes[i].r, (int) this.CharactersHitboxes[i].r * 2, (float) this.CharactersHitboxes[i].r * 2);
//                    }
//                }
            }
        }

        // update current inputs for all players
        if (this.gamestate == 1) {
            epicMusic.play();
            epicMusic.setVolume(25);
            World.WorldStep();
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(bgAnimation.getKeyFrame(stateTime, true), 0, 0, screenSize.width, screenSize.height);

            // draw all players
            // draw the world
            for (int i = 0; i < World.Stage.Grounds.length; i++) {
                for (int j = 0; j < World.Stage.Grounds[i].W / 256; j++) {
                    batch.draw(floor, (int) World.Stage.Grounds[i].x + (j * 256), (int) World.Stage.Grounds[i].y - 64, 256, 64);
                }
            }
            //draw players
            for (Player Player : World.Players) {
                batch.draw(Player.character.getAnimation(Player.facingRight, Player.currentAction, this.stateTime, Player), Math.round(Player.x), Math.round(Player.y) - Player.character.imgHeightBuffer, (float) Player.character.Width, (float) Player.character.Height);
            }
            // draw name
            for (int i = 0; i < World.Players.length; i++) {
                font.getData().setScale(3);
                font.setColor(255, 255, 255, 255);
                font.draw(batch, World.Players[i].character.name, 100 + 300 * i, 800);
            }
            // draw health
            for (int i = 0; i < World.Players.length; i++) {
                font.getData().setScale(5);
                font.setColor(255, 255, 255, 255);
                font.draw(batch, (float) (Math.round(World.Players[i].health * 100f)) + "%", 100 + 300 * i, 750);
            }
            // draw stock (under health)
            for (int i = 0; i < World.Players.length; i++) {
                font.getData().setScale(3);
                font.setColor(255, 255, 255, 255);
                font.draw(batch, "Lives: " + World.Players[i].stock, 100 + 300 * i, 675);
            }
            for (int i = 0; i < World.MoveHitBoxes.length; i++) {
                if (World.MoveHitBoxes[i] != null) {
                    if (World.MoveHitBoxes[i].velocity) {
                        if (World.MoveHitBoxes[i].velocityNum < 0) {
                            fireballsprite.flip(true, false);
                        }
                        batch.draw(fireballsprite, (int) World.MoveHitBoxes[i].x - (int) World.MoveHitBoxes[i].r, (int) World.MoveHitBoxes[i].y - (int) World.MoveHitBoxes[i].r, 128, 32);
                        if (World.MoveHitBoxes[i].velocityNum < 0) {
                            fireballsprite.flip(true, false);
                        }
                    }
                }
            }
//            // draw hitboxes debug
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
//                    if (World.PlayerHitBoxes[i].velocity != true) {
//                        batch.draw(hitbox, (int) World.PlayerHitBoxes[i].x - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].y - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].r * 2, (float) World.PlayerHitBoxes[i].r * 2);
//                    }
//                    if(World.PlayerHitBoxes[i].velocity){
//                        batch.draw(hitbox, (int) World.PlayerHitBoxes[i].x - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].y - (int) World.PlayerHitBoxes[i].r, (int) World.PlayerHitBoxes[i].r * 2, (float) World.PlayerHitBoxes[i].r * 2);
//                    }
//                }
//            }
        }
//
        if (this.gamestate == 2) {
            for (Player p : this.World.Players) {
                if (p.stock > 0) {
                    font.getData().setScale(5);
                    font.setColor(0, 0, 0, 255);
                    font.draw(batch, "Player " + p.playernum + " Wins", 3 * screenSize.width / 8, screenSize.height / 2);
                    break;
                }

            }
            font.getData().setScale(2);
            font.setColor(0, 0, 0, 255);
            font.draw(batch, "Press Back to return", screenSize.width / 2, screenSize.height / 6);
            for (Player play : this.World.Players) {
                play.inputUpdate();
                play.x = 0;
                play.y = 0;
                play.ax = 0;
                play.ay = 0;
                play.vx = 0;
                play.vy = 0;
                if (play.input[14] == 1) {
                    this.gamestate = 0;
                }
            }

        }
        // System.out.println(this.gamestate);
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
        Backroud.dispose();
        selectionscreen.dispose();
        font.dispose();
        CharacterSelect.img.dispose();
        epicSelectionMusic.dispose();
        manager.dispose();
        fireball.dispose();
        heads.dispose();

        //
    }

    public int getNumOfPlayers() {
        return Controllers.getControllers().size;
    }

    public Player[] numOfPlayersArr() {
        int Playerss = 0;
        Playerss = this.numOfPlayers + Keyboard;
        Player[] arr = new Player[Playerss];
        if (Playerss == 1) {
            arr[0] = player1;
        }
        if (Playerss == 2) {
            arr[0] = player1;
            arr[1] = player2;
        }
        if (Playerss == 3) {
            arr[0] = player1;
            arr[1] = player2;
            arr[2] = player3;
        }
        if (Playerss == 4) {
            arr[0] = player1;
            arr[1] = player2;
            arr[2] = player3;
            arr[3] = player4;
        }
        return arr;
    }

    public void pauseGame(World World) {
        World.runGame = false;
        font.draw(batch, "Please Reconnect Controller", 800, 400);
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
