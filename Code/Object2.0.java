package com.mygdx.game;

/**
 *
 * @author colem_000
 */
public class Object {

    double x = 0;
    double vx = 0;
    double ax = 0;

    double y = 0;
    double vy = 0;
    double ay = 0;
    
    GStage Stage = null;
    
    double width;
    
    int jumpsLeft = 0;
    int Jumps = 2;
    //These values are change able for each character
    
    double MaxFall = -100;
    double MaxRun = 20;
    double ObjA = 4;
    double weight = 3;
    double g = -4.8;
    double savedg = -4.8;
    public Object(double x , double y, double MF, double MR,double a, double W,int Jump, double g, double width){
        this.x = x;
        this.y = y;
        this.MaxFall = MF;
        this.MaxRun = MR;
        this.ObjA = a;
        this.weight = W;
        this.Jumps = Jump;
        this.g = g;
        this.savedg = g;
        this.width = width;
        
        
        
        
    }

    public void physicsUpdate() {

        this.vy += this.g + this.ay;

        this.vx += this.ax;

        if (this.vy < this.MaxFall) {
            this.vy = this.MaxFall;
        }

        this.x += this.vx;
        this.y += this.vy;

        if (this.jumpsLeft == this.Jumps) {
            this.vx = this.vx / 3;
        }
         if (this.Grounded() == null){
                this.g = this.savedg;
                System.out.println("Not Grounded");
            }
        if (this.Grounded()!=null && this.vy <= 0) {
            //System.out.println("Got Here and is grounded");
            Ground Groundon = this.Grounded();
            this.y = Groundon.y+Groundon.H;
            this.vy = 0;
            this.ay = 0;
            this.jumpsLeft = this.Jumps;
            this.g = 0;
        }
           
        
    }

    public void playerInput(float input) {
        if (this.jumpsLeft == 2) {
            this.ax = input * this.ObjA;
        } else {
            this.ax = input * this.ObjA / 3; // friction could be adjusted (only on ground)
        }

        if (this.ax + this.vx > this.MaxRun && this.ax > 0) {
            if (this.vx < this.MaxRun) {
                this.ax = this.MaxRun - this.vx;       // acceleration smoothing
            } else {
                this.ax = 0;   // if you are already at max speed, dont accelerate more in the same direction
            }
        }
        if (this.ax + this.vx < -this.MaxRun && this.ax < 0) {
            if (this.vx > -this.MaxRun) {
                this.ax = -this.MaxRun - this.vx;
            } else {
                this.ax = 0;
            }
        }

    }

    public void jump(float force) {
        this.vy = force;
        this.g = this.savedg;
        System.out.println("Jumped");
    }

    public void addForce(float force, float angle) {
        this.vy += Math.sin(angle) * force / this.weight;    // allows for the physics of moves to be applied to the body
        this.vx += Math.cos(angle) * force / this.weight;
        this.g = this.savedg;
    }
    public void SetStage(GStage G){
        this.Stage = G;
    }
    public Ground Grounded(){
        //System.out.println("Got here1");
        for(Ground Grounds:this.Stage.Grounds){
            //System.out.println("Got here2");
            //System.out.println(Grounds);
            if(Grounds.IsGrounded(this.x,this.y,this.width)){
                return Grounds;
            }
        }
        return null;
    }
            
}
