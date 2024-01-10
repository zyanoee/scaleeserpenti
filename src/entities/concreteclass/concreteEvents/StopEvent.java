package entities.concreteclass.concreteEvents;

import javax.swing.JOptionPane;
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
            gw.printMessage(" - Il Giocatore "+g.getTurnPlayerCounter()+" "+getDescription()+" -");
            
            if(g.isMoreCardEnabled() && g.getNCarteFuga(g.getPlayer()) > 0){
                boolean playerChoice = showCardChoice(gw);
                g.handleFugaUsage(g.getPlayer(), playerChoice);
                if(playerChoice){
                    gw.printMessage("- Il Giocatore "+g.getTurnPlayerCounter()+" si è dato alla fuga! -");
                }
                
            }else{g.getPlayer().setBlocked(turns);}
            int[] playerPos = new int[]{g.getPlayer().getPositionX(), g.getPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
        
    }

    public int getTurns(){
        return turns;
    }


    public boolean showCardChoice(GameView gw) {
        int scelta = JOptionPane.showConfirmDialog(
                gw.getMainframe(),
                "Vuoi utilizzare una tua Carta Fuga?",
                "Conferma",
                JOptionPane.YES_NO_OPTION
        );

        return scelta == JOptionPane.YES_OPTION;
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    public String getDescription(){
        return "è costretto a prendersi una pausa, Stai fermo "+turns+" turni";
    }

    
    
}
