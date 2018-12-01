package com.mygdx.game;

/**
 *
 * @author colem_000
 */
public class World {
    public Player[] Players = null;
    int count = 0;
    public Hitbox[] PlayerHitBoxes = new Hitbox[26];
    public Hitbox[] MoveHitBoxes = new Hitbox[26];
    public GStage Stage = null;
    
    public World(Player[] p, GStage S){
        this.Players = p;
        this.Stage = S;
        this.Players[0].SetStage(S);
        for(int i = 0; i < p.length; i++){
            for(int j = 0; j < p[i].character.HitBoxes.length; j++){
                Players[i].character.HitBoxes[j].addPlayer(p[i]);
                Players[i].character.HitBoxes[j].moveHitbox((int) Players[i].x + (int)Players[i].character.Width/2, ((int) Players[i].y + (int)Players[i].character.Height/4) * (j + 1));
            }
        }
    }
   public void WorldStep(){
       for(int i = 0; i < Players.length; i++){
            for(int j = 0; j < Players[i].character.HitBoxes.length; j++){
                Players[i].character.HitBoxes[j].addPlayer(Players[i]);
                Players[i].character.HitBoxes[j].moveHitbox((int) Players[i].x + (int)Players[i].character.Width/2, ((int) Players[i].y + (int)Players[i].character.Height/4) * (j + 1));
            }
        }
       
        for (Player Player : this.Players) {
            for(int x = 0; x < Player.character.HitBoxes.length; x++){
                PlayerHitBoxes[count++] = Player.character.HitBoxes[x];
            }
        }
        count = 0;
        for (Player Player : this.Players) {
            Player.inputUpdate();
        }
       for(int x = 0; x < this.Players.length; x++){
           this.Players[x].playerAction();
       }
       
       CheckPhysicsCollisions();
       
       for(int x = 0; x < this.Players.length; x++){
           this.Players[x].physicsUpdate();
           this.Players[x].frame += 1;
       }
       
   } 
    public void CheckPhysicsCollisions(){
        for(Hitbox Player: PlayerHitBoxes ){
            for(Hitbox Move : MoveHitBoxes){
                if(Player != null && Move != null){
                    if(Player.hitboxCollision(Move) != null){
                        Player.player.addForce(Move.force, Move.angle);
                    }
                }
            }
        }
    }
    
}

