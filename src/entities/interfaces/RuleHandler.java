package entities.interfaces;

public interface RuleHandler {

    public Event handleEvent();
    public int handleNextTurn();

    public void increaseFugaCard(Player p);
    public boolean handleFugaUsage(Player p, boolean playerChoice);
    public int getNCarteFuga(Player p);
    public Event getLastCard();

    public Event handleCard();
    public void handleOneDiceEnd(boolean b);

    public boolean isOneDiceEndEnabled();
    public boolean isDoubleSixEnabled();
    public boolean isMoreCardEnabled();



}
