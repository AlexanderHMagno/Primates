package sanctuary.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Utilities extends JFrame {


    public static void showErrorMessage(String error, Component c) {
        JOptionPane.showMessageDialog(c, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String title, String info, Component c) {
        JOptionPane.showMessageDialog(c, info, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Get the names on Enums as an array of strings
     * @param e enum.class
     * @return return enum as an array of strings
     * Taken from: https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string
     */
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


}
