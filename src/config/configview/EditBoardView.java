package config.configview;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;


import config.configcontroller.EditViewState;
import config.configmodels.GameBoard;
import config.frames.BoardCustomizerJFrame;
import config.frames.HighlightOverlayJPanel;

public class EditBoardView {

    private GameBoard editBoard;
    private GameBoardView gbview;
    private HighlightOverlayJPanel hlightpanel; 
    private BoardCustomizerJFrame editFrame;
    private EditViewState state;

    public EditBoardView(GameBoard editBoard){
            this.editBoard = editBoard;
            editFrame = new BoardCustomizerJFrame();
            editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editFrame.setSize(800, 600); // Imposta le dimensioni del frame
            editFrame.setVisible(true);
            gbview = new GameBoardView(this.editBoard);
            hlightpanel = new HighlightOverlayJPanel(this.editBoard);
            editFrame.getEditBoardPanel().setLayout(new OverlayLayout(editFrame.getEditBoardPanel()));
            int dimX = editBoard.getGridSizeX()*50;
            int dimY = editBoard.getGridSizeY()*50;
            editFrame.getEditBoardPanel().setPreferredSize(new Dimension(dimX,dimY));
            gbview.setPreferredSize(new Dimension(dimX,dimY));
            hlightpanel.setPreferredSize(new Dimension(dimX, dimY));
            state = EditViewState.NO_SELECTED;
            
            editFrame.getEditButton().setEnabled(false);

            editFrame.getEditBoardPanel().add(hlightpanel);
            editFrame.getEditBoardPanel().add(gbview);

            
            
            
            
            
            gbview.validate();
            gbview.repaint();
            hlightpanel.validate();
            hlightpanel.repaint();
            editFrame.validate();
            editFrame.repaint();
    }

    public JButton getEditButton(){
        return editFrame.getEditButton();
    }

    public GameBoardView getGameBoardView(){
        return gbview;
    }

    public HighlightOverlayJPanel getHighlightOverlayJPanel(){
        return hlightpanel;
    }

    public void highlightCell(int x, int y, boolean isScala){
        
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if(isScala){
            if( posy!=0){
                hlightpanel.highlightCell(posx, posy);
                
            }
            
        }else{
            if(posy!=editBoard.getGridSizeY()-1){
                hlightpanel.highlightCell(posx,posy);
            }
        }
    }

    public void highlightCell2(int x, int y, boolean isScala){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if(isScala){
            if( posy<hlightpanel.getSelected()[1]){
                hlightpanel.highlightCell(posx, posy);
            }
        } else {
            if(posy>hlightpanel.getSelected()[1]){
                hlightpanel.highlightCell(posx, posy);
            }
        }
    }

    public void highlightPermanent(int x, int y){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        hlightpanel.highlightEnd(posx, posy);
    }

    public void highlightSetNull(){
        hlightpanel.setNull();
    }

    public void highlightSetNullPermanent(){
        hlightpanel.setNullPermanent();
    }

    public EditViewState getState(){
        return state;
    }

    public void setState(EditViewState state){
        this.state = state;
    }

    public int[] getSelected(){
        return hlightpanel.getSelected();
    }

    public void disposeFrame(){
        editFrame.disposeFrame();
    }




    
}
