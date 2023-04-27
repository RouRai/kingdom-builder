package custom;

import javax.swing.BorderFactory;

import javax.swing.JButton;

public class TranslucentButton extends JButton {

   // switch this boolean onto true if you want to see the button itself
      Boolean bool = true;
      int ID;
      /**
       * only for Start Game Button
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

   @Override
   public String toString() {
      return "visibility: "+bool+ " "+ ID;
   }
}
