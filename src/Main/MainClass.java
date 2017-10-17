package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainClass extends JPanel implements MouseListener, ActionListener{

    public final int SCREENWIDTH = 1024;
    public final int SCREENHEIGHT = 768;
    public final int REFRESHRATE = 16;
    public Timer mTimer;
    public JFrame frame;


    public static void main(String... args){
        MainClass MC = new MainClass();
    }


    public MainClass(){
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        frame = new JFrame("Memory Game!");
        frame.setSize(SCREENWIDTH,SCREENHEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        mTimer = new Timer(REFRESHRATE,this);
        mTimer.start();

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}
