package main.maincommands;

import entities.interfaces.Callback;
import entities.interfaces.GameInterface;
import main.mainview.GameView;

public class MoveCommand implements Command {
    


    private GameInterface model;
    private GameView gw;
    private Command nextCommand;


    public MoveCommand(GameInterface model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        int[] currentPlayerPos = new int[]{model.getCurrentPlayer().getPositionX(), model.getCurrentPlayer().getPositionY()};
        int[] newPosition = model.muovi(model.getDadi());

        Callback callbackMove = () -> {
            nextCommand.execute();
        };

        gw.movePawn(currentPlayerPos, newPosition, callbackMove);
    }

}
