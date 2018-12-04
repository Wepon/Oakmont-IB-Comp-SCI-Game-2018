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

    public World(Player[] p, GStage S) {
        this.Players = p;
        this.Stage = S;
        this.Players[0].SetStage(S);

    }

    public void WorldStep() {
        CheckPhysicsCollisions();
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
}
