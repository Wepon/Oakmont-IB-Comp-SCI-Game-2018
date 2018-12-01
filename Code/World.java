package com.mygdx.game;

/**
 *
 * @author colem_000
 */
public class World {
    public Player[] Players = null;
    public int [][] PlayerHitBoxes = new int[4][3];
    public int [][] MoveHitBoxes = new int[4][6]; // player, x,y,size,Force,angle
    public GStage Stage = null;
    
    public World(Player[] p, GStage S){
        this.Players = p;
        this.Stage = S;
        this.Players[0].SetStage(S);
    }
   public void WorldStep(){
       for(int x = 0;x<this.Players.length;x++){
           this.Players[x].inputUpdate();
       }
       for(int x = 0;x<this.Players.length;x++){
           this.Players[x].playerAction();
       }
       
       CheckPhysicsCollisions();
       
       for(int x = 0;x<this.Players.length;x++){
           this.Players[x].physicsUpdate();
       }
       
       
       
       
   } 
    public void CheckPhysicsCollisions(){
        
    }
    
}
