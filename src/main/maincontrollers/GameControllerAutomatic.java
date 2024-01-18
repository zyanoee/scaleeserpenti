package main.maincontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.Timer;

import entities.State;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;


public class GameControllerAutomatic extends GameController {

    
    private RuleHandler ruler;
    private GameView gw;


    public GameControllerAutomatic(GameInterface model, RuleHandler ruler, GameView gw) {
        super(model,ruler, gw);
        this.ruler = ruler;
        this.gw = gw;
    }


    @Override
    public void startListener(){
        gw.removeAllListeners();
        gw.getDiceButton().setEnabled(true);
        gw.getDiceButton().addActionListener(new DiceRollAutomaticListener());
        gw.getImageLabel().addMouseListener(new CardClickAutomaticListener());
        gw.getDiceEndCheckbox().addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e){
               if( gw.getDiceEndCheckbox().isSelected()){
                  ruler.handleOneDiceEnd(true);
               } else {
                  ruler.handleOneDiceEnd(false);
               }
           }
        });

   }

   class DiceRollAutomaticListener extends DiceRollListener{

    private Timer timer;

    public DiceRollAutomaticListener(){
            super();
            automaticClick();
        }

    public void automaticClick(){
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gw.getDiceButton().isEnabled()){
                        gw.getDiceButton().doClick();
                    }
                }
            });
            timer.start();
        }

   }

   class CardClickAutomaticListener extends CardClickListener{

    private Timer timer;

        public CardClickAutomaticListener(){
            super();
            automaticClick();
        }

        public void automaticClick(){
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gw.getCurrentCardState()!=State.SET_NULL){
                        handleImageClick();
                    }
                }
            });
            timer.start();
        }

   }

}
