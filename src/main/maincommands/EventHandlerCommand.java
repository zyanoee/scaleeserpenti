package main.maincommands;

import javax.swing.SwingUtilities;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class EventHandlerCommand implements Command{
    

    private GameInterface model;
    private RuleHandler ruler;
    private GameView gw;
    private Command nextCommand;
    


    public EventHandlerCommand(GameInterface model, RuleHandler ruler, GameView gw, Command nextCommand) {
        this.model = model;
        this.ruler = ruler;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        Event event = ruler.handleEvent();
            SwingUtilities.invokeLater(() -> {
                Callback callbackEvent = () -> {
                    nextCommand.execute();
                };

                if(event!=null){
                    event.execute(ruler, model, gw, callbackEvent);
                }
                else{callbackEvent.onComplete();}
            });
    }
}
