package entities.concreteclass.concreteEvents;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import config.configmodels.GameConfig;
import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class StopEvent implements Event{


    private int turns;

    public StopEvent(int turns){
        this.turns = turns;
    }
    public void execute(RuleHandler g, GameInterface game, GameView gw, Callback callback) {
        Callback stopCallback = () -> {
            callback.onComplete();
        };
        SwingUtilities.invokeLater(() -> {
            gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
            
            if(g.isMoreCardEnabled() && g.getNCarteFuga(game.getCurrentPlayer()) > 0){
                boolean playerChoice = showCardChoice(gw);
                g.handleFugaUsage(game.getCurrentPlayer(), playerChoice);
                if(playerChoice){
                    gw.printMessage("- Il Giocatore "+game.getTurnPlayerCounter()+" si è dato alla fuga usando una Carta Fuga -");
                }
                
            }else{game.getCurrentPlayer().setBlocked(turns);}
            int[] playerPos = new int[]{game.getCurrentPlayer().getPositionX(), game.getCurrentPlayer().getPositionY()};
            gw.movePawnInstant(playerPos, playerPos, stopCallback);
        });
        
    }

    public int getTurns(){
        return turns;
    }


    public boolean showCardChoice(GameView gw) {
        if(GameConfig.getInstance().isAutomatic()){
            return true;
        }
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
