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

    boolean grounded = false;
    //These values are change able for each character
    double MaxFall = -100;
    double MaxRun = 20;
    double ObjA = 6;
    double weight = 4;
    double g = -4.8;

    public void physicsUpdate() {

        this.vy += this.g + this.ay;

        this.vx += this.ax;

        if (this.vy < this.MaxFall) {
            this.vy = this.MaxFall;
        }

        this.x += this.vx;
        this.y += this.vy;

        if (this.grounded) {
            this.vx = this.vx / 3;
        }

        if (this.y <= 0) {
            this.y = 0;
            this.vy = 0;
            this.ay = 0;
            this.grounded = true;
        } else {
            this.grounded = false;
        }
    }

    public void playerInput(float input) {
        if (this.grounded) {
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
        this.vy += force;
    }

    public void addForce(float force, float angle) {
        this.vy += Math.sin(angle) * force / this.weight;    // allows for the physics of moves to be applied to the body
        this.vx += Math.cos(angle) * force / this.weight;
    }
}
