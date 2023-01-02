package Common.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HandWindow extends JFrame {
    private JPanel tileDrawnPanel;

    public HandWindow() {
        this.setLayout(null);
        this.setTitle("Hand");
        this.setSize(Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS * 4, Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS * 4);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        tileDrawnPanel = new JPanel(null);
        tileDrawnPanel.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
        tileDrawnPanel.setBackground(Color.RED);
        this.add(tileDrawnPanel);
        tileDrawnPanel.setLocation(this.getWidth() / 2 - Display.TILE_SIZE / 2, this.getHeight() / 2 - Display.TILE_SIZE / 2);

        JOptionPane.showMessageDialog(null,"Press R to rotate clockwise\nShift + R to rotate anti-clockwise", "Key bind",JOptionPane.INFORMATION_MESSAGE);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "You can't close this window ! If you want to exit the game, close the main window instead.", "windowClosingError", JOptionPane.ERROR_MESSAGE);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.setVisible(true);
    }


}
