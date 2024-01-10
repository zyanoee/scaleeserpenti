package main.maincommands;

import entities.interfaces.Callback;
import main.mainmodels.Game;
import main.mainview.GameView;

public class MoveCommand implements Command {
    


    private Game model;
    private GameView gw;
    private Command nextCommand;


    public MoveCommand(Game model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        int[] currentPlayerPos = new int[]{model.getPlayer().getPositionX(), model.getPlayer().getPositionY()};
        int[] newPosition = model.muovi(model.getDadi());

        Callback callbackMove = () -> {
            nextCommand.execute();
        };

        gw.movePawn(currentPlayerPos, newPosition, callbackMove);
    }

}
