package com.mygdx.game;

/**
 * @author colem_000
 */
public class Ground {

    public double x = 0;
    public double y = 0;
    public double H = 5;
    public double W = 5;
    public boolean hasLedge;

    public Ground(int x, int y, int Height, int Width, boolean ledge) {
        this.x = x;
        this.y = y;
        this.H = Height;
        this.W = Width;
        this.hasLedge = ledge;
    }

    public boolean IsGrounded(double x, double y, double width) {
        return (x + 63 <= this.x + this.W && x + 63 >= this.x && y >= this.y && y <= this.y + this.H);
    }
}
