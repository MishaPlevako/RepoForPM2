package Plevako_Animation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/* @author Плевако Михаил Павлович */

public class Plevako_Animation extends JFrame {
 
    private static Plevako_Animation animation;
    private static long last_frame_time;
    private static Image logo;
    private static Image snowflakes;
    private static Image tree;
    private static float drop_left = 20;
    private static float drop_top = 50;
    private static float drop_v = 200; 
    private static int score = 0; 

    public static void main(String[] args) throws IOException {
       logo = ImageIO.read(Plevako_Animation.class.getResourceAsStream("logo.png"));
       snowflakes = ImageIO.read(Plevako_Animation.class.getResourceAsStream("snowflakes.png"));
       tree = ImageIO.read(Plevako_Animation.class.getResourceAsStream("tree.png"));
       animation = new Plevako_Animation();
       animation.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       animation.setLocation(200, 50);
       animation.setSize(1300, 1200);
       animation.setResizable(false);
       last_frame_time = System.nanoTime();
       Plevako_Animation_Field animation_field = new Plevako_Animation_Field();
       animation_field.addMouseListener(new MouseAdapter() { 
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + tree.getWidth(null);
                float drop_bottom = drop_top + tree.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                
                if (is_drop) {
                    drop_top = -100; 
                    drop_left = (int) (Math.random() * (animation_field.getWidth() - tree.getWidth(null))); 
                    drop_v = drop_v + 10; 
                    score++; 
                    animation.setTitle("Score: " + score); 
                }
            }
        });

       animation.add(animation_field);
       animation.setVisible(true);
    }
    
    private static void onRepaint(Graphics g){
        long current_time = System.nanoTime();
        float delt_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top + drop_v * delt_time;
        g.drawImage(logo, -10, 150, null);
        g.drawImage(snowflakes, (int) drop_left, (int) drop_top, null);
        if(drop_top > animation.getHeight()) g.drawImage(tree, 400, 500, null);
    }
    
    private static class Plevako_Animation_Field extends JPanel{
    @Override 
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        onRepaint(g);
        repaint();
    }
} 
}
