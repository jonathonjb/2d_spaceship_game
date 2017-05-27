import java.util.Random;

public class Other extends Objects
{
   Random random = new Random();

   private final int speed = 1;
   private int type; // 0 = extra life ball
   private int direction; //0 = left, 1 = right, 2 = up, 3 = down
   private int oppositeSpeed = 1; 

   public Other(int _currentX, int _currentY)
   {
      super(_currentX, _currentY);
      initOther(random.nextInt(4));
   }
   
   public void initOther(int _direction)
   {
      type = random.nextInt(1);//number will increase as I add more objects to the program
      if(type == 0)
      {
         setImage("lifeBall.png");
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
   
   public void move()
   {
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