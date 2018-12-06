import java.awt.event.*;
import javax.swing.*;

public class GoCommandHandler implements ActionListener {
    protected UserInterface ui;

    public GoCommandHandler(UserInterface ui) {
        this.ui = ui;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        switch(button.getText()) {
            case "North" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO North\n");
                break;
            case "North-east" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO North-east\n");
                break;
            case "North-west" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO North-west\n");
                break;
            case "East" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO East\n");
                break;
            case "West" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO West\n");
                break;
            case "South" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO South\n");
                break;
            case "South-east" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO South-east\n");
                break;
            case "South-west" : 
                // Placeholder. Should be replaced by an actual game command.
                ui.display("GO South-west\n");
                break;
        }
    }
}