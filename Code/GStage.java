package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.screenSize;
import java.awt.Toolkit;

/**
 *
 * @author colem_000
 */
class GStage {

    int numOfGrounds = 4;
    Ground[] Grounds = new Ground[numOfGrounds];
    public Hitbox[] Ledges = new Hitbox[numOfGrounds * 2];
    public int[][] RespawnLoc = new int[4][2];
    
    public GStage() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.Grounds[3] = new Ground((screenSize.width/2 - (1024/2)+375), 650, 1, 256, false);
        this.Grounds[2] = new Ground((screenSize.width/2 - (1024/2)+575), 475, 1, 256, false);
        this.Grounds[1] = new Ground((screenSize.width/2 - (1024/2)+175), 475, 1, 256, false);
        this.Grounds[0] = new Ground((screenSize.width/2 - (1024/2)), 300, 1, 1024, true);
        //
        this.RespawnLoc[0][0] = 600;
        this.RespawnLoc[0][1] = 300;
        this.RespawnLoc[1][0] = 500;
        this.RespawnLoc[1][1] = 300;
        this.RespawnLoc[2][0] = 800;
        this.RespawnLoc[2][1] = 475;
        this.RespawnLoc[3][0] = 1000;
        this.RespawnLoc[3][1] = 475;
        
        int ledgecount = 0;
        for (int i = 0; i < numOfGrounds; i++) {
            if(this.Grounds[i].hasLedge){
                Ledges[ledgecount] = new Hitbox(this.Grounds[i].x - 1, this.Grounds[i].y + this.Grounds[i].H, 50, 0, 0, null, 1);
                Ledges[ledgecount + 1] = new Hitbox(this.Grounds[i].x + this.Grounds[i].W + 1, this.Grounds[i].y + this.Grounds[i].H, 50, 0, 0, null, 2);
                ledgecount += 2;
            }
        }

    }
}
