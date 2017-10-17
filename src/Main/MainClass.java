package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainClass extends JPanel implements MouseListener, ActionListener{

    public final int SCREENWIDTH = 1024;
    public final int SCREENHEIGHT = 768;
    public final int REFRESHRATE = 16;
    public int[][] bingoNums = new int[5][5];
    public JButton button;
    public ArrayList<Integer> calledNums = new ArrayList<Integer>();
    public Timer mTimer;
    public JFrame frame;
    public boolean won = false;

    public static void main(String... args){
        MainClass MC = new MainClass();
    }


    public MainClass(){
        button = new JButton("Roll Number");
        button.setActionCommand("roll");
        button.addActionListener(this);
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.add(button);
        frame = new JFrame("Memory Game!");
        frame.setSize(SCREENWIDTH,SCREENHEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        mTimer = new Timer(REFRESHRATE,this);
        mTimer.setActionCommand("update");
        populateArray(bingoNums);
        mTimer.start();

    }

    public void resetGame(){

    }

    public void populateArray(int[][] array){
       for(int i=0;i<bingoNums.length;i++){
           for(int j=0;j<bingoNums.length;j++){
               if(i==0){
                    array[i][j] = genNum(array[i],14,1);
               }else if(i==1){
                   array[i][j] = genNum(array[i],14,16);
               }else if(i==2){
                   array[i][j] = genNum(array[i],14,31);
               }else if(i==3){
                   array[i][j] = genNum(array[i],14,46);
               }else if(i==4){
                   array[i][j] = genNum(array[i],14,61);
               }
           }
       }
    }

    public int genNum(int[] array, int range, int min){
       int temp;
        do {
            temp = (int) Math.round(Math.random() * range + min);
        }while(temp==array[0] || temp==array[1] || temp==array[2] || temp==array[3] || temp==array[4]);
        return temp;
    }

    public boolean checkBingo(int[][] array){
        boolean temp = true;
        for(int i=0;i<bingoNums.length;i++){
            for(int j=0;j<bingoNums.length;j++){
               if(!calledNums.contains(array[i][j])){
                   temp = false;
               }
            }
        }
        return temp;
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
        for(int i=0;i<bingoNums.length;i++){
            for(int j=0;j<bingoNums.length;j++){
                g.drawString( new Integer(bingoNums[i][j]).toString(),100*i+180,100*j+220);
            }
        }
        if(won){
            g.drawString("WON",700,700);
        }
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
        if(e.getActionCommand().equals(button.getActionCommand())){
            int temp;
            do{
                temp = (int)Math.round(Math.random()*74+1);
            }while(calledNums.contains(temp));
            calledNums.add(temp);
        }else {
            if(checkBingo(bingoNums)){
                won = true;
            }
            frame.repaint();
        }
    }
}
