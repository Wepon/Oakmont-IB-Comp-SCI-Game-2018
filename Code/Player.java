package com.mygdx.game;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

/**
 * @author Dillon & Cole Marquard
 */
public class Player extends Object {

    public Player(Character c, int StartX, int StartY) {
        super(StartX, StartY, c.MaxFall, c.MaxRun, c.ObjA, c.weight, c.Jumps, c.g, c.Width);
        this.character = c;
    }

    public Character character = null;
    String controllerType = null;
    Controller controller = null;
    //0 left analog X; 1 left analog Y; 2 right analog X; 3 right analog Y; 4 a; 5 b; 6 x; 7 y; 8 z; 9 right trigger; 10 left trigger; 11 d-pad; 12 start
    double[] input = new double[13];
    double heldJump = 0;
    //
    String directionL = null;
    String directionR = null;
    int actionFrame = 0;
    // state of character action
    int currentAction;
    int idleFrame = 0;
    enum moveSet {

    }

    public String controllerTypeUpdate() {
        return controller.getName();
    }

    public void addController(Controller c) {
        this.controller = c;
        System.out.println(c);
    }

    public int booleanToInt(boolean bool) {
        if (bool == true) {
            return 1;
        }
        if (bool == false) {
            return 0;
        }
        return 0;
    }

