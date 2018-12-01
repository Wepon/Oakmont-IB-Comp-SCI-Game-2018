package com.mygdx.game;

/**
 *
 * @author colem_000
 */
class GStage {
    
    Ground [] Grounds = new Ground[1]; 
    public GStage(){
     for(int x = 0; x<1;x++){
         this.Grounds[x] = new Ground(80,80,50,300);
         System.out.println("New ground = " + this.Grounds[x]);
     }   
    }
}
