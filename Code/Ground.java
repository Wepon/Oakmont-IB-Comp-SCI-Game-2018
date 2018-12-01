package com.mygdx.game;

/**
 *
 * @author colem_000
 */
public class Ground {
    public double x=0;
    public double y=0;
    public double H=5;
    public double W=5;
    public Ground(int x, int y, int Height, int Width){
        this.x = x;
        this.y = y;
        this.H = Height;
        this.W = Width;
    }
    public boolean IsGrounded(double x , double y, double width){
        // x = x + width / 2;
        
        return((x <= this.x + this.W && x >= this.x && y >= this.y && y <= this.y + this.H) || (x + width <= this.x + this.W && x + width >= this.x && y >= this.y && y <= this.y + this.H));
    }
}
