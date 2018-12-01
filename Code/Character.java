package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 *
 * @author colem_000
 */
public class Character {
   int Jumps = 2;
   double MaxFall = -60;
   double MaxRun = 20;
   double ObjA = 4;
   double weight = 3;
   double g = -2;
   
   double Height = 8;
   double Width = 5;
   public Animation getAnimation(int a, String b, boolean c, boolean d){
       Texture img = new Texture("Character.png");
       Animation animation = new Animation(1f/30f,img);
       return animation;
} 
}
