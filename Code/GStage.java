package com.mygdx.game;

/**
 *
 * @author colem_000
 */
class GStage {

    int numOfGrounds = 3;
    Ground[] Grounds = new Ground[numOfGrounds];
    public Hitbox[] Ledges = new Hitbox[numOfGrounds * 2];
    public int[] RespawnLoc = new int[2];
    public GStage() {
        this.Grounds[2] = new Ground(402, 335, 1, 268, false);
        this.Grounds[1] = new Ground(804, 335, 1, 268, false);
        this.Grounds[0] = new Ground(335, 134, 1, 938, true);
        this.RespawnLoc[0] = 500;
        this.RespawnLoc[1] = 200;
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
