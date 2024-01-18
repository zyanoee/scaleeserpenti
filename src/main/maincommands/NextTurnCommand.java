package main.maincommands;

import javax.swing.SwingUtilities;

import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class NextTurnCommand implements Command{

    private GameInterface model;
    private GameView gw;
    private Command nextCommand;
    private RuleHandler ruler;


    public NextTurnCommand(GameInterface model, RuleHandler ruler, GameView gw, Command nextCommand) {
        this.model = model;
        this.ruler = ruler;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        SwingUtilities.invokeLater(() -> {
                int turnPlayer = ruler.handleNextTurn();
                gw.showLanciaIDadi(turnPlayer);
                gw.getDiceButton().setEnabled(true);
                SwingUtilities.invokeLater(() -> {
                    gw.getDiceEndCheckbox().setSelected(false);
                    if(model.checkDistance()){
                        gw.getDiceEndCheckbox().setEnabled(true);                                     
                    }
                    if(nextCommand!=null){
                        nextCommand.execute();
                    }
                });
            });
    }

}
