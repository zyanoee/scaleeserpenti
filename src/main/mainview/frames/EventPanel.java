package main.mainview.frames;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EventPanel extends JPanel {
    private JTextArea logTextArea;

    public EventPanel() {
        setLayout(new BorderLayout());
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void aggiungiLog(String messaggio) {
        logTextArea.append(messaggio + "\n");
        JScrollBar verticalScrollBar = ((JScrollPane) logTextArea.getParent().getParent()).getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }

    
}
