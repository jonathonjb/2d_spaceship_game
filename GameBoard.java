import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.ArrayList;

public class GameBoard extends JPanel implements ActionListener
{
   Timer timer;
   final int DELAY = 15, WAIT = 3000;
   int currentEnemyAmount = 5;
   int currentRound = 1;
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   int boardHeight = (int) screenSize.getHeight() - 106;
   int boardWidth = (int) screenSize.getWidth();
   boolean inGame = true;
   int xSpaceship = (boardWidth / 2);
   int ySpaceship = (boardHeight / 2);
   int gameSpeed = 0, enemiesKilled = 0;
   
   Spaceship ship;
   Other other;
   ArrayList<Enemy> enemies;
   
   Random random = new Random();
   
   ImageIcon ii;
   Image bgImage;
   
   public Image getCurrentImage()
   {
      return bgImage;
   }
   
   public GameBoard()
   {
      initBoard();
      java.net.URL url = getClass().getResource("background-image.jpg");
      ii = new ImageIcon(url);
      bgImage = ii.getImage();
   }
   
   public void initBoard()
   {
      addKeyListener(new TAdapter());
      setPreferredSize(new Dimension(boardWidth, boardHeight));
      setFocusable(true);
      
      ship = new Spaceship(xSpaceship, ySpaceship, 1);
      
      initEnemies(currentEnemyAmount);
         
      timer = new Timer(DELAY, this);
      timer.start();
   }
   
   public void initEnemies(int _currentEnemyAmount)
   {
      gameSpeed++;
      enemies = new ArrayList<Enemy>();
      
      for(int i = 0; i < _currentEnemyAmount; i++)
      {
         int randomNum = random.nextInt(4);
         int xPos = 0;
         int yPos = 0;
         if(randomNum == 0)//spawns from the right
         {
            xPos = boardWidth;
            yPos = random.nextInt(boardHeight);
         }
         else if(randomNum == 1)//spawns from the left
         {
            xPos = 0;
            yPos = random.nextInt(boardHeight);
         }
         else if(randomNum == 2)//spawns from the top
         {
            xPos = random.nextInt(boardWidth);
            yPos = 0;
         }
         else if(randomNum == 3)//spawns from the bottom
         {
            xPos = random.nextInt(boardWidth);
            yPos = boardHeight;
         }
         enemies.add(new Enemy(xPos, yPos, gameSpeed));
         initOther();
      }
   }
   
   public void initOther()
   {
      int randomNum = random.nextInt(4);
      int xPos = 0;
      int yPos = 0;
      if(randomNum == 0)//spawns from the right
      {
         xPos = boardWidth;
         yPos = random.nextInt(boardHeight);
      }
      else if(randomNum == 1)//spawns from the left
      {
         xPos = 0;
         yPos = random.nextInt(boardHeight);
      }
      else if(randomNum == 2)//spawns from the top
      {
         xPos = random.nextInt(boardWidth);
         yPos = 0;
      }
      else if(randomNum == 3)//spawns from the bottom
      {
         xPos = random.nextInt(boardWidth);
         yPos = boardHeight;
      }
         
      other = new Other(xPos, yPos);
      randomNum = random.nextInt(10);
      if(randomNum > 1)
      {
         other.setIsVisible(false);
      }
      else
      {
         other.setIsVisible(true);
      }
   }
   
   public void paintComponent(Graphics g)
   {
      this.setDoubleBuffered(true); 
      super.setDoubleBuffered(true);      super.paintComponent(g);
      
      if(inGame)
      {
         g.drawImage(bgImage, 0, 0, boardWidth, boardHeight, null);
         drawObjects(g);
      }
      else
      {
         drawGameOver(g);
      }
      
      Toolkit.getDefaultToolkit().sync();
   }
   
   private void drawGameOver(Graphics g)
   {
      java.net.URL url = getClass().getResource("gameOver.png");
      ii = new ImageIcon(url);
      bgImage = ii.getImage();
      g.drawImage(bgImage, 0, 0, boardWidth, boardHeight, null);
      
      int x = boardWidth / 2 - 420;
      int y = boardHeight / 2 - 150;
      
      Font small = new Font("Helvetica", Font.BOLD, 40);
      g.setFont(small);
      g.setColor(Color.RED);
      
      g.drawString("Oh darn, the human race has been destroyed.", x, y);
      
      y+= 50;
      g.drawString("Oh well, no biggie! Try again next time", x, y);
        
      try
      {
         Thread.sleep(500);
      }
      catch(InterruptedException ex1)
      {
         System.out.println(ex1.getMessage());
      }
         
         
      y += 50;
      if(currentRound > 1)
         g.drawString("You have survived for " + currentRound + " rounds", x, y); 
      else
      {
         g.drawString("You have survived... wait, this can't be right...", x, y);
         
         y += 50;
         g.drawString("Oh my god, you survived only one ", x, y);
         
         y += 50;
         g.drawString("freaking round? Haha, wow!", x, y);
      }
        
      y+= 50;
      if(enemiesKilled > 1 || enemiesKilled == 0)
         g.drawString("You have killed " + enemiesKilled + " enemies", x, y);
      else
         g.drawString("You have killed only " + enemiesKilled + " one enemy... Jesus Christ", x, y);
   }
   
