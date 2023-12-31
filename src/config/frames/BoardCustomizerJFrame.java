package config.frames;

public class BoardCustomizerJFrame extends javax.swing.JFrame {

    
    private javax.swing.JLabel cellTypeLabel;
    private javax.swing.JLabel cellTypeLabel2;
    private javax.swing.JPanel editBoardPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel fillerText1;
    private javax.swing.JLabel fillerText2;
    private javax.swing.JLabel fillerText3;
    private javax.swing.JLabel fillerText4;
    private javax.swing.JLabel fillerText5;
    private javax.swing.JLabel actualValueCellType;
    private javax.swing.JLabel maximumValueCellType;
    private javax.swing.JPanel jPanel1;

    

                        
    private void initComponents() {

        editBoardPanel = new javax.swing.JPanel();
        editButton = new javax.swing.JButton();
        fillerText1 = new javax.swing.JLabel();
        cellTypeLabel = new javax.swing.JLabel();
        fillerText2 = new javax.swing.JLabel();
        fillerText3 = new javax.swing.JLabel();
        cellTypeLabel2 = new javax.swing.JLabel();
        fillerText4 = new javax.swing.JLabel();
        actualValueCellType = new javax.swing.JLabel();
        fillerText5 = new javax.swing.JLabel();
        maximumValueCellType = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout editBoardPanelLayout = new javax.swing.GroupLayout(editBoardPanel);
        editBoardPanel.setLayout(editBoardPanelLayout);
        editBoardPanelLayout.setHorizontalGroup(
            editBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        editBoardPanelLayout.setVerticalGroup(
            editBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        editButton.setText("Termina");

        fillerText1.setText("Inserisci la cella di tipo:");

        cellTypeLabel.setText("cellTypeText");

        fillerText2.setText("Ricorda: Dopo aver selezionato una cella per renderla una scala o un serpente, ");

        fillerText3.setText("scegli un'altra cella rispettivamente di riga superiore o inferiore per indicarne la cima / coda");

        cellTypeLabel2.setText("cellTypeText2");

        fillerText4.setText("inserite:");

        actualValueCellType.setText("0");

        fillerText5.setText("/");

        maximumValueCellType.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fillerText1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cellTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cellTypeLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(fillerText4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actualValueCellType)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fillerText5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maximumValueCellType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillerText1)
                    .addComponent(cellTypeLabel))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cellTypeLabel2)
                    .addComponent(fillerText4)
                    .addComponent(actualValueCellType)
                    .addComponent(fillerText5)
                    .addComponent(maximumValueCellType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(editButton)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fillerText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fillerText3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(editBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(editBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)))
                .addComponent(fillerText2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillerText3)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold> 

    public javax.swing.JLabel getCellTypeLabel() {
        return cellTypeLabel;
    }


    public javax.swing.JLabel getCellTypeLabel2() {
        return cellTypeLabel2;
    }


    public javax.swing.JPanel getEditBoardPanel() {
        return editBoardPanel;
    }


    public javax.swing.JButton getEditButton() {
        return editButton;
    }


    public javax.swing.JLabel getFillerText1() {
        return fillerText1;
    }


    public javax.swing.JLabel getFillerText2() {
        return fillerText2;
    }


    public javax.swing.JLabel getFillerText3() {
        return fillerText3;
    }


    public javax.swing.JLabel getFillerText4() {
        return fillerText4;
    }


    public javax.swing.JLabel getFillerText5() {
        return fillerText5;
    }


    public javax.swing.JLabel getActualValueCellType() {
        return actualValueCellType;
    }


    public javax.swing.JLabel getMaximumValueCellType() {
        return maximumValueCellType;
    }


    public BoardCustomizerJFrame() {
        initComponents();
    }
        
    public void disposeFrame(){
        dispose();
    }

          
}
