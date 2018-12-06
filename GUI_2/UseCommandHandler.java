import javax.swing.*;
import java.awt.event.*;

public class UseCommandHandler implements ActionListener {
    protected UserInterface ui;
    protected JTextField artifactNameField, directionField;
    protected JCheckBox openCheckBox;

    public UseCommandHandler(UserInterface ui, JTextField artifactNameField, JTextField directionField, JCheckBox openCheckBox) {
        this.ui = ui;
        this.artifactNameField = artifactNameField;
        this.directionField = directionField;
        this.openCheckBox = openCheckBox;
    }

    public void actionPerformed(ActionEvent e) {
        // Placeholder. Should be replaced by an actual game command.
        boolean open = openCheckBox.isSelected();
        String artifactName = artifactNameField.getText();
        String direction = directionField.getText();

        ui.display("USE " + (open ? "open " : "") + artifactName + " " + direction + "\n");
    }
}