   public void drawObjects(Graphics g)
   {
      if(ship.getIsVisible())
      {
         g.drawImage(ship.getImage(), ship.getCurrentX(), ship.getCurrentY(), this);
         ship.addFrameCount();
      }
      
      ArrayList<Missile> missiles = ship.getMissiles();
      
      for(Missile m : missiles)
      {
         if(m.getIsVisible())
         {
            g.drawImage(m.getImage(), m.getCurrentX(), m.getCurrentY(), this);
         }
      }
         
      for(Enemy e : enemies)
      {
         if(e.getIsVisible())
         {
            g.drawImage(e.getImage(), e.getCurrentX(), e.getCurrentY(), this);
         }
      }
      
      if(other.getIsVisible())
         g.drawImage(other.getImage(), other.getCurrentX(), other.getCurrentY(), this);
      
      g.setColor(Color.WHITE);
      g.drawString("Round " + currentRound, 20, 20);
      g.drawString("Enemies killed: " + enemiesKilled, 20, 40);
      g.drawString("Lives left: " + ship.getLives(), 20, 60);
      if(ship.getIfAutoShoot())
      {
         g.drawString("Auto shooting: ON", 20, 80);
      }
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      updateSpaceship();
      updateMissiles();
      updateEnemies();
      updateOther();
      
      checkCollusions();
      repaint();
   }
   
   public void updateSpaceship()
   {
      if(ship.getIsVisible())
      {
         ship.move();
         if(ship.getIfAutoShoot())
            ship.fire();
      }
      else
      {
         ship.setImage(null);
         ship.setImageDimension();
      }
   }
   
   private void updateMissiles()
   {
      ArrayList<Missile> missiles = ship.getMissiles();
      
      for(int i = 0; i < missiles.size(); i++)
      {
         Missile m = missiles.get(i);
         
         if(m.getIsVisible())
         {
            m.move();
         }
         else
         {
            missiles.remove(i);
            i--;
         }  
      }
   }
   
   private void updateEnemies()
   {
      if(enemies.isEmpty())
      {
         currentEnemyAmount += 5;
         currentRound++;
         initEnemies(currentEnemyAmount);
      }
      
      for(int i = 0; i < enemies.size(); i++)
      {
         if(enemies.get(i).getIsVisible())
         {
            enemies.get(i).move();
         }
         else
         {
            enemies.remove(i);
            i--;
         }
      }
   }
   
   public void updateOther()
   {
      if(other.getIsVisible())
      {
         other.move();
      }
   }
   
   public void checkCollusions()
   {
      Rectangle shipRectangle = ship.getBounds();
      Rectangle otherRectangle = other.getBounds();
      
      if(otherRectangle.intersects(shipRectangle))
      {
         if(other.getIsVisible() == true)
            ship.addALife();
         other.setIsVisible(false);
      }
      
      for(Enemy enemy : enemies)
      {
         Rectangle enemyRectangle = enemy.getBounds();
         
         if(enemyRectangle.intersects(shipRectangle))
         {
            ship.loseALife();
            if(ship.getLives() == 0)
            {
               ship.setIsVisible(false);
               inGame = false;
            }
            else
            {
               int lives = ship.getLives();
               ship = new Spaceship(xSpaceship, ySpaceship, lives);
            }
            
            enemy.loseLife();
            if(enemy.getLives() == 0)
            {
               enemy.setIsVisible(false);
            }
         }
         
         ArrayList<Missile> missiles = ship.getMissiles();
         for(Missile missile : missiles)//For some reason, missiles and enemies do not disappear when they collapse
         {
            Rectangle missileRectangle = missile.getBounds();
            if(enemyRectangle.intersects(missileRectangle))
            {
               missile.setIsVisible(false);
               enemy.loseLife();
               if(enemy.getLives() == 0)
               {
                  enemy.setIsVisible(false);
                  enemiesKilled++;
               }
            }
         }
      }
   }
   
   private class TAdapter extends KeyAdapter
   {
      @Override
         public void keyReleased(KeyEvent e)
      {
         ship.keyReleased(e);
      }
         
      @Override
         public void keyPressed(KeyEvent e)
      {
         ship.keyPressed(e);
      }
   }
}