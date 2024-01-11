package config.frames;


public class BoardCustomizerJFrame extends javax.swing.JFrame {

    

    private javax.swing.JPanel editBoardPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JRadioButton cardButton;
    private javax.swing.JRadioButton locandaButton;
    private javax.swing.JRadioButton mollaButton;
    private javax.swing.JRadioButton rerollButton;
    private javax.swing.JRadioButton scalaButton;
    private javax.swing.JRadioButton serpenteButton;
    private javax.swing.ButtonGroup statebuttonGroup;
    private javax.swing.JRadioButton stopButton;

    

                        
   

    private void initComponents() {

        statebuttonGroup = new javax.swing.ButtonGroup();
        editBoardPanel = new javax.swing.JPanel();
        scalaButton = new javax.swing.JRadioButton();
        serpenteButton = new javax.swing.JRadioButton();
        cardButton = new javax.swing.JRadioButton();
        stopButton = new javax.swing.JRadioButton();
        locandaButton = new javax.swing.JRadioButton();
        rerollButton = new javax.swing.JRadioButton();
        mollaButton = new javax.swing.JRadioButton();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout editBoardPanelLayout = new javax.swing.GroupLayout(editBoardPanel);
        editBoardPanel.setLayout(editBoardPanelLayout);
        editBoardPanelLayout.setHorizontalGroup(
            editBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );
        editBoardPanelLayout.setVerticalGroup(
            editBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        statebuttonGroup.add(scalaButton);
        scalaButton.setText("Scala");

        statebuttonGroup.add(serpenteButton);
        serpenteButton.setText("Serpente");

        statebuttonGroup.add(cardButton);
        cardButton.setText("CarteFolli");

        statebuttonGroup.add(stopButton);
        stopButton.setText("Stop");

        statebuttonGroup.add(locandaButton);
        locandaButton.setText("Locanda");

        statebuttonGroup.add(rerollButton);
        rerollButton.setText("Reroll");

        statebuttonGroup.add(mollaButton);
        mollaButton.setText("Molla");

        editButton.setText("Termina");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(editBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scalaButton)
                        .addComponent(serpenteButton)
                        .addComponent(cardButton)
                        .addComponent(stopButton)
                        .addComponent(locandaButton)
                        .addComponent(rerollButton)
                        .addComponent(mollaButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(scalaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serpenteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locandaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rerollButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mollaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(editBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        



    public javax.swing.JPanel getEditBoardPanel() {
        return editBoardPanel;
    }


    public javax.swing.JButton getEditButton() {
        return editButton;
    }


     public javax.swing.JRadioButton getCardButton() {
        return cardButton;
    }

    public javax.swing.JRadioButton getLocandaButton() {
        return locandaButton;
    }

    public javax.swing.JRadioButton getMollaButton() {
        return mollaButton;
    }

    public javax.swing.JRadioButton getRerollButton() {
        return rerollButton;
    }

    public javax.swing.JRadioButton getScalaButton() {
        return scalaButton;
    }

    public javax.swing.JRadioButton getSerpenteButton() {
        return serpenteButton;
    }

    public javax.swing.ButtonGroup getStatebuttonGroup() {
        return statebuttonGroup;
    }

    public javax.swing.JRadioButton getStopButton() {
        return stopButton;
    }


    public BoardCustomizerJFrame() {
        initComponents();
    }
        
    public void disposeFrame(){
        dispose();
    }



          
}
