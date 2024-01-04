package entities.concreteclass.concreteEvents;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class StopEvent implements Event{


    private int turns;

    public StopEvent(int turns){
        this.turns = turns;
    }
    public void execute(Game g, GameView gw, Callback callback) {
        Callback stopCallback = () -> {
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            System.out.println("[DEBUG-EVENTO] Execute di StopEvent Avviato");
            g.getPlayer().setBlocked(turns);
            int[] playerPos = new int[]{g.getPlayer().getPositionX(), g.getPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
        
    }

    public int getTurns(){
        return turns;
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    
    
}
