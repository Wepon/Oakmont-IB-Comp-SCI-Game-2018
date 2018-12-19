package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;

/*
 * @author colem_000
 */
public class World {

    public Hitbox[] GrabHitBoxes;
    public Player[] Players = null;
    int count = 0;
    public int MoveSize = 0;
    public Hitbox[] PlayerHitBoxes = new Hitbox[26];
    public Hitbox[] MoveHitBoxes = new Hitbox[100];
    public GStage Stage = null;

    public boolean runGame = true;
    public static MyGdxGame game;
    private int othersum;

    public World(Player[] p, GStage S, MyGdxGame game) {
        this.Players = p;
        this.Stage = S;
        for (Player P : this.Players) {
            P.SetStage(S);
        }
        //
        this.game = game;
        GrabHitBoxes = new Hitbox[this.Players.length];
        //System.out.println(this.Players.length);
    }

    public void WorldStep() {
        checkDeath();
        CheckPhysicsCollisions();
        checkLedgeGrab();

        for (int i = 0; i < MoveHitBoxes.length; i++) {
            if (MoveHitBoxes[i] != null) {
                if (MoveHitBoxes[i].velocity != true) {
                    MoveHitBoxes[i] = null;
                } else {
                    MoveHitBoxes[i].x += MoveHitBoxes[i].velocityNum;
                    if (MoveHitBoxes[i].x > 1800 || MoveHitBoxes[i].x < -400 || MoveHitBoxes[i].y < -400 || MoveHitBoxes[i].y > 1200) {
                        MoveHitBoxes[i].velocity = false;
                    }
                }
            }
        }

        MoveSize = 0;
        count = 0;

        for (int x = 0; x < this.Players.length; x++) {
            this.Players[x].physicsUpdate();
        }

        for (Player Player : this.Players) {
            Player.inputUpdate();
        }
        for (Player pself : this.Players) {
            this.othersum = 0;
            for (Player pcheck2 : this.Players) {
                if (pself.playernum != pcheck2.playernum && pcheck2.stock > -1) {
                    this.othersum += pcheck2.stock;
                }
            }
            if (pself.stock > 0 && this.othersum == 0 && this.game.gamestate == 1) {
                this.game.gamestate = 2;
                break;
            }
        }

        for (Player Player : this.Players) {
            if (Player.input[14] == 1) {

                for (Player p : this.Players) {
                    p.stock = 3;
                    p.x = 0;
                    p.y = 0;
                    this.game.gamestate = 0;
                }
            }
        }
        for (int x = 0; x < this.Players.length; x++) {
            addMove(this.Players[x].playerAction());
        }
        for (int i = 0; i < this.Players.length; i++) {
            Hitbox[] h = this.Players[i].character.playerHitbox(this.Players[i]);
            GrabHitBoxes[i] = this.Players[i].character.ledgeHitbox(this.Players[i]);
            for (int j = 0; j < h.length; j++) {
                PlayerHitBoxes[count++] = h[j];
            }
        }

    }

    public void CheckPhysicsCollisions() {
        for (Hitbox Player : PlayerHitBoxes) {
            for (Hitbox Move : MoveHitBoxes) {
                if (Player != null && Move != null) {
                    if (Player.hitboxCollision(Move) != null) {

                        Player.player.addForce(Move.force, Move.angle);
                        Player.player.canGrabLedge = false;
                        Player.player.onLedge = false;
                        Player.player.ledgeCooldown = Player.player.ledgeCooldownLength;
                        MyGdxGame.manager.get("Punch.mp3", Sound.class).play(1.0f);
                        // logic for ranged attacks // they will be removed the next world loop by not having a velocity
                        if (Move.velocity == true) {
                            Move.velocity = false; // removes the velocity from the hitbox
                        }
                        break;

                    }
                }
            }
        }
    }

    public void addMove(Hitbox[] h) {
        if (h == null) {
            return;
        }
        for (int x = 0; x < h.length; x++) {
            while (true) {
                if (MoveHitBoxes[MoveSize] == null) {
                    MoveHitBoxes[MoveSize] = h[x];
                    break;
                }
                MoveSize += 1;
            }

        }
    }

    public void checkLedgeGrab() {
        for (Hitbox h : this.GrabHitBoxes) {
            for (Hitbox h2 : this.Stage.Ledges) {

                if (h == null || h2 == null || (h2.player != null && h2.player != h.player)) {
                    continue;
                } else {
                    h2.addPlayer(null);
                }

                if (h.hitboxCollision(h2) != null) {
                    if (h.player.facingRight) {
                        h.player.x = h2.x - (4 * (h.player.character.Width / 6));
                        h.player.y = h2.y - h.player.character.Height;
                    }
                    if (h.player.facingRight != true) {
                        h.player.x = h2.x - (2 * (h.player.character.Width / 6));
                        h.player.y = h2.y - h.player.character.Height;
                    }
                    h.player.vy = 0;
                    h.player.vx = 0;
                    h.player.g = 0;
                    h2.addPlayer(h.player);
                    h.ledge = h2;
                    h.player.onLedge = true;
                    h.player.jumpsLeft = h.player.Jumps;
                }

            }
        }
    }

    public void checkDeath() {
        for (Player Player : Players) {
            if (Player.x > 1800 || Player.x < -400 || Player.y < -400 || Player.y > 1200) {
                Player.stock--;
                // Takes the xy coords of the Stage's respawn 
                Player.x = Stage.RespawnLoc[Player.playernum - 1][0]; // difference of one between array starting index and playernumber 
                Player.y = Stage.RespawnLoc[Player.playernum - 1][1];
                Player.vx = 0;
                Player.vy = 0;
                Player.ax = 0;
                Player.ay = 0;
                Player.health = 0;
            }
        }

    }

}
