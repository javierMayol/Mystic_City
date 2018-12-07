import java.awt.*;
import java.awt.event.*;

public class GUI_2 extends Frame implements UserInterface{

        private Label go;
        private Label get;
        private Label use;
        private Label drop;

        private TextField t_go;
        private TextField t_get;
        private TextField t_use;
        private TextField t_drop;

        private Button look;
        private Button inve;
        private Button quit;

        private TextArea dis_area;

        private String cmd = "";
        private keyboardScanner keyboard;

        public GUI_2(){
                setLayout(new FlowLayout());


                go = new Label("GO");
                add(go);
                t_go = new TextField(10);
                t_go.setEditable(true);
                add(t_go);

	        get = new Label("GET");
                add(get);
                t_get = new TextField(10);
                t_get.setEditable(true);
                add(t_get);

                use = new Label("USE");
                add(use);
                t_use = new TextField(10);
                t_use.setEditable(true);
                add(t_use);

                drop = new Label("DROP");
                add(drop);
                t_drop = new TextField(10);
                t_drop.setEditable(true);
                add(t_drop);

                look = new Button("LOOK");
                add(look);

                inve = new Button("INVE");
                add(inve);

                quit = new Button("QUIT");
                add(quit);

                dis_area = new TextArea(50,50);
                add(dis_area);



//              setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(550, 450);
                setVisible(true);

        }


        public void display(String s)
        {
                dis_area.setText(s);
        }

        public void displayPrompt(String s)
        {
                setTitle(s);
        }

        public String getLine()
        {
                String out = cmd;
                if(out.equalsIgnoreCase("GET"))
                        return out +" leather bag";
                else if(out.equalsIgnoreCase("GO"))
                {
                        out = "GO N";
                        return out;
                }
                else if(out.equalsIgnoreCase("TALK"))
                        return out + " N";
                else if(out.equalsIgnoreCase("USE"))
                        return out + " N";
                return out;
        }
}

