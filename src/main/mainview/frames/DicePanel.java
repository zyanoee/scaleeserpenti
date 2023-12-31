package main.mainview.frames;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import entities.interfaces.Callback;


public class DicePanel extends JPanel {
    
    private BufferedImage[] diceFaces;
    private int face1Index;
    private int face2Index;

    public DicePanel(){
        try {
            diceFaces = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                String imagePath = "/assets/dices/face" + (i) + ".png";
                try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                    diceFaces[i] = ImageIO.read(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        face1Index = 1;
        face2Index = 0;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = getWidth() / 4; 


        g.drawImage(diceFaces[face1Index], getWidth()/4 - size/2, getHeight()/2 - size/2, size, size, null);
        g.drawImage(diceFaces[face2Index], getWidth()/2 + size/2,  getHeight()/2 - size/2, size, size, null);
    }

    public void showDice(int face1, int face2) {
       
        if (face1 >= 1 && face1 <= 6 && face2 <= 6) {
            face1Index = face1;
            face2Index = face2;
            repaint(); 
        } else {
            System.out.println("[DEBUG-DICEVIEW] Valori non validi per le facce del dado.");
        }
    }

    public void shuffleView(int face1, int face2, Callback callback){
        Random rng = new Random();
        Timer timer = new Timer(100, new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 10) {
                    face1Index = rng.nextInt(6) + 1;
                    if(face2!=0){face2Index = rng.nextInt(6) + 1;}
                    repaint();
                    count++;
                } else {
                    ((Timer) e.getSource()).stop();
                    showDice(face1, face2);
                    callback.onComplete();
                }
            }
        });
        timer.start();

    }
}
