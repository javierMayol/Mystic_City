import java.awt.event.*;
import javax.swing.JTextField;

public class InventoryCommandHandler implements ActionListener {
    protected UserInterface ui;

    public InventoryCommandHandler(UserInterface ui) {
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {
        // Placeholder. Should be replaced by an actual game command.
        ui.display("INVENTORY\n");
    }
}