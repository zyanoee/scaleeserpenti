package entities.concreteclass.concreteEvents;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class FugaEvent implements Event{
    
    public FugaEvent(){}

    public void execute(RuleHandler g, GameInterface game, GameView gw, Callback callback) {
        Callback stopCallback = () -> {
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
            g.increaseFugaCard(game.getCurrentPlayer());
            int[] playerPos = new int[]{game.getCurrentPlayer().getPositionX(), game.getCurrentPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
        
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    public String getDescription(){
        return "ha ottenuto una Carta Fuga, potrebbe darsela a gambe quando meno te lo aspetti";
    }

}
