package entities.interfaces;

public interface GameInterface {
    public int[] lanciaDadi();
    public void movePosition(int[] positions);
    public int[] muovi(int[] dadi);

    public int getTurnPlayerCounter();
    public void setTurnPlayerCounter(int turn);
    public Player getPawn(int i);
    public Player getCurrentPlayer();
    public int getNumberOfPlayers();

    public int[] getDadi();

    public Cell[] getPath(Cell start, Cell end, int lancio);
    public boolean checkDistance();

    public Cell getCell(int x, int y);
}
