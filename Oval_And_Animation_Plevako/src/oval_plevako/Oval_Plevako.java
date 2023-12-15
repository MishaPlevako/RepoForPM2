package oval_plevako;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;


/**
 *
 * @author Плевако Михаил Павлович
 */
public class Oval_Plevako extends JFrame {
    
    private static Oval_Plevako oval;

    public static void main(String[] args) {
        oval = new Oval_Plevako();
        oval.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        oval.setLocation(200, 50);
        oval.setSize(900, 600);
        oval.setResizable(false);
        OvalField oval_field = new OvalField();
        oval.add(oval_field);
        oval.setVisible(true);
    }
    
    public static void onRepaint(Graphics g){
        g.fillOval(10, 10, 200, 100);
    }
    
    public static class OvalField extends JPanel{
    
        @Override
        protected void paintComponent(Graphics g){
    
            super.paintComponent(g);
                onRepaint(g);
        }
    }
    
}
