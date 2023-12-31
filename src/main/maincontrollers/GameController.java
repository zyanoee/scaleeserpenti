package main.maincontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;



import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainview.GameView;
import main.mainmodels.Game;


public class GameController {

    private Game model;
    private GameView gw;

    

    public GameController(Game model, GameView gw){

        this.model = model;
        this.gw = gw;
        if(model.isOneDiceEndEnabled()){
            gw.getDiceEndCheckbox().setVisible(true);
        }
        gw.showLanciaIDadi(0);
       

    }

    public void startListener(){
         gw.getDiceButton().addActionListener(new DiceRollListener());
         gw.getImageLabel().addMouseListener(new CardClickListener());
         gw.getDiceEndCheckbox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if( gw.getDiceEndCheckbox().isSelected()){
                   model.handleOneDiceEnd(true);
                } else {
                   model.handleOneDiceEnd(false);
                }
            }
         });
    }

    class DiceRollListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gw.getDiceButton().setEnabled(false);
            gw.getDiceEndCheckbox().setEnabled(false);

                
                int[] dadi;
                dadi = model.lanciaDadi();
                int[] currentPlayerPos = new int[]{model.getPlayer().getPositionX(),model.getPlayer().getPositionY()};
                int[] newPosition = model.muovi(dadi);


                Callback callbackDices = () -> {
                    Callback callbackMove = () -> {
                        Event event = model.handleEvent();
                        SwingUtilities.invokeLater(() -> {
                            Callback callbackEvent = () -> {
                                System.out.println("[DEBUG-CONTROLLER] Terminato Evento del tipo: "+event);
                                SwingUtilities.invokeLater(() -> {
                                    int turnPlayer = model.handleNextTurn();
                                    gw.showLanciaIDadi(turnPlayer);
                                    gw.getDiceButton().setEnabled(true);
                                    
                                     SwingUtilities.invokeLater(() -> {
                                        gw.getDiceEndCheckbox().setSelected(false);
                                        if(model.checkDistance()){
                                            gw.getDiceEndCheckbox().setEnabled(true);
                                            
                                        }
                                     });
                                });
                            };
                            if(event!=null){
                                System.out.println("[DEBUG-CONTROLLER] Chiamato evento del tipo: "+event);
                                event.execute(model, gw, callbackEvent);
                            }
                            else{callbackEvent.onComplete();}
                        });
                         
                        
                       
                    };
                    gw.movePawn(currentPlayerPos, newPosition, callbackMove);
                };
                gw.showDicesRolled(dadi, callbackDices);
        }

    }  

    class CardClickListener extends MouseAdapter{

        Callback callbackEndEvent;

        @Override
        public void mouseClicked(MouseEvent e) {
            handleImageClick();
        }

        public void handleImageClick(){

            callbackEndEvent = gw.getEndCardEventCallback();

            switch (gw.getCurrentCardState()) {
                case SHOW_DECK:
                    gw.showCard(model.getLastCard());
                    break;
                case SHOW_CARD:
                    Callback removeCardCallback = () -> {
                        model.getLastCard().execute(model, gw, callbackEndEvent);
                    };
                    gw.removeCard(removeCardCallback);

                    break;
                case SET_NULL:
                    break;
            }
        }

    }

    
}
