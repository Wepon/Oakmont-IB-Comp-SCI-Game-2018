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
    public boolean facingRight = false;
    // ledges
    public boolean onLedge = false;
    public boolean canGrabLedge = true;
    public int ledgeCooldown = 0;
    public int ledgeCooldownLength = 20;
    // health
    public double health = 0.0;
    public int stock = 3;
    // dont touch :P

    public Object(double x, double y, double MF, double MR, double a, double W, int Jump, double g, double width) {
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
        this.vx += this.ax;

        if (this.vy > this.MaxFall) {
            this.vy += this.g + this.ay;
        }

        if (passThrough() != -69 && this.vy < 0) {
            this.y = passThrough();
        } else {
            this.y += this.vy;
        }
        this.x += this.vx;

        if (this.jumpsLeft == this.Jumps && this.Grounded() != null) {
            this.vx = this.vx / 3;
        }
        if (this.Grounded() == null && this.onLedge == false) {
            this.g = this.savedg;
        }
        if (this.Grounded() != null && this.vy <= 0) {
            Ground Groundon = this.Grounded();
            this.y = Groundon.y + Groundon.H;
            this.vy = 0;
            this.ay = 0;
            this.jumpsLeft = this.Jumps;
        }
        this.g = this.savedg;
    }

    public double passThrough() {
        for (int x = 0; x < Math.abs(this.vy); x++) {
            for (Ground Grounds : this.Stage.Grounds) {
                if (Grounds.IsGrounded(this.x, this.y - x, this.width)) {
                    return Grounds.y + Grounds.H;
                }
            }
        }
        return -69;
    }

    public void playerInput(float input) {
        if (this.Grounded() != null) {
            if (input > 0) {
                this.facingRight = true;
            }
            if (input < 0) {
                this.facingRight = false;
            }
        }
        if (this.jumpsLeft == this.Jumps) {
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
        
        // System.out.println("Jumped");
    }

    public void addForce(float force, int angle) {
        double a = Math.toRadians((double) angle);
        this.vy = (Math.sin(a) * force / this.weight) * (this.health + .1) * 10;    // allows for the physics of moves to be applied to the body
        this.vx = (Math.cos(a) * force / this.weight) * (this.health + .1) * 10;
        this.health += force / 1000;
        this.g = this.savedg;
    }

    public void SetStage(GStage G) {
        this.Stage = G;
    }

    public Ground Grounded() {
        for (Ground Grounds : this.Stage.Grounds) {
            if (Grounds.IsGrounded(this.x, this.y, this.width)) {
                return Grounds;
            }
        }
        return null;
    }

}
