package entities.concreteclass.concreteEvents;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class FugaEvent implements Event{
    
    public FugaEvent(){}

    public void execute(Game g, GameView gw, Callback callback) {
        Callback stopCallback = () -> {
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            gw.printMessage(" - Il Giocatore "+g.getTurnPlayerCounter()+" "+getDescription()+" -");
            g.increaseFugaCard(g.getPlayer());
            int[] playerPos = new int[]{g.getPlayer().getPositionX(), g.getPlayer().getPositionY()};
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
