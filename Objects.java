import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Objects
{
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private int boardWidth = (int)screenSize.getWidth();
   private int boardHeight = (int)screenSize.getHeight() - 106;
   private int currentX, currentY, height, width;
   private boolean isVisible;
   Image image;

   public Objects(int _currentX, int _currentY)
   {
      currentX = _currentX;
      currentY = _currentY;
      isVisible = true;
   }
   
   public void setImageDimension()
   {
      height = image.getHeight(null);
      width = image.getWidth(null);
   }
   
   public int getBoardHeight()
   {
      return boardHeight;
   }
   
   public int getBoardWidth()
   {
      return boardWidth;
   }
   
   public int getHeight()
   {
      return height; 
   }
   
   public int getWidth()
   {
      return width;
   }
   
   public void setImage(String _imageName)
   {
      java.net.URL url = getClass().getResource(_imageName);
      ImageIcon i = new ImageIcon(url);
      image = i.getImage();
   }
   
   public Image getImage()
   {
      return image;
   }
   
   public void setCurrentX(int _currentX)
   {
      currentX = _currentX;
   }
   
   public int getCurrentX()
   {
      return currentX;
   }
   
   public void setCurrentY(int _currentY)
   {
      currentY = _currentY;
   }
   
   public int getCurrentY()
   {
      return currentY;
   }
   
   public void setIsVisible(boolean _isVisible)
   {
      isVisible = _isVisible;
   }
   
   public boolean getIsVisible()
   {
      return isVisible;
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle(currentX, currentY, width, height);
   }
}