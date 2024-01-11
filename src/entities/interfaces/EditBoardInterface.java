package entities.interfaces;

public interface EditBoardInterface extends GameBoardInterface {

    public boolean addScala(int x1, int y1, int x2, int y2);
    public boolean selectScala(int x1, int y1);
    public boolean addSerpente(int x1, int y1, int x2, int y2);
    public boolean selectSerpente(int x1, int y1);
    public boolean isNormal(int x1, int x2);
    public boolean addRuleCell(Cell cell);

}
