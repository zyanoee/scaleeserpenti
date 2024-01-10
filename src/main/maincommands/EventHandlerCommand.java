package main.maincommands;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class EventHandlerCommand implements Command{
    

    private Game model;
    private GameView gw;
    private Command nextCommand;


    public EventHandlerCommand(Game model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        Event event = model.handleEvent();
            SwingUtilities.invokeLater(() -> {
                Callback callbackEvent = () -> {
                    nextCommand.execute();
                };

                if(event!=null){
                    event.execute(model, gw, callbackEvent);
                }
                else{callbackEvent.onComplete();}
            });
    }
}
