import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PlayerInteractionCommandHandler implements ActionListener {
    protected JTextField playerNameField;
    protected UserInterface ui;

    public PlayerInteractionCommandHandler(JTextField playerNameTextField, UserInterface ui) {
        this.playerNameField = playerNameTextField;
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {
        // Placeholder. Should be replaced by an actual game command.
        JButton button = (JButton) e.getSource();
        String action = button.getText();

        ui.display(action.toUpperCase() + " " + playerNameField.getText() + "\n");
    }
}