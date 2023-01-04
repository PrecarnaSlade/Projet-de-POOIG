package Common.Window;

import Carcassonne.CarcassonneTile;
import Common.Player;
import Common.Tile;
import Misc.WindowManagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Misc.WindowManagement.*;

public class HandWindow extends JInternalFrame {
    private final JPanel tileDrawnPanel;
    private BufferedImage tileDrawnImage;
    private Tile tileDrawn;

    public HandWindow() throws IOException {
        this.setLayout(null);
        this.setTitle("Hand");
        Insets insets = this.getInsets();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.tileDrawn = null;
        this.tileDrawnImage = WindowManagement.resize(ImageIO.read(new File("./Data/Resources/Null1.png")), Display.TILE_SIZE, Display.TILE_SIZE);
        this.tileDrawnPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tileDrawnImage, 0, 0, this);
            }
        };
        tileDrawnPanel.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
        tileDrawnPanel.setBackground(Color.RED);
        this.add(tileDrawnPanel);
        tileDrawnPanel.setLocation(0, 0);

        JOptionPane.showMessageDialog(null,"Press R to rotate clockwise\nShift + R to rotate anti-clockwise", "Key bind",JOptionPane.INFORMATION_MESSAGE);

        InputMap inputMap = this.tileDrawnPanel.getInputMap();
        ActionMap actionMap = this.tileDrawnPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "rotateClockwise");
        actionMap.put("rotateClockwise", new RotationClockwiseAction(this));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK), "rotateAntiClockwise");
        actionMap.put("rotateAntiClockwise", new RotationAntiClockwiseAction(this));
        this.setVisible(true);
        this.setSize(Display.TILE_SIZE + insets.left + insets.right, Display.TILE_SIZE + insets.top + insets.bottom + Display.DISTANCE_BETWEEN_BUTTONS);

    }

    public void setDrawnTile(Tile tile, Player player) {
        this.setTitle(player.getName() + "'s hand");
        setDrawnTile(tile);
    }

    private void setDrawnTile(Tile tile) {
        this.tileDrawn = tile;
        if (tile instanceof CarcassonneTile) {
            this.tileDrawnImage = ((CarcassonneTile) tile).getDisplay();
        } else {
            this.tileDrawnImage = panelToBufferedImage(tile.getGraphic());
        }
        this.tileDrawnPanel.repaint();
    }

    public void rotateClockwise() {
        this.tileDrawn.rotateClockwise();
        setDrawnTile(this.tileDrawn);
    }

    public void rotateAntiClockwise() {
        this.tileDrawn.rotateAntiClockwise();
        setDrawnTile(this.tileDrawn);
    }

    private static class RotationClockwiseAction extends AbstractAction {
        private final HandWindow parentWindow;

        public RotationClockwiseAction(HandWindow parentWindow) {
            this.parentWindow = parentWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parentWindow.rotateClockwise();
        }
    }

    private static class RotationAntiClockwiseAction extends AbstractAction {
        private final HandWindow parentWindow;

        public RotationAntiClockwiseAction(HandWindow parentWindow) {
            this.parentWindow = parentWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parentWindow.rotateAntiClockwise();
        }
    }
}
