package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainClass extends JPanel implements ActionListener{

    public final int SCREENWIDTH = 1536;
    public final int SCREENHEIGHT = 768;
    public final int REFRESHRATE = 16;
    public int[][] bingoNums = new int[5][5];
    public JButton button, yesButton, noButton;
    public ArrayList<Integer> calledNums = new ArrayList<Integer>();
    public Timer mTimer;
    public JFrame frame;
    public boolean won = false;

    public static void main(String... args){
        MainClass MC = new MainClass();
    }


    public MainClass(){
        this.setLayout(null);
        button = new JButton("Roll Number");
        button.setActionCommand("roll");
        button.addActionListener(this);
        button.setBounds(SCREENWIDTH/2-75,0,150,50);
        yesButton = new JButton("Yes");
        yesButton.setActionCommand("acceptReset");
        yesButton.addActionListener(this);
        yesButton.setVisible(false);
        yesButton.setBounds(900,550,150,50);
        noButton = new JButton("No");
        noButton.setActionCommand("declineReset");
        noButton.addActionListener(this);
        noButton.setVisible(false);
        noButton.setBounds(700,550,150,50);
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.add(button);
        this.add(yesButton);
        this.add(noButton);
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
        yesButton.setVisible(false);
        noButton.setVisible(false);
        for(int i=0;i<bingoNums.length;i++){
            for(int j=0;j<bingoNums.length;j++){
                bingoNums[i][j] = -1;
            }
        }
        populateArray(bingoNums);
        won = false;
        calledNums.clear();
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
        boolean temp = false;
        for(int i=0;i<bingoNums.length;i++){
            if(calledNums.contains(array[i][0]) && calledNums.contains(array[i][1]) && calledNums.contains(array[i][2]) && calledNums.contains(array[i][3]) && calledNums.contains(array[i][4])){
                temp = true;
            }
        }
        for(int j=0;j<bingoNums.length;j++){
            if(calledNums.contains(array[0][j]) && calledNums.contains(array[1][j]) && calledNums.contains(array[2][j]) && calledNums.contains(array[3][j]) &&calledNums.contains(array[4][j])){
                temp = true;
            }
        }
        if(calledNums.contains(array[0][0]) && calledNums.contains(array[1][1]) && calledNums.contains(array[2][2]) && calledNums.contains(array[3][3]) && calledNums.contains(array[4][4])){
            temp = true;
        }
        if(calledNums.contains(array[0][4]) && calledNums.contains(array[1][3]) && calledNums.contains(array[2][2]) && calledNums.contains(array[3][1]) && calledNums.contains(array[4][0])){
            temp = true;
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
                if(calledNums.contains(bingoNums[i][j])){
                    g.setColor(Color.green);
                }
                g.drawString( new Integer(bingoNums[i][j]).toString(),100*i+180,100*j+220);
                g.setColor(Color.WHITE);
            }
        }
        if(calledNums.size()>0) {
            g.drawString("Rolled Number: " + calledNums.get(calledNums.size()-1), 100, 700);
        }
        if(won){
            g.drawString("Play Again?",775,500);
            g.drawString("BINGO!",675,700);
            String[] nums = new String[calledNums.size()/13+1];
            for(int i=0;i<nums.length;i++){
                nums[i] = "";
            }
            int count =0;
            for(int i=0;i<calledNums.size();i++){
                if(i!=0) {
                    if (i % 13 == 0) {
                        count++;
                        nums[count] += "," + calledNums.get(i);
                    } else {
                        nums[count] += "," + calledNums.get(i);
                    }
                }else{
                    nums[count]+=calledNums.get(i);
                }
            }
            g.drawString("Called Numbers: ",700,100);
            for(int i=0;i<nums.length;i++){
                g.drawString(nums[i],700,160+i*60);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(button.getActionCommand())) {
            if(!won){
            int temp;
            do {
                temp = (int) Math.round(Math.random() * 74 + 1);
            } while (calledNums.contains(temp));
            calledNums.add(temp);
        }
        }else if(e.getActionCommand().equals(yesButton.getActionCommand())) {
            if(won){
                resetGame();
            }
        }else if(e.getActionCommand().equals(noButton.getActionCommand())) {
            if(won){
                System.exit(0);
            }
        }else {
            if(checkBingo(bingoNums)){
                won = true;
                yesButton.setVisible(true);
                noButton.setVisible(true);
                //I have too many chromosomes.
                frame.setSize(frame.getWidth()+1,frame.getHeight());
                frame.setSize(frame.getWidth()-1,frame.getHeight());
            }
            frame.repaint();
        }
    }
}
