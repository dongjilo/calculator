package CustomComponents;

import javax.swing.*;
import java.awt.*;

public class myButton extends JButton {
    Font font = new Font("Open Sans", Font.PLAIN, 16);
    public myButton(String text){
        super(text);
        this.setFocusable(false);
        this.setBackground(Color.decode("#F5F5F5"));
        this.setForeground(Color.decode("#4D4D4D"));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setFont(font);
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#D9D9D9")));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
