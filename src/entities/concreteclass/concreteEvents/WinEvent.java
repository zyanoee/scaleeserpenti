package entities.concreteclass.concreteEvents;

import javax.swing.JOptionPane;

import config.configcontroller.GameSetupController;
import config.configview.GameSetupView;
import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class WinEvent implements Event{

    public WinEvent(){

    }
    public void execute(Game g, GameView gw, Callback callback) {
        gw.printMessage(" - Il Giocatore "+g.getTurnPlayerCounter()+" "+getDescription()+" -");
        int choice = JOptionPane.showConfirmDialog(gw.getMainframe(),
            "Il Giocatore "+g.getTurnPlayerCounter()+" Ha vinto! Vuoi iniziare una nuova partita?", "Vittoria!",
            JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION){
            GameSetupView gameSetupView = new GameSetupView();
            GameSetupController gameSetupController = new GameSetupController(gameSetupView, gw.getMainframe());
            gameSetupController.startListener();
            gameSetupView.showSetupScreen();
        } else {
            gw.getMainframe().close();
        }
    }

    public void accept(GameView gw){
        
    }

    public String getDescription(){
        return "HA VINTO! Lunga vita al nostro campione!";
    }

    
}
