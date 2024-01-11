package main.mainview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import config.configmodels.GameConfig;
import config.configview.GameBoardView;
import entities.State;
import entities.concreteclass.concreteEvents.FugaEvent;
import entities.concreteclass.concreteEvents.MollaEvent;
import entities.concreteclass.concreteEvents.RerollEvent;
import entities.concreteclass.concreteEvents.StopEvent;
import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameBoardInterface;
import main.mainmodels.Game;
import main.mainview.frames.CardPanel;
import main.mainview.frames.DicePanel;
import main.mainview.frames.EventPanel;
import main.mainview.frames.MainframeJFrame;
import main.mainview.frames.PawnsPanel;

public class GameView {

    private MainframeJFrame mainframe;
    private DicePanel dicePanel;
    private PawnsPanel pawnPanel;
    private CardPanel cardPanel;
    private EventPanel eventPanel;
    private GameBoardView gbview;

    public GameView(MainframeJFrame mainframe, GameBoardInterface board, GameConfig model, Game game){
        
            this.mainframe = mainframe;
            mainframe.getGameZonePanel().removeAll();
            mainframe.getDiceZonePanel().removeAll();
            mainframe.getEventZonePanel().removeAll();
            gbview = new GameBoardView(board);
            int dimX = board.getGridSizeX()*50;
            int dimY = board.getGridSizeY()*50;
            gbview.setPreferredSize(new Dimension(dimX,dimY));
            int biggerDimY = mainframe.getEventZonePanel().getHeight();
            if(board.getGridSizeY()-10>0){
                biggerDimY=biggerDimY+(board.getGridSizeY()-10)*50;
            }
            mainframe.setSize(new Dimension(dimX+320, biggerDimY+370));

            mainframe.getGameZonePanel().setLayout(new OverlayLayout(mainframe.getGameZonePanel()));
            mainframe.getDiceZonePanel().setLayout(new OverlayLayout(mainframe.getDiceZonePanel()));
            mainframe.getEventZonePanel().setLayout(new OverlayLayout(mainframe.getEventZonePanel()));
            
    
            pawnPanel = new PawnsPanel(board, game);
            dicePanel = new DicePanel();
            cardPanel = new CardPanel();
            eventPanel = new EventPanel();
            pawnPanel.setPreferredSize(new Dimension(dimX,dimY));
            dicePanel.setPreferredSize(new Dimension(mainframe.getDiceZonePanel().getWidth(), mainframe.getDiceZonePanel().getHeight()));
            eventPanel.setPreferredSize(new Dimension(mainframe.getEventZonePanel().getWidth()+20, mainframe.getEventZonePanel().getHeight()+20));
            cardPanel.setPreferredSize(new Dimension(dimX,dimY));
            mainframe.getGameZonePanel().add(cardPanel);
            mainframe.getGameZonePanel().add(pawnPanel);
            mainframe.getDiceZonePanel().add(dicePanel);
            mainframe.getGameZonePanel().add(gbview);
            mainframe.getEventZonePanel().add(eventPanel);

            gbview.validate();
            gbview.repaint();
            pawnPanel.validate();
            pawnPanel.repaint();
            eventPanel.validate();
            eventPanel.repaint();
            mainframe.getGameZonePanel().revalidate();
            mainframe.getGameZonePanel().repaint();
            mainframe.revalidate();
            mainframe.repaint();
    }


    public JButton getDiceButton(){
        return mainframe.getDiceButton();
    }

    public MainframeJFrame getMainframe(){
        return mainframe;
    }

    public void showLanciaIDadi(int turnPlayer){
        JLabel turnNameLabel = mainframe.getTurnNameLabel();
        turnNameLabel.setText("Tocca al Player-"+turnPlayer+"!");
        
    }

    public void movePawn(int[] startPlayerPos, int[] newPosition, Callback callback){
        pawnPanel.movePawn(startPlayerPos, newPosition, callback);
        
    }

    public void movePawnInstant(int[] startPlayerPos, int[] newPosition, Callback callback){
        pawnPanel.movePawnInstant(startPlayerPos, newPosition, callback);
    
    }
    
    public void showDicesRolled(int[] dadi, Callback callback){
        dicePanel.shuffleView(dadi[0], dadi[1], callback);
    }

    public void animTimer(int millis, Callback callback) {
        Timer timer = new Timer(millis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                SwingUtilities.invokeLater(() -> {
                    callback.onComplete();
                });
            }
        });
        timer.start();
    }

    public void showDeck(Callback callback){
        cardPanel.showDeck(callback);
    }

    public void showCard(Event e){
        e.accept(this);
    }

    public void showCard(MollaEvent e){
        cardPanel.showCard(e);
    }
    public void showCard(RerollEvent e){
        cardPanel.showCard(e);
    }
    public void showCard(StopEvent e){
        cardPanel.showCard(e);
    }
    public void showCard(FugaEvent e){
        cardPanel.showCard(e);
    }

    public void removeCard(Callback callback){ 
        cardPanel.setNull();
        SwingUtilities.invokeLater(() -> {
                    callback.onComplete();
                });
    }

    public JLabel getImageLabel(){
        return cardPanel.getImageLabel();
    }

    public JCheckBox getDiceEndCheckbox(){
        return mainframe.getOneDiceEndCheckBox();
    }

    public State getCurrentCardState(){
        return cardPanel.getCurrentState();
    }

    public Callback getEndCardEventCallback(){
        return cardPanel.getEventCallback();
    }

    public void printMessage(String s){
        eventPanel.aggiungiLog(s);
    }

    public void removeAllListeners(){
        for(ActionListener listener : getDiceButton().getActionListeners()){
            getDiceButton().removeActionListener(listener);
        }
        for(MouseListener listener : getImageLabel().getMouseListeners()){
            getImageLabel().removeMouseListener(listener);
        }
        for(ItemListener listener : getDiceEndCheckbox().getItemListeners()){
            getDiceEndCheckbox().removeItemListener(listener);
        }
    }


}
