package entities.interfaces;

import java.awt.Color;

import entities.concreteclass.CardCell;
import entities.concreteclass.NormalCell;
import entities.concreteclass.RerollCell;
import entities.concreteclass.ScalaCell;
import entities.concreteclass.SerpenteCell;
import entities.concreteclass.StopCell;
import entities.concreteclass.WinCell;

public interface Player {
    int getPositionX();
    int getPositionY();
    int getId();
    Color getColor();

    void move(int x, int y);
    int applyEffect(Cell cell);
    int applyEffect(NormalCell cell);
    int applyEffect(ScalaCell cell);
    int applyEffect(SerpenteCell cell);
    int applyEffect(RerollCell cell);
    int applyEffect(StopCell cell);
    int applyEffect(CardCell cell);
    int applyEffect(WinCell cell);
}
