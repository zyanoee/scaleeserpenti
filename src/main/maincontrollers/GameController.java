package main.maincontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;


import entities.interfaces.Callback;
import main.mainview.GameView;
import main.maincommands.DiceRollCommand;
import main.maincommands.EventHandlerCommand;
import main.maincommands.MoveCommand;
import main.maincommands.NextTurnCommand;
import main.maincommands.SixRollCommand;
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
         gw.removeAllListeners();
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

    

    public class DiceRollListener implements ActionListener{

    
        @Override
        public void actionPerformed(ActionEvent e) {
            new DiceRollCommand(model, gw,
                new MoveCommand(model, gw, 
                    new EventHandlerCommand(model, gw, 
                        new SixRollCommand(model, gw,
                            new NextTurnCommand(model, gw, null)
                        )
                    )
                )
            ).execute();
        }

        protected void enableDiceButton(){
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
