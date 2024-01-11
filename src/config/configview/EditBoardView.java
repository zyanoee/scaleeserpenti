package config.configview;

import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.OverlayLayout;


import config.configcontroller.EditViewState;
import config.configutility.EditBoardState;
import config.configutility.states.ScalaState;
import config.configutility.states.SerpenteState;
import config.frames.BoardCustomizerJFrame;
import config.frames.HighlightOverlayJPanel;
import entities.interfaces.EditBoardInterface;

public class EditBoardView {

    private EditBoardInterface editBoard;
    private GameBoardView gbview;
    private HighlightOverlayJPanel hlightpanel; 
    private BoardCustomizerJFrame editFrame;
    private EditViewState state;

    public EditBoardView(EditBoardInterface editBoard){
            this.editBoard = editBoard;
            int dimX = editBoard.getGridSizeX()*50;
            int dimY = editBoard.getGridSizeY()*50;
            editFrame = new BoardCustomizerJFrame();
            editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editFrame.setSize(dimX+320, dimY+200); 
            editFrame.setVisible(true);
            gbview = new GameBoardView(this.editBoard);
            hlightpanel = new HighlightOverlayJPanel(this.editBoard);
            editFrame.getEditBoardPanel().setLayout(new OverlayLayout(editFrame.getEditBoardPanel()));
            
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


    public void highlightCell(int x, int y, SerpenteState serpenteState){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if(posy!=editBoard.getGridSizeY()-1){
            hlightpanel.highlightCell(posx,posy, serpenteState);
        }
    }

    public void highlightCell(int x, int y, ScalaState scalaState){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if( posy!=0){
                hlightpanel.highlightCell(posx, posy, scalaState);
                
            }
    }

    public void highlightCell(int x, int y, EditBoardState ebstate){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        hlightpanel.highlightCell(posx, posy, ebstate);
    }

    public void highlightCell2(int x, int y, ScalaState scalaState){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if( posy<hlightpanel.getSelected()[1]){
            hlightpanel.highlightCell(posx, posy, scalaState);
        }
    }

    public void highlightCell2(int x, int y, SerpenteState serpenteState){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        if( posy>hlightpanel.getSelected()[1]){
            hlightpanel.highlightCell(posx, posy, serpenteState);
        }
    }

    public void highlightCell2(int x, int y, EditBoardState ebstate){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        hlightpanel.highlightCell(posx, posy, ebstate);
        
    }

    public void setAllRadio(boolean b){
        ButtonGroup buttonGroup = editFrame.getStatebuttonGroup();

        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(b);
        }
    }

    public void setAllRuleButtons(boolean b){
        editFrame.getStopButton().setEnabled(b);
        editFrame.getLocandaButton().setEnabled(b);
        editFrame.getRerollButton().setEnabled(b);
        editFrame.getMollaButton().setEnabled(b);
        editFrame.getCardButton().setEnabled(b);
    }




    public void highlightPermanent(int x, int y, EditBoardState ebstate){
        int posx = x/(gbview.getWidth() / editBoard.getGridSizeX());
        int posy = y/(gbview.getHeight() / editBoard.getGridSizeY());
        hlightpanel.highlightEnd(posx, posy, ebstate );
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

    public JRadioButton getScalaButton() {
        return editFrame.getScalaButton();
    }

    public JRadioButton getSerpenteButton() {
        return editFrame.getSerpenteButton();
    }

    public JRadioButton getRerollButton() {
        return editFrame.getRerollButton();
    }

    public JRadioButton getMollaButton() {
        return editFrame.getMollaButton();
    }

    public JRadioButton getStopButton() {
        return editFrame.getStopButton();
    }

    public JRadioButton getLocandaButton() {
        return editFrame.getLocandaButton();
    }

    public JRadioButton getCardButton() {
        return editFrame.getCardButton();
    }




    
}