    public int checkDPad() {
        if (controllerType.contains("Xbox")) {
            if (controller.getButton(12) == true || controller.getButton(13) == true || controller.getButton(14) == true || controller.getButton(15) == true) {
                return 1;
            } else {
                return 0;
            }
        }
        if (controllerType.contains("MAYFLASH")) {
            if (controller.getButton(12) == true || controller.getButton(13) == true || controller.getButton(14) == true || controller.getButton(15) == true) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public Hitbox[] playerAction() {
        
        // horizontal movement
        float horizontal_input = (float) this.input[0];
        if (Math.abs(horizontal_input) < .10) {
            horizontal_input = 0;
        }
        playerInput(horizontal_input);
        if (this.input[1] > .1 && this.onLedge && this.canGrabLedge || (this.directionL != "C" && this.onLedge)) {
            if(this.directionL == "N" && this.jumpsLeft > 0){
                jump(25);
                this.jumpsLeft--;
            }
            if (this.onLedge && this.canGrabLedge) {
                this.canGrabLedge = false;
                this.onLedge = false;
                this.ledgeCooldown = this.ledgeCooldownLength;
            }
        }
        if (this.ledgeCooldown > 0) {
            this.ledgeCooldown--;
        }
        if (this.ledgeCooldown == 0) {
            this.canGrabLedge = true;
        }
        if ((this.input[6] == 1 || this.input[7] == 1) && this.jumpsLeft > 0 && this.heldJump == 0) {
            jump(25);
            if (this.onLedge && this.canGrabLedge) {
                this.canGrabLedge = false;
                this.onLedge = false;
                this.ledgeCooldown = this.ledgeCooldownLength;
            }
            this.jumpsLeft--;
            this.heldJump = 1;
        }
        if (this.input[6] == 0 && this.input[7] == 0) {
            this.heldJump = 0;
        }
        if (this.onLedge == false) {
            //interpret player action
            if ((((this.directionL == "S" && this.input[4] == 1) || this.directionR == "S") && this.jumpsLeft < this.Jumps && this.actionFrame == 0) || (this.currentAction == 1 && this.jumpsLeft < this.Jumps)) { // Down Air
                // System.out.println("down air");
                this.currentAction = 1;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if (((((this.directionL == "E" && this.facingRight && this.input[4] == 1) || (this.directionL == "W" && this.facingRight != true && this.input[4] == 1)) || ((this.directionR == "W" && this.facingRight != true) || (this.directionR == "E" && this.facingRight))) && this.jumpsLeft < this.Jumps && this.actionFrame == 0) || (this.currentAction == 2 && this.jumpsLeft < this.Jumps)) { // Forward Air              
                // System.out.println("forward air");
                this.currentAction = 2;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);
            }
            if (((((this.directionL == "E" && this.facingRight != true && this.input[4] == 1) || (this.directionL == "W" && this.facingRight && this.input[4] == 1)) || ((this.directionR == "W" && this.facingRight) || (this.directionR == "E" && this.facingRight != true))) && this.jumpsLeft < this.Jumps && this.actionFrame == 0) || (this.currentAction == 3 && this.jumpsLeft < this.Jumps)) { // Back Air              
                // System.out.println("back air");
                this.currentAction = 3;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if ((((this.directionL == "N" && this.input[4] == 1) || this.directionR == "N") && this.jumpsLeft < this.Jumps && this.actionFrame == 0) || (this.currentAction == 4 && this.jumpsLeft < this.Jumps)) { // Up Air
                this.currentAction = 4;
                // System.out.println("up air");
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if ((this.input[4] == 1 && this.jumpsLeft < this.Jumps && this.actionFrame == 0) || (this.currentAction == 5 && this.jumpsLeft < this.Jumps)) { //Neutral Air
                this.currentAction = 5;
                // System.out.println("neutral air");
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            //
            if ((((this.directionL == "S" && this.input[4] == 1) || this.directionR == "S") && this.jumpsLeft == this.Jumps && this.actionFrame == 0) || (this.currentAction == 6 && this.jumpsLeft == this.Jumps)) { // Down Smash
                // System.out.println("down smash");
                this.currentAction = 6;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if (((((this.directionL == "E" && this.facingRight && this.input[4] == 1) || (this.directionL == "W" && this.facingRight != true && this.input[4] == 1)) || ((this.directionR == "W" && this.facingRight != true) || (this.directionR == "E" && this.facingRight))) && this.jumpsLeft == this.Jumps && this.actionFrame == 0) || (this.currentAction == 7 && this.jumpsLeft == this.Jumps)) { // Forward Smash              
                // System.out.println("forward smash");
                this.currentAction = 7;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);
            }

            if (((((this.directionL == "E" && this.facingRight != true && this.input[4] == 1) || (this.directionL == "W" && this.facingRight && this.input[4] == 1)) || ((this.directionR == "W" && this.facingRight) || (this.directionR == "E" && this.facingRight != true))) && this.jumpsLeft == this.Jumps && this.actionFrame == 0) || (this.currentAction == 8 && this.jumpsLeft == this.Jumps)) { // Back Smash              
                // System.out.println("back smash");
                this.currentAction = 8;
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if ((((this.directionL == "N" && this.input[4] == 1) || this.directionR == "N") && this.jumpsLeft == this.Jumps && this.actionFrame == 0) || (this.currentAction == 9 && this.jumpsLeft == this.Jumps)) { // Up Smash
                this.currentAction = 9;
                // System.out.println("up smash");
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);

            }
            if (this.input[4] == 1 || this.currentAction == 10) {
                this.currentAction = 10;
                // System.out.println("jab");
                this.actionFrame += 1;
                return character.getHitbox(this.facingRight, this.x, this.y, this.currentAction, this.actionFrame, this);
            }
        }
        resetFrameNum();
        this.idleFrame++;
        if(this.idleFrame == 4){
            this.idleFrame = 0;
        }
        
        
        
        return null;
    }

    public void resetFrameNum() {
        // System.out.println("reset");
        this.actionFrame = 0;
        this.currentAction = 0;
    }

    public String getDirection(double x, double y) {
        double angle = Math.atan2(y, x);
        angle = (Math.toDegrees(-angle) + 180);
        if (x <= .1 && y <= .1 && x > -.1 && y > -.1) {
            return "C"; // center
        }
        if (angle <= 292.5 && angle >= 247.5) {
            return "N";
        }
        if (angle <= 112.5 && angle >= 67.5) {
            return "S";
        }
        if (angle <= 202.5 && angle >= 157.5) {
            return "E";
        }
        if (angle <= 22.5 || angle >= 337.5) {
            return "W";
        }
        if (angle <= 247.5 && angle >= 202.5) {
            return "NE";
        }
        if (angle <= 337.5 && angle >= 292.5) {
            return "NW";
        }
        if (angle <= 157.5 && angle >= 112.5) {
            return "SE";
        }
        if (angle >= 22.5 && angle <= 67.5) {
            return "SW";
        }
        return null;
    }

    public void inputUpdate() {
        controllerType = controllerTypeUpdate();
        // only xbox controllers work for now
        if (controllerType.contains("Xbox")) {
            this.input[0] = controller.getAxis(1); // left x
            this.input[1] = controller.getAxis(0); // left y
            this.input[2] = controller.getAxis(3); // right x
            this.input[3] = controller.getAxis(2); // right y
            this.directionL = getDirection(this.input[0], this.input[1]);
            this.directionR = getDirection(this.input[2], this.input[3]);
            // System.out.println(this.directionR);
            this.input[4] = booleanToInt(controller.getButton(0)); //A
            this.input[5] = booleanToInt(controller.getButton(1));
            this.input[6] = booleanToInt(controller.getButton(2));
            this.input[7] = booleanToInt(controller.getButton(3));
            this.input[8] = booleanToInt(controller.getButton(5));
            this.input[9] = booleanToInt(controller.getButton(7));
            this.input[10] = booleanToInt(controller.getButton(6));
            this.input[11] = checkDPad();// currently not responding
            this.input[12] = booleanToInt(controller.getButton(11));
            if (this.input[10] == 1) {
                this.x = Stage.RespawnLoc[0];
                this.y = Stage.RespawnLoc[1];
            }
        }
        if (controllerType.contains("MAYFLASH")) {
            // needs development
            this.input[0] = controller.getAxis(0); // left x
            this.input[1] = controller.getAxis(1); // left y
            this.input[2] = controller.getAxis(5); // right x
            this.input[3] = controller.getAxis(2); // right y
            this.directionL = getDirection(this.input[0], this.input[1]);
            this.directionR = getDirection(this.input[2], this.input[3]);
            // System.out.println(this.directionR);
            this.input[4] = booleanToInt(controller.getButton(1));
            this.input[5] = booleanToInt(controller.getButton(2));
            this.input[6] = booleanToInt(controller.getButton(0));
            this.input[7] = booleanToInt(controller.getButton(3));
            this.input[8] = booleanToInt(controller.getButton(7));
            this.input[9] = booleanToInt(controller.getButton(5));
            this.input[10] = booleanToInt(controller.getButton(4));
            this.input[11] = checkDPad();// currently not responding
            this.input[12] = booleanToInt(controller.getButton(11));

        }
    }
}
