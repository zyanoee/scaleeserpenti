import javax.swing.JFrame;

import config.configcontroller.GameSetupController;
import config.configmodels.GameConfig;
import config.configview.GameSetupView;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Giocone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Imposta le dimensioni del frame
        frame.setVisible(true);

        GameConfig gameConfig = GameConfig.getInstance();
        GameSetupView gameSetupView = new GameSetupView();
        GameSetupController gameSetupController = new GameSetupController(gameSetupView, gameConfig, frame);
        gameSetupView.showSetupScreen();

        
    }
}
