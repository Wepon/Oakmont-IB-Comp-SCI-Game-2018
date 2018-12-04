package com.mygdx.game;

/**
 *
 * @author colem_000
 */
class GStage {

    int numOfGrounds = 2;
    Ground[] Grounds = new Ground[numOfGrounds];

    public GStage() {
        this.Grounds[1] = new Ground(335, 268, 1, 268);
        this.Grounds[0] = new Ground(335, 67, 1, 938);

    }
}
