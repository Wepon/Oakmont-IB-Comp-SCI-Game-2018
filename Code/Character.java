package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 *
 * @author colem_000
 */
public class Character {
   int Jumps = 2;
   double MaxFall = -40;
   double MaxRun = 10;
   double ObjA = 4;
   double weight = 3;
   double g = -2;
   
   double Height = 18;
   double Width = 20;
   public Hitbox[] HitBoxes = new Hitbox[2];
   public Sprite getAnimation(boolean facingRight){
       Texture img = new Texture("penguinsolo.png");
       Sprite sprite = new Sprite(img);
       if(facingRight == true){
           sprite.flip(true, false);
       }
       // System.out.println(sprite);
       // Animation animation = new Animation(1f/14f,sprite);
       return sprite;
} 
   public Character(){
       HitBoxes[0] = new Hitbox(0, 0, 10, 0, 0);
       HitBoxes[1] = new Hitbox(0, 0, 10, 0, 0);
   }
}
