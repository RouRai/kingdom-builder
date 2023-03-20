package panels;

import javax.swing.*;
import java.awt.*;

public class Testing extends JFrame{
   HexButtonBackup jbutton1;
   private Container win;
   private JButton button1;

   Testing (){
      win = getContentPane();
      win.setLayout(null);

      jbutton1 = new HexButtonBackup();
      jbutton1.setSize(20,20);
      jbutton1.setLocation(10,40);
      //jbutton1.addActionListener(this);
      win.add(jbutton1);


      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1480,700);
      setVisible(true);
      this.setTitle("Red and Black Tree Simulator - Jessica C.");


   }
   public static void main (String args[]){
      Testing a = new Testing();
   }


}
