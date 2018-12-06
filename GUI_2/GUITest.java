import javax.swing.*;

public class GUITest {
    public static void main(String[] args) {
        System.out.println("Launching test GUI...");
         
        GraphicalInterface guiInterface = new GraphicalInterface(860, 640);
        guiInterface.setVisible(true);
    }
}