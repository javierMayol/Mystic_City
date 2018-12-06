import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;

/**
 * A JFrame containing panels to interact with the game engine.
 * Construction has been subdivided to clear the code.
 * Command handling has been delegated to Command Handlers.
 * Implements UserInterface, outputting to a graphical text area.
 */

public class GraphicalInterface extends JFrame implements UserInterface {
    protected JPanel mainPanel, commandsPanel, consolePanel;
    protected JTextPane consoleTextPane;

    public GraphicalInterface(int width, int height) {
        mainPanel = createMainPanel(width, height);

        commandsPanel = createCommandsPanel(width, height * 3 / 4);
        consolePanel = createConsolePanel(width, height / 4);

        commandsPanel.add(createGoPanel());
        commandsPanel.add(createGetPanel());
        commandsPanel.add(createStatusPanel());
        commandsPanel.add(createUsePanel());
        commandsPanel.add(createPlayerInteractionPanel());

        consoleTextPane = createConsoleTextPane();
        consolePanel.add(createConsoleScrollPane(consoleTextPane));

        mainPanel.add(commandsPanel);
        mainPanel.add(consolePanel);

        this.add(mainPanel);
        this.pack();
    }

    protected JPanel createPlayerInteractionPanel() {
        JPanel playerInteractionPanel = new JPanel();
        playerInteractionPanel.setLayout(new BoxLayout(playerInteractionPanel, BoxLayout.PAGE_AXIS));

        JLabel playerNameLabel = new JLabel("Player name: ");
        JTextField playerNameField = new JTextField(20);

        JButton talkButton = new JButton("Talk");
        JButton askButton = new JButton("Ask");
        JButton tradeButton = new JButton("Trade");

        PlayerInteractionCommandHandler piCmdHandler = new PlayerInteractionCommandHandler(playerNameField, this);
        talkButton.addActionListener(piCmdHandler);
        askButton.addActionListener(piCmdHandler);
        tradeButton.addActionListener(piCmdHandler);

        JPanel textPanel = new JPanel(); 
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 10, 5));

        JPanel buttonsPanel = new JPanel(); 
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 40, 5));

        textPanel.add(playerNameLabel);
        textPanel.add(playerNameField);

        buttonsPanel.add(talkButton);
        buttonsPanel.add(askButton);
        buttonsPanel.add(tradeButton);

        playerInteractionPanel.add(textPanel);
        playerInteractionPanel.add(buttonsPanel);

        return playerInteractionPanel;
    }

    protected JPanel createUsePanel() {
        JPanel usePanel = new JPanel();
        usePanel.setLayout(new BoxLayout(usePanel, BoxLayout.LINE_AXIS));

        JTextField artifactNameField = new JTextField(20);
        artifactNameField.setText("Artifact name");
        JTextField directionField = new JTextField(20);
        directionField.setText("Direction");
        JLabel openLabel = new JLabel("Open");
        openLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        JCheckBox openCheckBox = new JCheckBox();
        JButton useButton = new JButton("Use");

        UseCommandHandler useCmdHandler = new UseCommandHandler(this, artifactNameField, directionField, openCheckBox);
        useButton.addActionListener(useCmdHandler);

        JPanel useTextFieldsPanel = new JPanel();
        useTextFieldsPanel.setLayout(new BoxLayout(useTextFieldsPanel, BoxLayout.PAGE_AXIS));
        useTextFieldsPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

        JPanel useButtonsPanel = new JPanel();
        useButtonsPanel.setLayout(new BoxLayout(useButtonsPanel, BoxLayout.LINE_AXIS));

        useTextFieldsPanel.add(artifactNameField);
        useTextFieldsPanel.add(directionField);

        useButtonsPanel.add(useButton);
        useButtonsPanel.add(openLabel);
        useButtonsPanel.add(openCheckBox);

        usePanel.add(useTextFieldsPanel);
        usePanel.add(useButtonsPanel);

        return usePanel;
    }

    protected JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20));

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(new InventoryCommandHandler(this));
        inventoryButton.setMargin(new Insets(20, 20, 20, 20));
        JButton lookButton = new JButton("Look");
        lookButton.addActionListener(new LookCommandHandler(this));
        lookButton.setMargin(new Insets(20, 20, 20, 20));

        statusPanel.add(inventoryButton, BorderLayout.PAGE_START);
        statusPanel.add(lookButton, BorderLayout.PAGE_END);

        return statusPanel;
    }

    protected JPanel createGetPanel() {

        JPanel getPanel = new JPanel();
        getPanel.setLayout(new BoxLayout(getPanel, BoxLayout.LINE_AXIS));
        getPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));

        JTextField getField = new JTextField(20);
        getField.setText("Name of artifact");
        JButton getButton = new JButton("Get");

        GetCommandHandler getCmdHandler = new GetCommandHandler(getField, this);
        getButton.addActionListener(getCmdHandler);

        getPanel.add(getField);
        getPanel.add(getButton);

        return getPanel;
    }

    protected JPanel createGoPanel() {
        GoCommandHandler goCmdHandler = new GoCommandHandler(this);

        JPanel goPanel = new JPanel(new GridLayout(3, 3));
        goPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel goLabel = new JLabel("Go", JLabel.CENTER);

        JButton northButton = new JButton("North");
        northButton.addActionListener(goCmdHandler);
        JButton northEastButton = new JButton("North-east");
        northEastButton.addActionListener(goCmdHandler);
        JButton eastButton = new JButton("East");
        eastButton.addActionListener(goCmdHandler);
        JButton southEastButton = new JButton("South-east");
        southEastButton.addActionListener(goCmdHandler);
        JButton southButton = new JButton("South");
        southButton.addActionListener(goCmdHandler);
        JButton southWestButton = new JButton("South-west");
        southWestButton.addActionListener(goCmdHandler);
        JButton westButton = new JButton("West");
        westButton.addActionListener(goCmdHandler);
        JButton northWestButton = new JButton("North-west");
        northWestButton.addActionListener(goCmdHandler);

        goPanel.add(northWestButton);
        goPanel.add(northButton);
        goPanel.add(northEastButton);
        goPanel.add(westButton);
        goPanel.add(goLabel);
        goPanel.add(eastButton);
        goPanel.add(southWestButton);
        goPanel.add(southButton);
        goPanel.add(southEastButton);

        return goPanel;
    }

    protected JPanel createMainPanel(int width, int height) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.setPreferredSize(new Dimension(width, height));
        return mainPanel;
    }

    protected JPanel createCommandsPanel(int width, int height) {
        JPanel commandsPanel = new JPanel(new GridLayout(3, 3));
        commandsPanel.setPreferredSize(new Dimension(width, height));
        return commandsPanel;
    }

    protected JPanel createConsolePanel(int width, int height) {
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setPreferredSize(new Dimension(width, height));
        return consolePanel;
    }

    protected JTextPane createConsoleTextPane() {
        JTextPane consoleTextPane = new JTextPane();
        consoleTextPane.setEditable(false);
        return consoleTextPane;
    }

    protected JScrollPane createConsoleScrollPane(JTextPane consolTextPane) {
        JScrollPane consoleScrollPane = new JScrollPane(consoleTextPane);
        return consoleScrollPane;
    }

    public void display(String str) {
        StyledDocument conDoc = consoleTextPane.getStyledDocument();

        try {
            conDoc.insertString(conDoc.getLength(), str, null);
        } catch(BadLocationException e) { System.err.println(e); }
    }

    public void displayPrompt(String promptString) {
        System.out.println(promptString);
    }

    // TODO: It doesn't seem really related to GUI as far as i understood. To be implemented.
    public String getLine() {
        return "";
    }
}