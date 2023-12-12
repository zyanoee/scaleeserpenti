package config.configview;

import javax.swing.*;
import java.awt.*;

import entities.concreteclass.ScalaCell;
import entities.concreteclass.SerpenteCell;
import entities.interfaces.Cell;
import entities.interfaces.SpecialCell;
import config.configmodels.GameBoard;

import java.util.List;
import java.util.ArrayList;

public class GameBoardView extends JPanel {
    private int gridSizeX;
    private int gridSizeY;
    private Cell[][] grid;
    private List<Cell> snakes;
    private List<Cell> scale;
    

    public GameBoardView(GameBoard model) {
        gridSizeX = model.getGridSizeX();
        gridSizeY = model.getGridSizeY();
        grid = model.getGrid();
        snakes = new ArrayList<>();
        scale = new ArrayList<>();


    }

    public int getGridSizeX(){
        return this.gridSizeX;
    }

    public int getGridSizeY(){
        return this.gridSizeY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        paintChessboard(g);
        paintSnakes(g);
        paintScale(g);
        paintNumbers(g);


        

        
    }


    public void paintChessboard(Graphics g){
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
        Color c;

        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                if((x+y)%2==0){
                    c = Color.LIGHT_GRAY;
                } else {
                    c = Color.WHITE;
                }
                Cell cell = grid[x][y];
                if(cell.isSpecial()){
                    SpecialCell scell = (SpecialCell) cell;
                    switch (scell.getSpecialCellType()) {
                        case SERPENTE:
                            snakes.add(scell);
                            break;
                        case SCALA:
                            scale.add(scell);
                            break;
                        default:
                            break;
                    }
                    g.setColor(cell.getColor());
                } else {
                    g.setColor(c);
                }
                
                g.fillRect((x) * cellWidth, (gridSizeY - y- 1)  * cellHeight, cellWidth, cellHeight);
            }
        }

    }

    public void paintSnakes(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
        for(Cell s: snakes){
            SerpenteCell snake = (SerpenteCell) s;
            int x1 = snake.getPositionX();
            int y1 = snake.getPositionY();
            int x2 = snake.getLowerCell().getPositionX();
            int y2 = snake.getLowerCell().getPositionY();

            int rotatedx1 = x1;
            int rotatedy1 = (gridSizeY - y1- 1);
            int rotatedx2 = x2;
            int rotatedy2 = (gridSizeY - y2- 1);

            int centerX1 = (rotatedx1) * cellWidth + cellWidth / 2;
            int centerY1 = (rotatedy1) * cellHeight + cellHeight / 2;
            int centerX2 = (rotatedx2) * cellWidth + cellWidth / 2;
            int centerY2 = (rotatedy2) * cellHeight + cellHeight / 2;
            g2d.setColor(new Color(Integer.decode("#db3d68")));
            g2d.setStroke(new BasicStroke(4.5f));
            g2d.drawLine(centerX1,centerY1,centerX2,centerY2);
        }
    }

    public void paintScale(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
        for(Cell s: scale){
            ScalaCell scala = (ScalaCell) s;
            int x1 = scala.getPositionX();
            int y1 = scala.getPositionY();
            int x2 = scala.getUpperCell().getPositionX();
            int y2 = scala.getUpperCell().getPositionY();

            int rotatedx1 = x1;
            int rotatedy1 = (gridSizeY - y1- 1);
            int rotatedx2 = x2;
            int rotatedy2 = (gridSizeY - y2- 1);

            
            int centerX1 = (rotatedx1) * cellWidth + cellWidth / 2;
            int centerY1 = (rotatedy1) * cellHeight + cellHeight / 2;
            int centerX2 = (rotatedx2) * cellWidth + cellWidth / 2;
            int centerY2 = (rotatedy2) * cellHeight + cellHeight / 2;
            g2d.setColor(new Color(Integer.decode("#3d3ddb")));
            g2d.setStroke(new BasicStroke(4.5f));
            g2d.drawLine(centerX1,centerY1,centerX2,centerY2);
        }
    }

    public void paintNumbers(Graphics g) {
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
    
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
    
        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                int number = grid[x][y].getNumber(); // Aggiungi un metodo getNumber() alla classe Cell
                String numberString = String.valueOf(number);
    
                int rotatedX = x;
                int rotatedY = (gridSizeY - y - 1);
    
                int centerX = (rotatedX) * cellWidth + cellWidth / 2;
                int centerY = (rotatedY) * cellHeight + cellHeight / 2;
    
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int textWidth = fontMetrics.stringWidth(numberString);
                int textHeight = fontMetrics.getAscent();

                int offsetX = cellWidth / 3;
                int offsetY = cellHeight / 3;
    
                g2d.drawString(numberString, centerX - textWidth / 2 + offsetX, centerY + textHeight / 2 + offsetY);
            }
        }

        
    }



   
}
