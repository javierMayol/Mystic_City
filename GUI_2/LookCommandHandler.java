import java.awt.event.*;
import javax.swing.JTextField;

public class LookCommandHandler implements ActionListener {
    protected UserInterface ui;

    public LookCommandHandler(UserInterface ui) {
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {
        // Placeholder. Should be replaced by an actual game command.
        ui.display("LOOK\n");
    }
}