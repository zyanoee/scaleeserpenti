package main.maincontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import config.configmodels.GameConfig;
import entities.interfaces.Callback;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;
import main.maincommands.DiceRollCommand;
import main.maincommands.EventHandlerCommand;
import main.maincommands.MoveCommand;
import main.maincommands.NextTurnCommand;
import main.maincommands.SixRollCommand;



public class GameController {

    private GameInterface model;
    private RuleHandler ruler;
    private GameView gw;
   
    

    public GameController(GameInterface model, RuleHandler ruler, GameView gw){
        GameConfig config = GameConfig.getInstance();
        this.model = model;
        this.ruler = ruler;
        this.gw = gw;
        gw.getDiceButton().setEnabled(true);
        if(config.isOneDiceEndEnabled()){
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
                   ruler.handleOneDiceEnd(true);
                } else {
                   ruler.handleOneDiceEnd(false);
                }
            }
         });
    }

    

    public class DiceRollListener implements ActionListener{

    
        @Override
        public void actionPerformed(ActionEvent e) {
            new DiceRollCommand(model, gw,
                new MoveCommand(model, gw, 
                    new EventHandlerCommand(model, ruler, gw, 
                        new SixRollCommand(model, ruler, gw,
                            new NextTurnCommand(model, ruler, gw, null)
                        )
                    )
                )
            ).execute();
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
                    gw.showCard(ruler.getLastCard());
                    break;
                case SHOW_CARD:
                    Callback removeCardCallback = () -> {
                        ruler.getLastCard().execute(ruler, model, gw, callbackEndEvent);
                    };
                    gw.removeCard(removeCardCallback);
                    break;
                case SET_NULL:
                    break;
            }
        }

    }

    
}
