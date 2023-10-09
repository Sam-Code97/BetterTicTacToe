import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;

public class View extends JFrame {

    Controller controller;

    private JFrame frame;
    private JPanel myButtonPanel;
    private JButton[][] buttons;
    private JPanel myTextPanel;
    private JLabel myLabel;
    private JPanel myMainPanel;
    View(Controller _controller){
        controller = _controller;
        int rows = controller.getRows();
        int cols = controller.getCols();
        int totalGames = controller.gameType.values().length;
        if(!controller.gameStarted){
            viewFrame(totalGames, 1);
        }
        else viewFrame(rows, cols);

    }

    public void viewFrame(int rows, int cols){

        frame = new JFrame("TerribleTicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myButtonPanel = new JPanel();
        myButtonPanel.setLayout(new GridLayout(rows,cols));
        buttons = new JButton[rows][cols];

        myTextPanel = new JPanel();
        myTextPanel.setLayout(new GridLayout(1,1));
        myTextPanel.setPreferredSize(new Dimension(150,50));
        myLabel = new JLabel("player 1's turn", SwingConstants.CENTER);
        myTextPanel.add(myLabel);

        myMainPanel = new JPanel();
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
        myMainPanel.add(myButtonPanel);
        myMainPanel.add(myTextPanel);

        if(!controller.gameStarted) {
            frame.setTitle("TableGames");
            myLabel.setText("Chose a game to play");
        }

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                final int _r = r;
                final int _c = c;//(!controller.gameStarted? 0 : c);
                JButton button = buttons[r][c] = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.setText(!controller.gameStarted? controller.gameType.values()[r].name() : " ");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.unitClicked(_r, _c);
                    }
                });
                myButtonPanel.add(button);
            }
        }


        frame.getContentPane().add(myMainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    public void updateViewBoard(int _r, int _c, String symbol){
        buttons[_r][_c].setText(symbol);
    }
    public void updateViewLable(String text){
        myLabel.setText(text);
    }
}
