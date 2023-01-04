package Graphic.Menu;

import Common.IA;
import Common.Player;
import Common.Window.Display;
import Common.Window.MainWindow;
import Misc.WindowManagement;
import Misc.ArrayManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSelectionMenu extends JPanel implements ActionListener {
    private Player[] playersArray;
    private JComboBox<String>[] associatedComboBox;
    private JButton[] associatedButton;
    private JTextField[] associatedJTextField;
    private final JButton buttonDone;
    private final JButton buttonAdd;
    private JScrollPane scrollPane;
    private int deletedPlayer;

    public PlayerSelectionMenu() {
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(new BorderLayout());
        this.playersArray = new Player[0];
        this.deletedPlayer = 0;

        Dimension buttonDimension = new Dimension(Display.BUTTON_WIDTH, Display.BUTTON_HEIGHT);

        buttonAdd = new JButton("Add");
        this.add(buttonAdd, BorderLayout.LINE_START);
        buttonAdd.setSize(buttonDimension);
        buttonAdd.addActionListener(this);

        buttonDone = new JButton();
        this.add(buttonDone, BorderLayout.LINE_END);
        buttonDone.setText("Done");
        buttonDone.setSize(buttonDimension);
        buttonDone.addActionListener(this);

        addPlayer("Player1", false);
        addPlayer("Player2", true);
        updateDisplay();
    }

    public void addPlayer(String name, boolean IA) {
        Player playerToAdd;
        if (IA) {
            playerToAdd = new IA(name);
        } else {
            playerToAdd = new Player(name);
        }
        playersArray = ArrayManagement.addPlayer(playersArray, playerToAdd);
        updateDisplay();
    }

    private void updateDisplay() {
        if (scrollPane != null) {
            this.remove(scrollPane);
        }
        JPanel listedPlayersPanel = new JPanel();
        listedPlayersPanel.setPreferredSize(new Dimension(Display.WIDTH - buttonDone.getWidth() * 2, Display.HEIGHT));
        listedPlayersPanel.setLayout(new GridBagLayout());
        associatedComboBox = new JComboBox[playersArray.length];
        associatedButton = new JButton[playersArray.length];
        associatedJTextField = new JTextField[playersArray.length];
        JTextField textFieldName;
        JComboBox<String> comboBoxIAPlayer;
        String[] items = {"Player", "IA"};
        JPanel tempPanel;
        JButton buttonDelete;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        for (int i = 0; i < playersArray.length; i++) {
            tempPanel = new JPanel();
            tempPanel.setLayout(new GridLayout(1,3, 20, 20));
            tempPanel.setPreferredSize(new Dimension(Display.BUTTON_WIDTH * 2, 20));

            textFieldName = new JTextField(playersArray[i].getName());

            comboBoxIAPlayer = new JComboBox<>(items);
            comboBoxIAPlayer.setSize(textFieldName.getSize());
            if (playersArray[i].isIA()) {
                comboBoxIAPlayer.setSelectedIndex(1);
            } else {
                comboBoxIAPlayer.setSelectedIndex(0);
            }
            comboBoxIAPlayer.addActionListener(this);

            buttonDelete = new JButton("Delete");
            buttonDelete.addActionListener(this);

            tempPanel.add(comboBoxIAPlayer, BorderLayout.LINE_START);
            tempPanel.add(textFieldName, BorderLayout.CENTER);
            tempPanel.add(buttonDelete, BorderLayout.LINE_END);

            listedPlayersPanel.add(tempPanel, gridBagConstraints);
            tempPanel.setVisible(true);
            associatedComboBox[i] = comboBoxIAPlayer;
            associatedButton[i] = buttonDelete;
            associatedJTextField[i] = textFieldName;
        }

        scrollPane = new JScrollPane(listedPlayersPanel);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setLocation(Display.DISTANCE_BETWEEN_BUTTONS, Display.DISTANCE_BETWEEN_BUTTONS + buttonAdd.getX() + buttonAdd.getHeight());
        this.revalidate();
        this.repaint();
    }

    public Player[] getPlayersArray() {
        return playersArray;
    }

    private int getPlayerByComboBox(JComboBox<String> comboBox) {
        for (int i = 0; i < associatedComboBox.length; i++) {
            if (associatedComboBox[i] == comboBox) {
                return i;
            }
        }
        return -1;
    }

    private void removePlayerByButton(JButton source) {
        for (int i = 0; i < associatedButton.length; i++) {
            if (associatedButton[i] == source) {
                playersArray = ArrayManagement.removePlayer(playersArray, i);
                deletedPlayer++;
                break;
            }
        }
        updateDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) source;
            Player newPlayer;
            int nPlayerNumber = getPlayerByComboBox(comboBox);
            if (comboBox.getSelectedItem() == "IA") {
                newPlayer = new IA(playersArray[nPlayerNumber].getName());
            } else{
                newPlayer = new Player(playersArray[nPlayerNumber].getName());
            }
            playersArray[nPlayerNumber] = newPlayer;
        } else {
            JButton buttonSource = (JButton) source;
            updatePlayerName();
            if (buttonSource == buttonAdd) {
                int playerNb = playersArray.length + 1 + deletedPlayer;
                addPlayer("Player" + playerNb, false);
            } else if (buttonSource == buttonDone) {
                MainWindow parent = (MainWindow) WindowManagement.getMasterParentWindow(this);
                parent.setPlayers(this.playersArray);
                parent.switchToMenu(MainWindow.GAME_OPTION_MENU);
            } else {
                removePlayerByButton(buttonSource);
            }
        }
    }

    private void updatePlayerName() {
        for (int i = 0; i < playersArray.length; i++) {
            playersArray[i].setName(associatedJTextField[i].getText());
        }
    }
}
