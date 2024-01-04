package config.configutility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import config.configmodels.GameConfig;

public class ConfigFileHandler {

    public static void writeConfiguration(File file) throws FileNotFoundException{
        try (PrintWriter writer = new PrintWriter(file)) {
            GameConfig configModel = GameConfig.getInstance();
            configModel.writeConfiguration(writer);
        }
    }

    public static void saveConfiguration(JFrame mainframe){
        int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi salvare la configurazione?", "Salva Configurazione",
            JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleziona il percorso per salvare la configurazione");
             fileChooser.addPropertyChangeListener(JFileChooser.DIRECTORY_CHANGED_PROPERTY, evt -> {
            if (JFileChooser.CANCEL_SELECTION.equals(evt.getNewValue())) {
                SwingUtilities.getWindowAncestor(fileChooser).dispose();
            }
            });
            int userSelection = fileChooser.showSaveDialog(mainframe);
            

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String fileName = fileToSave.getName();
                String newFileName = fileName.replaceAll("\\..*$", "") + ".cfg";
                fileToSave = new File(fileToSave.getParentFile(), newFileName);

                    if (fileToSave.exists()) {
                        int overwriteChoice = JOptionPane.showConfirmDialog(mainframe,
                                "Il file esiste già. Vuoi sovrascriverlo?", "Conferma sovrascrittura",
                                JOptionPane.YES_NO_OPTION);
        
                        if (overwriteChoice == JOptionPane.NO_OPTION) {
                            return;
                        }
                    }
                
                try {
                    ConfigFileHandler.writeConfiguration(fileToSave);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File non salvato correttamente.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
    }

    public static void readConfiguration(File file) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            GameConfig configModel = GameConfig.getInstance();
            configModel.readConfiguration(reader);
        }
            
    }

    public static void loadConfiguration(JFrame mainframe){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleziona il File di configurazione");

            fileChooser.addPropertyChangeListener(JFileChooser.DIRECTORY_CHANGED_PROPERTY, evt -> {
            if (JFileChooser.CANCEL_SELECTION.equals(evt.getNewValue())) {
                SwingUtilities.getWindowAncestor(fileChooser).dispose();
            }
            });

            int userSelection = fileChooser.showSaveDialog(mainframe);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String fileName = fileToSave.getName();
            if(!fileName.endsWith(".cfg")){
               JOptionPane.showMessageDialog(null, "Scegli un file di Configurazione Valido", "Errore", JOptionPane.ERROR_MESSAGE);
               return;
            }
            try{
                ConfigFileHandler.readConfiguration(fileToSave);
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(null, "File di Configurazione non valido o corrotto", "Errore", JOptionPane.ERROR_MESSAGE);

            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "File di Configurazione non valido o corrotto", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
            
    }
}
