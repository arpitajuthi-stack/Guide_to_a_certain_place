package guide;

import javax.swing.SwingUtilities;

public class DirectoryBook {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });

    }
}