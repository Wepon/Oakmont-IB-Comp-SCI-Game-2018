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
    public Ground(int x, int y, int Height, int Weidth){
        this.x = x;
        this.y = y;
        this.H = Height;
        this.W = Weidth;
    }
    public boolean IsGrounded(double x , double y,double width){
        //checking
        x =  x+width/2;
        return(x<=this.x+this.W && x>= this.x && y>=this.y && y<=this.y+this.H);
    }
}
