import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Spaceship extends Objects
{
   int dx, dy;
   ArrayList<Missile> missiles;
   int frameCount = 0; //Can only shoot when frame count is at 40
   final int MAX_FRAME_COUNT = 100;
   private int lives;
   private final int speed = 10;
   private boolean autoShoot = false;

   public Spaceship(int _currentX, int _currentY, int _lives)
   {
      super(_currentX, _currentY);
      lives = _lives;
      
      initSpaceship();
      
      if(frameCount <= MAX_FRAME_COUNT)
         frameCount++;
   }
   
   public void initSpaceship()
   {
      missiles = new ArrayList<Missile>();
      setImage("spaceship.png");
      setImageDimension();
   }
   
   public void move()
   {
      setCurrentX(getCurrentX() + dx);
      setCurrentY(getCurrentY() + dy);
      
      int x = getCurrentX();
      int y = getCurrentY();
      if(getCurrentX() < 1)
         setCurrentX(1);
      if((getCurrentX() + getWidth()) > getBoardWidth())
         setCurrentX(getBoardWidth() - getWidth());
      if(getCurrentY() < 1)
         setCurrentY(1);
      if((getCurrentY() + getHeight()) > getBoardHeight())
         setCurrentY(getBoardHeight() - getHeight());
   }
   
   public void fire()
   {
      if(getIsVisible() == true && frameCount == MAX_FRAME_COUNT)
      {
         missiles.add(new Missile(getCurrentX(), (getCurrentY()), 0));//shooting right
         missiles.add(new Missile(getCurrentX(), (getCurrentY()), 1));//shooting left
         missiles.add(new Missile(getCurrentX(), (getCurrentY()), 2));//shooting up
         missiles.add(new Missile(getCurrentX(), (getCurrentY()), 3));//shooting down
         
         frameCount = 0;
      }
   }
   
   public ArrayList<Missile> getMissiles()
   {
      return missiles;
   }
   
   public void addFrameCount()
   {
      if(frameCount < MAX_FRAME_COUNT)
         frameCount++;
   }
   
   public void addALife()
   {
      lives++;
   }
   
   public void loseALife()
   {
      lives--;
   }
   
   public int getLives()
   {
      return lives;
   }
   
   public boolean getIfAutoShoot()
   {
      return autoShoot;
   }
     
   public void keyPressed(KeyEvent ke)
   {
      int key = ke.getKeyCode();
      
      
      if(key == KeyEvent.VK_SPACE)
         fire();
      if(key == KeyEvent.VK_UP || key == KeyEvent.VK_I)
         dy = speed * -1;
      if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_K)
         dy = speed;
      if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_J)
         dx = speed * -1;
      if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L)
         dx = speed;
      
      if(key == KeyEvent.VK_A)
      {
         if(autoShoot)
            autoShoot = false;
         else
            autoShoot = true;
      }
      if(key == KeyEvent.VK_ESCAPE)
         System.exit(1);
   }
   
   public void keyReleased(KeyEvent ke)
   {
      int key = ke.getKeyCode();
   
      if(key == KeyEvent.VK_UP || key == KeyEvent.VK_I)
         dy = 0;
      if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_K)
         dy = 0;
      if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_J)
         dx = 0;
      if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L)
         dx = 0;
   }
   
   public void keyTyped(KeyEvent ke)
   {
   }
}