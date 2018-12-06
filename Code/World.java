package com.mygdx.game;

/**
 *
 * @author colem_000
 */
public class World {

    public Player[] Players = null;
    int count = 0;
    public int MoveSize = 0;
    public Hitbox[] PlayerHitBoxes = new Hitbox[26];
    public Hitbox[] MoveHitBoxes = new Hitbox[26];
    public GStage Stage = null;
    public Hitbox[] GrabHitBoxes = new Hitbox[4];
    public boolean runGame = true;
    
    public World(Player[] p, GStage S) {
        this.Players = p;
        this.Stage = S;
        for (Player P : this.Players) {
            P.SetStage(S);
        }
    }

    public void WorldStep() {
        if (this.runGame) {
            checkDeath();
            CheckPhysicsCollisions();
            checkLedgeGrab();
            for (int i = 0; i < MoveHitBoxes.length; i++) {
                MoveHitBoxes[i] = null;
            }

            MoveSize = 0;
            count = 0;

            for (int x = 0; x < this.Players.length; x++) {
                this.Players[x].physicsUpdate();
            }

            for (Player Player : this.Players) {
                Player.inputUpdate();

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

    }

    public void CheckPhysicsCollisions() {
        for (Hitbox Player : PlayerHitBoxes) {
            for (Hitbox Move : MoveHitBoxes) {
                if (Player != null && Move != null) {
                    if (Player.hitboxCollision(Move) != null) {
                        Player.player.addForce(Move.force, Move.angle);
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
            MoveHitBoxes[MoveSize] = h[x];
            MoveSize += 1;
        }
    }

    public void checkLedgeGrab() {
        for (Hitbox h : this.GrabHitBoxes) {
            for (Hitbox h2 : this.Stage.Ledges) {
                if (h == null || h2 == null || (h2.player != null && h.player != h2.player)) {
                    return;
                } else {
                    h2.addPlayer(null);
                }
                if (h.hitboxCollision(h2) != null) {
                    // System.out.println("grab ledge");
                    if (h.player.facingRight) {
                        h.player.x = h2.x - h.player.character.Width;
                        h.player.y = h2.y - h.player.character.Height;
                    }
                    if (h.player.facingRight != true) {
                        h.player.x = h2.x;
                        h.player.y = h2.y - h.player.character.Height;
                    }
                    h.player.vy = 0;
                    h.player.vx = 0;
                    h.player.g = 0;
                    h2.addPlayer(h.player);
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
                // make it choose from spawn point in the gstage class
                Player.x = Stage.RespawnLoc[0];
                Player.y = Stage.RespawnLoc[1];

                Player.vx = 0;
                Player.vy = 0;
                Player.ax = 0;
                Player.ay = 0;
                Player.health = 0;
            }
        }

    }

    
}
