import javax.swing.JFrame;

import config.configcontroller.GameSetupController;
import config.configmodels.GameConfig;
import config.configview.GameSetupView;
import main.mainview.frames.MainframeJFrame;

public class App {
    public static void main(String[] args) {

        MainframeJFrame frame = new MainframeJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700); // Imposta le dimensioni del frame
        frame.setVisible(true);

        GameConfig gameConfig = GameConfig.getInstance();
        GameSetupView gameSetupView = new GameSetupView();
        GameSetupController gameSetupController = new GameSetupController(gameSetupView, gameConfig, frame);
        gameSetupController.startListener();
        gameSetupView.showSetupScreen();

        
    }
}
