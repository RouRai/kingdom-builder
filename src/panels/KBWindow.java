package panels;

import javax.swing.*;
import java.awt.*;

public class KBWindow extends JFrame {

   Container win;
   KBPanel pane;

   KBWindow (){
      win = getContentPane();
      win.setLayout(null);

      pane = new KBPanel ();
      pane.setLayout(null);
      pane.setBounds(0,0,1920, 1080);
      setResizable(false);
      pane.setBorder(BorderFactory.createLineBorder(Color.black));


      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1920,1080);
      setVisible(true);
      this.setTitle("Kingdom Builder.");
      win.add(pane);
   }

}
