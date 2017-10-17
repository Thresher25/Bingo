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
    public int[][] bingoNums = new int[5][5];
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
        g.setFont(new Font("Times New Roman",Font.PLAIN,40));
        g.setColor(Color.WHITE);
        g.drawLine(150,150,150,650);
        g.drawLine(250,150,250,650);
        g.drawLine(350,150,350,650);
        g.drawLine(450,150,450,650);
        g.drawLine(550,150,550,650);
        g.drawLine(650,150,650,650);
        g.drawLine(150,150,650,150);
        g.drawLine(150,250,650,250);
        g.drawLine(150,350,650,350);
        g.drawLine(150,450,650,450);
        g.drawLine(150,550,650,550);
        g.drawLine(150,650,650,650);
        g.drawString("B",180,145);
        g.drawString("I",280,145);
        g.drawString("N",380,145);
        g.drawString("G",480,145);
        g.drawString("O",580,145);
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
