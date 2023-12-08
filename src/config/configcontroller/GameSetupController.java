package config.configcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import config.configview.GameBoardView;
import config.configview.GameSetupView;

public class GameSetupController {
    private GameSetupView view;
    private GameConfig model;
    private JFrame mainframe;

    public GameSetupController(GameSetupView view, GameConfig model, JFrame mainframe) {
        this.view = view;
        this.model = model;
        this.mainframe = mainframe;

        view.addStartGameButtonListener(new StartGameButtonListener());

        
    }

    

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Informazioni dal form
            int numPlayers = view.getNumPlayers();
            int gridSizeX = view.getGridSizeX();
            int gridSizeY = view.getGridSizeY();
            boolean enableSpecialRules = view.isSpecialRulesEnabled();
            boolean enableStopSquares = view.isStopRuleEnabled();
            boolean enableReRollSquares = view.isRerollEnabled();
            boolean enableCards = view.isCardsEnabled();


            // Aggiorna il model del Gamecfg
            model.setNumberOfPlayers(numPlayers);
            model.setGridSize(gridSizeX, gridSizeY);
            model.setSpecialRules(enableSpecialRules);
            model.setBlockSquareRule(enableStopSquares);
            model.setRerollSquareRule(enableReRollSquares);
            model.setCardsRule(enableCards);
            
            GameBoard board = new GameBoard(model.getGridSizeX(), model.getGridSizeY());
            GameBoardView gbview = new GameBoardView(board);
            SwingUtilities.invokeLater(() -> {
        
                mainframe.add(gbview); // Aggiungi GameBoardView a JFrame
                mainframe.revalidate(); // Aggiorna il layout del JFrame
            });



        }
    }
}