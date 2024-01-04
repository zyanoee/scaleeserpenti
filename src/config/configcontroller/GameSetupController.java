package config.configcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import config.configutility.ConfigFileHandler;
import config.configview.EditBoardView;
import config.configview.GameSetupView;
import main.maincontrollers.GameController;
import main.mainmodels.Game;
import main.mainview.GameView;
import main.mainview.frames.MainframeJFrame;


public class GameSetupController {
    private GameSetupView view;
    private GameConfig model;
    private MainframeJFrame mainframe;

    public GameSetupController(GameSetupView view, GameConfig model, MainframeJFrame mainframe) {
        this.view = view;
        this.model = model;
        this.mainframe = mainframe;

        
    }

    public void startListener(){
        view.addStartGameButtonListener(new StartGameButtonListener());
        view.addLoadButtonListener(new LoadConfigListener());
    }

    

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Informazioni dal form
            int numPlayers = view.getNumPlayers();
            int gridSizeX = view.getGridSizeX();
            int gridSizeY = view.getGridSizeY();
            int nScale = view.getNScale();
            int nSerpenti = view.getNSerpenti();
            boolean enableSpecialRules = view.isSpecialRulesEnabled();
            boolean enableStopSquares = view.isStopRuleEnabled();
            boolean enablePrizeSquares = view.isPrizeEnabled();
            boolean enableCards = view.isCardsEnabled();
            boolean enableDoubleSixRule = view.isDoubleSixEnabled();
            boolean enableOneDice = view.isOneDiceEnabled();
            boolean enableOneDiceEnd = view.isOneDiceEndEnabled();
            boolean enableCardsAddon = view.isCardsAddonEnabled();
            boolean wantToEdit = view.wantToEdit();
            
            



            // Aggiorna il model del Gamecfg
            model.setNumberOfPlayers(numPlayers);
            model.setGridSize(gridSizeX, gridSizeY);
            model.setSpecialRules(enableSpecialRules);
            model.setBlockSquareRule(enableStopSquares);
            model.setPrizeSquareRule(enablePrizeSquares);
            model.setCardsRule(enableCards);
            model.setDoubleSixRule(enableDoubleSixRule);
            model.setOneDiceRule(enableOneDice);
            model.setOneDiceEndRule(enableOneDiceEnd);
            model.setCardsRuleAddon(enableCardsAddon);
            model.setEditing(wantToEdit);
            model.setNScale(nScale);
            model.setNSerpenti(nSerpenti);
            
            SwingUtilities.invokeLater(() -> {
                if(model.wantToEdit()){
                    initializeEditBoardView();
                }else{
                    initializeGameBoardView();
                }
                
                closeConfigFrame();
            });



        }

        private void closeConfigFrame(){
            view.disposeFrame();
        }

        private void initializeGameBoardView() {
            ConfigFileHandler.saveConfiguration(mainframe);
            GameBoard board = new GameBoard(model);
            board.generateElements();
            Game game = new Game(model, board);
            GameView gw = new GameView(mainframe, board, model, game);
            GameController gc = new GameController(game, gw);
            gc.startListener();
            
 
        }



        private void initializeEditBoardView(){
            GameBoard editBoard = new GameBoard(model);
            EditBoardView ebview = new EditBoardView(editBoard);
            EditBoardController ebcontroller = new EditBoardController(ebview, editBoard, model, mainframe);
            ebcontroller.startListener();
            


        }
    }

    class LoadConfigListener implements ActionListener{
         public void actionPerformed(ActionEvent e) {
            ConfigFileHandler.loadConfiguration(view.getFrame());
            updateView();
         }

         public void updateView(){
            view.setNumberOfPlayers(model.getNumberOfPlayers());
            view.setGridSizeX(model.getGridSizeX());
            view.setGridSizeY(model.getGridSizeY());
            view.setNScale(model.getNScale());
            view.setNSerpenti(model.getNSerpenti());
            view.setSpecialRules(model.isSpecialRulesEnabled());
            view.setCardsRule(model.isCardRuleEnabled());
            view.setCardsRuleAddon(model.isCardsAddonEnabled());
            view.setBlockSquareRule(model.isStopRuleEnabled());
            view.setPrizeSquareRule(model.isPrizeEnabled());
            view.setDoubleSixRule(model.isDoubleSixEnabled());
            view.setOneDiceRule(model.isOneDiceEnabled());
            view.setOneDiceEndRule(model.isOneDiceEndEnabled());
         }
  
    }



   
}