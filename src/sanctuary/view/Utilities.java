package sanctuary.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.util.Arrays;

public class Utilities extends JFrame {


    public static void showErrorMessage(String error, Component c) {
        JOptionPane.showMessageDialog(c, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String title, String info, Component c) {
        JOptionPane.showMessageDialog(c, info, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static JTextArea bioPanel(){
        JTextArea bioPanel = new JTextArea();
        bioPanel.setSize(300, 200);
        bioPanel.setLocation(800, 200);
        bioPanel.setEnabled(false);
        bioPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5,5,5,5),
                BorderFactory.createCompoundBorder(
                    BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.lightGray),
                    BorderFactory.createEmptyBorder(10,10,10,10)
                )
        ));

        bioPanel.setOpaque(true);

        return bioPanel;
    }

    /**
     * Get the names on Enums as an array of strings
     * @param e enum.class
     * @return return enum as an array of strings
     * Taken from: <a href="https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string">...</a>
     */
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


    public static Component createTitle(String text) {
        JLabel title = new JLabel(text);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);
        return title;
    }

    public static JTextArea createContainer() {
        JTextArea panel = new JTextArea(200,100);
        panel.setLayout(null);
        panel.setEnabled(false);
        panel.setOpaque(false);

        return panel;
    }

    public static JPanel createGridRooms() {
        JPanel room = new JPanel(new GridLayout(4, 4, 4, 4));
        room.setSize(400, 300);
        room.setLocation(200,  200);
        return room;
    }

    public static JButton addButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setSize(200, 30);
        button.setLocation(x, y);

        return button;
    }
}
