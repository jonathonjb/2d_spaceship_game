import java.util.Random;
import java.awt.Rectangle;

public class Enemy extends Objects
{
   Random random = new Random();
   
   int dy; // no idea what this means... figure it out later
   private int speed;
   private int type; //0-8 = fast, small and destructable, 9 = slow, big and indestructable
   private int direction; //0 = left, 1 = right, 2 = up, 3 = down
   private int oppositeSpeed; //the enemy is going to its direction at it's normal speed and is going this (opposite) direction at this speed (ex: (normal speed) x = 40, opposite speed: 10)
   private int livesLeft;

   public Enemy(int _currentX, int _currentY, int _speed)
   {
      super(_currentX, _currentY);
      speed = _speed;
      
      initEnemy(random.nextInt(10), random.nextInt(4));//0-3 = small, fast, destructable enemy, 4 = big, slow, undestructable enemy
      
   }
   
   private void initEnemy(int _type, int _direction)
   {
      if(_type < 9)
      {
         setImage("bullet.gif");
         type = 1;
         livesLeft = 1;
      }
      else if(_type == 9)
      {
         setImage("spikeBall.gif");
         type = 2;
         livesLeft = 3;
      }
      
      direction = _direction;
      
      int randomNum = random.nextInt(6);
      if(randomNum == 0)
         oppositeSpeed = -3;
      else if(randomNum == 1)
         oppositeSpeed = -2;
      else if(randomNum == 2)
         oppositeSpeed = -1;
      else if(randomNum == 3)
         oppositeSpeed = 1;
      else if(randomNum == 4)
         oppositeSpeed = 2;
      else if(randomNum == 5)
         oppositeSpeed = 3;
      
      setImageDimension();
   }
   
   public int getLives()
   {
      return livesLeft;
   }
   
   public void loseLife()
   {
      livesLeft--;
   }
   
   public void move()
   {
      if(type < 9)
      {
         if(speed > 4)
         {
            speed = 4;
         }
      }
      else if(type == 9)
      {
         if(speed > 4)
         {
            speed = 2;
         }
         else
         {
            speed = (int)(speed / 2);
         }
      }
      
      if((getCurrentX() + getWidth()) < 0)//if the enemy object passes the left side of the screen, it will reappear at the right side of the screen
      {
         setCurrentX(getBoardWidth());
      }
      if(getCurrentX() > getBoardWidth())//if the enemy passes the right side of the screen, it will reappear at the left side of the screen
      {
         setCurrentX(0 - getWidth());
      }
      
      if((getCurrentY() + getHeight()) < 0)//if the enemy passes the top of the screen, it will reappear at the bottom of the screen
      {
         setCurrentY(getBoardHeight());
      }
      if((getCurrentY()) > getBoardHeight())//if the enemy passes the bottom of the screen, it will reappear at the top of the screen
      {
         setCurrentY(0 - getHeight());
      }
      
      if(type == 4)//DOESN'T MAKE THE SPIKE BALL SLOWER... FIX THIS LATER
      {
         speed = speed / 2;
      }
      
      
      
      if(direction == 0)//enemy is going left
      {
         setCurrentX(getCurrentX() - speed);
         setCurrentY(getCurrentY() + oppositeSpeed);
      }
      else if(direction == 1)//enemy is going right
      {
         setCurrentX(getCurrentX() + speed);
         setCurrentY(getCurrentY() + oppositeSpeed);
      }
      else if(direction == 2)//enemy is going up
      {
         setCurrentY(getCurrentY() - speed);
         setCurrentX(getCurrentX() + oppositeSpeed);
      }
      else if(direction == 3)//enemy is going down
      {
         setCurrentY(getCurrentY() + speed);
         setCurrentX(getCurrentX() + oppositeSpeed);
      }
   }
}