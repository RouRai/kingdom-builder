package custom;

import javax.swing.*;
import javax.swing.BorderFactory;

import javax.swing.JButton;

public class TranslucentButton extends JButton {

   // switch this boolean onto true if you want to see the button itself
      Boolean bool = false;
      /**
       * only for Start Game Button
       * @param x
       */
      public TranslucentButton (int x, int y, int w, int h){
         setSize(x, y);
         setBorder(BorderFactory.createBevelBorder(10));
         //setBorder(null);
         setBorderPainted(bool);
         setContentAreaFilled(bool);
         setOpaque(bool);
      }

   }
