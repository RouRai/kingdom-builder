package custom;

import javax.swing.*;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslucentButton extends JButton {

   // switch this boolean onto true if you want to see the button itself
      Boolean bool = true;
      int ID;
      /**
       * only for Start Game Button
       * @param
       */
      public TranslucentButton (){
         setBorder(BorderFactory.createBevelBorder(10));
         //setBorder(null);
         setBorderPainted(bool);
         setContentAreaFilled(bool);
         setOpaque(bool);
      }
      public TranslucentButton (int id){
         setBorder(BorderFactory.createBevelBorder(10));
         //setBorder(null);
         setBorderPainted(bool);
         setContentAreaFilled(bool);
         setOpaque(bool);
         ID = id;
      }
      public int getID(){
         return ID;
      }

   @Override
   public String toString() {
      return "visibility: "+bool+ " "+ ID;
   }
}
