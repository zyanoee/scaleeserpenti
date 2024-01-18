package entities.interfaces;

import main.mainview.GameView;

public interface Event{
    public void execute(RuleHandler model, GameInterface game, GameView gw, Callback callback);
    public void accept(GameView gw);
    public String getDescription();
}
