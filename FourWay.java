import javax.swing.JFrame;
import java.awt.EventQueue;

public class FourWay extends JFrame
{
   public FourWay()
   {
      initUI();
   }
   
   public void initUI()
   {
      add(new GameBoard());
   
      setResizable(false);
      pack();
      
      setTitle("4 Way");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable(){
         public void run()
         {
            FourWay fourWay = new FourWay();
            fourWay.setVisible(true);
         }
      }
      );
   }
}