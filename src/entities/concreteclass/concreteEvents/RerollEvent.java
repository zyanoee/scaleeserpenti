package entities.concreteclass.concreteEvents;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class RerollEvent implements Event{


    public RerollEvent(){

    }
    public void execute(Game g, GameView gw, Callback callback) {
        

        Callback stopCallback = () -> {
            g.handleReroll();
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            System.out.println("[DEBUG-EVENTO] Execute di RerollEvent Avviato");
            int[] playerPos = new int[]{g.getPlayer().getPositionX(), g.getPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    
}
