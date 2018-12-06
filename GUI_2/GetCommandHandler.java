import java.awt.event.*;
import javax.swing.JTextField;

public class GetCommandHandler implements ActionListener {
    protected JTextField textField;
    protected UserInterface ui;

    public GetCommandHandler(JTextField textField, UserInterface ui) {
        this.textField = textField;
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {
        // Placeholder. Should be replaced by an actual game command.
        ui.display("GET " + textField.getText() + "\n");
    }
}