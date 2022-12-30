package Graphic;

import Common.Display;
import Common.MainWindow;
import Common.WindowManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionMenu extends JPanel implements ActionListener {
    JButton buttonReturn, buttonApply, buttonAutoDetect;
    JComboBox<String> comboBoxScreenSize;

    public OptionMenu() {
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);

        Dimension buttonDimension = new Dimension(Display.BUTTON_WIDTH, Display.BUTTON_HEIGHT);


        int nMinimumSize = 600;
        int nPadding = 50;
        int nSizeNumber = 17;
        int nResolution;
        String[] aScreenSize = new String[nSizeNumber];
        String sSelectedItem = "600x600";
        String sTemp;

        for (int i = 0; i < nSizeNumber ; i++) {
            nResolution = nMinimumSize + nPadding * i;
            sTemp = nResolution + "x" + nResolution;
            aScreenSize[i] = sTemp;
            if (nResolution == Display.WIDTH) {
                sSelectedItem = sTemp;
            }
        }

        buttonAutoDetect = new JButton();
        this.add(buttonAutoDetect);
        buttonAutoDetect.setText("Auto Detect");
        buttonAutoDetect.setSize(buttonDimension);
        buttonAutoDetect.setLocation(Display.CENTER_X - Display.PADDING_X, Display.DISTANCE_BETWEEN_BUTTONS);
        buttonAutoDetect.addActionListener(this);

        JLabel label = new JLabel("Resolution : ");
        this.add(label);
        label.setSize(buttonDimension.width / 2, buttonDimension.height / 2);
        label.setLocation(Display.CENTER_X - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS / 2), Display.CENTER_Y - Display.PADDING_Y - Display.DISTANCE_BETWEEN_BUTTONS - label.getHeight());

        comboBoxScreenSize = new JComboBox<>(aScreenSize);
        this.add(comboBoxScreenSize);
        comboBoxScreenSize.setSize(buttonDimension);
        comboBoxScreenSize.setLocation(Display.CENTER_X - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS / 2), Display.CENTER_Y - Display.PADDING_Y);
        comboBoxScreenSize.setSelectedItem(sSelectedItem);

        buttonApply = new JButton();
        this.add(buttonApply);
        buttonApply.setText("Apply");
        buttonApply.setSize(buttonDimension);
        buttonApply.setLocation(Display.CENTER_X + Display.DISTANCE_BETWEEN_BUTTONS / 2, Display.CENTER_Y - Display.PADDING_Y);
        buttonApply.addActionListener(this);

        buttonReturn = new JButton();
        this.add(buttonReturn);
        buttonReturn.setText("Return");
        buttonReturn.setSize(buttonDimension);
        buttonReturn.setLocation(Display.WIDTH - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS), Display.HEIGHT - (Display.BUTTON_HEIGHT + Display.DISTANCE_BETWEEN_BUTTONS * 2));
        buttonReturn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();

        if (buttonSource == buttonReturn) {
            sIdentifier = MainWindow.MAIN_MENU;
        } else if (buttonSource == buttonApply || buttonSource == buttonAutoDetect) {
            MainWindow parent = (MainWindow) WindowManagement.getMasterParentWindow(this);
            int nWidth, nHeight;
            if (buttonSource == buttonApply) {
                String sComboContent = (String) comboBoxScreenSize.getSelectedItem();
                assert sComboContent != null;
                String[] sSize = sComboContent.split("x");
                if (sSize.length != 2) {
                    JOptionPane.showMessageDialog(parent, "Incorrect size entered. Please put a reasonable size according to your screen definition.");
                    return;
                }
                nWidth = Integer.parseInt(sSize[0]);
                nHeight = Integer.parseInt(sSize[1]);
            } else {
                Dimension oScreenResolution = Toolkit.getDefaultToolkit().getScreenSize();
                int nRoundedTo50_X = (int) (Math.round(oScreenResolution.width / 50.) * 50.);
                int nRoundedTo50_Y = (int) (Math.round(oScreenResolution.height / 50.) * 50.);
                while (nRoundedTo50_X > oScreenResolution.width) {
                    nRoundedTo50_X -= 50;
                }
                while (nRoundedTo50_Y > oScreenResolution.height) {
                    nRoundedTo50_Y -= 50;
                }
                // trick to have a square, but technically we can remove the Math.min test and keep the rounded value if we want a rectangle window
                nWidth = Math.min(nRoundedTo50_X, nRoundedTo50_Y);
                nHeight = Math.min(nRoundedTo50_X, nRoundedTo50_Y);
            }
            parent.Resize(nWidth, nHeight);
            return;
        }

        MainWindow.switchToMenu((JPanel) this.getParent(), sIdentifier);
    }

}
