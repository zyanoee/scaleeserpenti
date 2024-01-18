package config.configutility;
import java.awt.Color;
import java.awt.event.MouseEvent;


public interface EditBoardState {

    void mouseClicked(MouseEvent e);
    void mouseMoved(MouseEvent e);
    boolean isAvailable();
    Color getColor();
    Color getColorHover();
    
}



