package tetris;

public class TetrisComponents {
    // AUTO GENERATED - do not modify//GEN-BEGIN:variables
    public void initComponents(Tetris tetris) {
        tetris.jPanel1 = new javax.swing.JPanel();
        tetris.gameGrid2 = new ch.aplu.jgamegrid.GameGrid();
        tetris.startBtn = new javax.swing.JButton();
        tetris.jPanel2 = new javax.swing.JPanel();
        tetris.gameGrid1 = new ch.aplu.jgamegrid.GameGrid();
        tetris.jPanel3 = new javax.swing.JPanel();
        tetris.jScrollPane1 = new javax.swing.JScrollPane();
        tetris.jTextArea1 = new javax.swing.JTextArea();
        tetris.jPanel4 = new javax.swing.JPanel();
        tetris.scoreText = new javax.swing.JTextField();

        tetris.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tetris.jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Next TetrisBlock"));

        tetris.gameGrid2.setCellSize(20);
        tetris.gameGrid2.setNbHorzCells(6);
        tetris.gameGrid2.setNbVertCells(4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(tetris.jPanel1);
        tetris.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(tetris.gameGrid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tetris.gameGrid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        tetris.startBtn.setText("Start");
        tetris.startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tetris.startBtnActionPerformed(evt);
            }
        });

        tetris.jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tetris.gameGrid1.setCellSize(20);
        tetris.gameGrid1.setGridColor(new java.awt.Color(255, 3, 0));
        tetris.gameGrid1.setNbVertCells(30);
        tetris.gameGrid1.setNbHorzCells(15);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(tetris.jPanel2);
        tetris.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tetris.gameGrid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tetris.gameGrid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tetris.jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Instructions"));

        tetris.jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tetris.jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tetris.jTextArea1.setBackground(new java.awt.Color(102, 255, 255));
        tetris.jTextArea1.setColumns(20);
        tetris.jTextArea1.setEditable(false);
        tetris.jTextArea1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tetris.jTextArea1.setRows(5);
        tetris.jTextArea1.setText(" Welcome to the JGameGrid Tetris! \n\n Cursor keys:\n    Left/right:  Move tetrisBlock\n    Up:  Rotate tetrisBlock\n    Down:  Drop tetrisBlock\n  \n When a  line  is complete, it is removed \n and you earn one point, but the speed\n of  the tetrisBlocks  increases  more and\n more...\n       \n       Good luck!");
        tetris.jTextArea1.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        tetris.jTextArea1.setFocusable(false);
        tetris.jTextArea1.setRequestFocusEnabled(false);
        tetris.jScrollPane1.setViewportView(tetris.jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(tetris.jPanel3);
        tetris.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tetris.jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tetris.jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tetris.jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Score"));

        tetris.scoreText.setBackground(new java.awt.Color(255, 102, 102));
        tetris.scoreText.setEditable(false);
        tetris.scoreText.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tetris.scoreText.setText("0");
        tetris.scoreText.setAutoscrolls(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(tetris.jPanel4);
        tetris.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tetris.scoreText, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tetris.scoreText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tetris.getContentPane());
        tetris.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tetris.jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tetris.jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                                        .addComponent(tetris.jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tetris.jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tetris.startBtn)
                                                .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tetris.jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(tetris.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tetris.jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tetris.jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tetris.startBtn)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tetris.pack();
    }// </editor-fold>//GEN-END:initComponents
}
