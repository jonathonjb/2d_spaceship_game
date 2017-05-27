import java.awt.Rectangle;

public class Missile extends Objects
{
   private final int MISSILE_SPEED = 20;
   private int type; //0 = shooting right, 1 = shooting left, 2 = shooting up, 3 = shooting down
   
   public Missile(int _currentX, int _currentY, int _type)
   {
      super(_currentX, _currentY);
      initMissile();
      type = _type;
   }
   
   public void initMissile()
   {
      setImage("missile.gif");
      setImageDimension();
   }
   
   public void move()
   {
      if(type == 0)
         setCurrentX(getCurrentX() + MISSILE_SPEED);
      else if(type == 1)
         setCurrentX(getCurrentX() - MISSILE_SPEED);
      else if(type == 2)
         setCurrentY(getCurrentY() - MISSILE_SPEED);
      else if(type == 3)
         setCurrentY(getCurrentY() + MISSILE_SPEED);
      
      if((getCurrentX() + getWidth()) < 0 || getCurrentX() > getBoardWidth() ||
          (getCurrentY() + getHeight()) < 0 || getCurrentY() > getBoardHeight())
      {
         setIsVisible(false);
      }
   }
}