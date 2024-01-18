package entities.concreteclass.concreteEvents;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class RerollEvent implements Event{


    public RerollEvent(){

    }
    public void execute(RuleHandler g, GameInterface game, GameView gw, Callback callback) {
        

        Callback stopCallback = () -> {
            int newTurn =  game.getTurnPlayerCounter()!=0 ? game.getTurnPlayerCounter()-1 : game.getNumberOfPlayers()-1;
            game.setTurnPlayerCounter(newTurn);
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
            int[] playerPos = new int[]{game.getCurrentPlayer().getPositionX(), game.getCurrentPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    public String getDescription(){
        return "ha ricevuto una seconda possibilità! Rilancia i dadi";
    }

    
}
