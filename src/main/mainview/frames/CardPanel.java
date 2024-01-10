package main.mainview.frames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.State;
import entities.concreteclass.concreteEvents.FugaEvent;
import entities.concreteclass.concreteEvents.MollaEvent;
import entities.concreteclass.concreteEvents.RerollEvent;
import entities.concreteclass.concreteEvents.StopEvent;
import entities.interfaces.Callback;


public class CardPanel extends JPanel {

    private JLabel imageLabel;
    private BufferedImage visibleImage;
    private BufferedImage cardBack;
    private BufferedImage[] cardsFaces;
    private State currentState = State.SET_NULL;
    private Callback endEventCallback;


    public CardPanel(){
        try {
            cardsFaces = new BufferedImage[5];
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/molla.png")) {
                    cardsFaces[0] = ImageIO.read(inputStream);
                }
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/reroll.png")) {
                    cardsFaces[1] = ImageIO.read(inputStream);
                }
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/stop.png")) {
                    cardsFaces[2] = ImageIO.read(inputStream);
                }
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/locanda.png")) {
                    cardsFaces[3] = ImageIO.read(inputStream);
                }
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/fuga.png")) {
                    cardsFaces[4] = ImageIO.read(inputStream);
                }
            try (InputStream inputStream = getClass().getResourceAsStream("/assets/cards/back.png")) {
                    cardBack = ImageIO.read(inputStream);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel = new JLabel();
        add(imageLabel);
        imageLabel.setVisible(false);
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(visibleImage!=null){
            imageLabel.setVisible(true);
        } else {
            imageLabel.setVisible(false);
        }
    }

    public void showDeck(Callback callback){
        endEventCallback = callback;
        currentState = State.SHOW_DECK;
        visibleImage = cardBack;
        Image scaledImage = visibleImage.getScaledInstance(visibleImage.getWidth()/3, visibleImage.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        repaint();
    }



    public void showCard(MollaEvent ev){
        currentState = State.SHOW_CARD;
        visibleImage = cardsFaces[0];
        Image scaledImage = visibleImage.getScaledInstance(visibleImage.getWidth()/3, visibleImage.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        repaint();
    }

    public void showCard(RerollEvent ev){
        currentState = State.SHOW_CARD;
        visibleImage = cardsFaces[1];
        Image scaledImage = visibleImage.getScaledInstance(visibleImage.getWidth()/3, visibleImage.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        repaint();
    }

    public void showCard(StopEvent ev){
        currentState = State.SHOW_CARD;
        visibleImage = ev.getTurns()==1 ? cardsFaces[2] : cardsFaces[3];
        Image scaledImage = visibleImage.getScaledInstance(visibleImage.getWidth()/3, visibleImage.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        repaint();
    }

    public void showCard(FugaEvent ev){
        currentState = State.SHOW_CARD;
        visibleImage = cardsFaces[4];
        Image scaledImage = visibleImage.getScaledInstance(visibleImage.getWidth()/3, visibleImage.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        repaint();
    }

    public void setNull(){
        currentState = State.SET_NULL;
        visibleImage = null;
        imageLabel.setIcon(null);
        repaint();
    }

    public JLabel getImageLabel(){
        return this.imageLabel;
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public Callback getEventCallback(){
        return this.endEventCallback;
    }

    
}

 
