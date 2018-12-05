package com.mygdx.game;

/**
 *
 * @author colem_000
 */
class GStage {

    int numOfGrounds = 2;
    Ground[] Grounds = new Ground[numOfGrounds];
    public Hitbox[] Ledges = new Hitbox[2];
    public int[] RespawnLoc = new int[2];
    public GStage() {
        this.Grounds[1] = new Ground(402, 335, 1, 268);
        this.Grounds[0] = new Ground(335, 134, 1, 938);
        this.RespawnLoc[0] = 500;
        this.RespawnLoc[1] = 200;
        int ledgecount = 0;
        for (int i = 0; i < 1; i++) {
            Ledges[ledgecount] = new Hitbox(this.Grounds[i].x, this.Grounds[i].y + this.Grounds[i].H, 25, 0, 0, null, 1);
            Ledges[ledgecount + 1] = new Hitbox(this.Grounds[i].x + this.Grounds[i].W, this.Grounds[i].y + this.Grounds[i].H, 25, 0, 0, null, 2);
            ledgecount += 2;
        }

    }
}
