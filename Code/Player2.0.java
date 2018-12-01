package com.mygdx.game;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

/**
 * @author Dillon & Cole Marquard
 */
public class Player extends Object {

    public Player(Character c,int StartX, int StartY){
    super(StartX,StartY, c.MaxFall, c.MaxRun, c.ObjA,c.weight, c.Jumps,c.g,c.Width);
}
    
    
    Character character = null;
    String controllerType = null;
    Controller controller = null;
    //0 left analog X; 1 left analog Y; 2 right analog X; 3 right analog Y; 4 a; 5 b; 6 x; 7 y; 8 z; 9 right trigger; 10 left trigger; 11 d-pad; 12 start
    double[] input = new double[13];
    double heldJump = 0;
    //
    String directionL = null;
    String directionR = null;
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

    public void playerAction() {
        // horizontal movement
        float horizontal_input = (float) this.input[0];
        if (Math.abs(horizontal_input) < .1) {
            horizontal_input = 0;
        }
        playerInput(horizontal_input);
        // vertical movement
        //System.out.println(this.jumpsLeft);
        if ((this.input[6] == 1 || this.input[7] == 1) && this.jumpsLeft > 0 && this.heldJump == 0) {
            System.out.println("jump ed");
            jump(30);
            this.jumpsLeft--;
            this.heldJump = 1;
        }
        if(this.input[6] == 0 && this.input[7] == 0){
            this.heldJump = 0;
        }
        //interpret player action
        if(this.directionL == "S" && this.input[4] == 1 && this.jumpsLeft < 2){ // Down Air
            System.out.println("down air");
        }
    }

    public String getDirection(double x, double y){
        double angle = Math.atan2(y, x);
        angle = (Math.toDegrees(-angle)+180);
        //System.out.println(angle);
        if(x <= .1 && y <= .1 && x > -.1 && y > -.1){
            return "C"; // center
        }
        if(angle <= 292.5 && angle >= 247.5){
            return "N"; 
        }
        if(angle <= 112.5 && angle >= 67.5){
            return "S"; 
        }
        if(angle <= 202.5 && angle >= 157.5){
            return "E"; 
        }
        if(angle <= 22.5 || angle >= 337.5){
            return "W"; 
        }
        if(angle <= 247.5 && angle >= 202.5){
            return "NE"; 
        }
        if(angle <= 337.5 && angle >= 292.5){
            return "NW"; 
        }
        if(angle <= 157.5 && angle >= 112.5){
            return "SE"; 
        }
        if(angle >= 22.5 && angle <= 67.5){
            return "SW"; 
        }
        return "error with getDirection()";
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
