package config.configview;

import javax.swing.*;
import java.awt.*;

import entities.concreteclass.NormalCell;
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
                if(!(cell instanceof NormalCell)){
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
                
                g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }

    }

    public void paintSnakes(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
        System.out.println("Chiamato paintSnakes con dimensione snakes: "+snakes.size());
        for(Cell s: snakes){
            SerpenteCell snake = (SerpenteCell) s;
            int x1 = snake.getPositionX();
            int y1 = snake.getPositionY();
            int x2 = snake.getLowerCell().getPositionX();
            int y2 = snake.getLowerCell().getPositionY();
            System.out.println("Serpente: "+x1+" "+y1+" "+x2+" "+y2);

            int centerX1 = (x1) * cellWidth + cellWidth / 2;
            int centerY1 = (y1) * cellHeight + cellHeight / 2;
            int centerX2 = (x2) * cellWidth + cellWidth / 2;
            int centerY2 = (y2) * cellHeight + cellHeight / 2;
            g2d.setColor(new Color(Integer.decode("#db3d68")));
            g2d.setStroke(new BasicStroke(4.5f));
            g2d.drawLine(centerX1,centerY1,centerX2,centerY2);
        }
    }

    public void paintScale(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
        System.out.println("Chiamato paintScale con dimensione snakes: "+scale.size());
        for(Cell s: scale){
            ScalaCell scala = (ScalaCell) s;
            int x1 = scala.getPositionX();
            int y1 = scala.getPositionY();
            int x2 = scala.getUpperCell().getPositionX();
            int y2 = scala.getUpperCell().getPositionY();
            g2d.setColor(new Color(Integer.decode("#3d3ddb")));
            g2d.setStroke(new BasicStroke(4.5f));
            g2d.drawLine((x1)*cellWidth+cellWidth/2, (y1)*cellHeight+cellHeight/2, (x2)*cellWidth+cellWidth/2, (y2)*cellHeight+cellHeight/2);
        }
    }

    public void paintNumbers(Graphics g) {
        int cellWidth = getWidth() / gridSizeX;
        int cellHeight = getHeight() / gridSizeY;
    
        for (int j = 0; j < gridSizeY; j++) {
            for (int i = 0; i < gridSizeX; i++) {
                int number = grid[i][j].getNumber();
                JLabel numero = new JLabel(Integer.toString(number));
                numero.setHorizontalAlignment(JLabel.RIGHT);
                numero.setVerticalAlignment(JLabel.BOTTOM);
    
                // Calcola la posizione per posizionare il numero nella parte inferiore destra della cella
                int xPosition = i * cellWidth + cellWidth - numero.getPreferredSize().width;
                int yPosition = (gridSizeY - j) * cellHeight + cellHeight;
    
                // Imposta la posizione del JLabel
                numero.setBounds(xPosition, yPosition, numero.getPreferredSize().width, numero.getPreferredSize().height);
    
                this.add(numero);
                numero.setVisible(true);
            }
        }
    }
    


   
}
