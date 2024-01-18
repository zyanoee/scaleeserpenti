package config.configutility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import entities.SpecialCellType;
import entities.concreteclass.concreteCells.CardCell;
import entities.concreteclass.concreteCells.LocandaCell;
import entities.concreteclass.concreteCells.MollaCell;
import entities.concreteclass.concreteCells.RerollCell;
import entities.concreteclass.concreteCells.ScalaCell;
import entities.concreteclass.concreteCells.SerpenteCell;
import entities.concreteclass.concreteCells.StopCell;
import entities.interfaces.Cell;
import entities.interfaces.GameBoardInterface;

public class ConfigFileHandler {

    public static void writeConfiguration(File file, GameBoardInterface gboard) throws FileNotFoundException{
        try (PrintWriter writer = new PrintWriter(file)) {
            GameConfig configModel = GameConfig.getInstance();
            configModel.writeConfiguration(writer);
        }
        ConfigFileHandler.writeBoard(file, gboard);
    }

    public static void saveConfiguration(JFrame mainframe, GameBoardInterface gboard){
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
                    ConfigFileHandler.writeConfiguration(fileToSave, gboard);
                    
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(mainframe, "File non salvato correttamente.", "Errore", JOptionPane.ERROR_MESSAGE);
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

    public static GameBoardInterface loadConfiguration(JFrame mainframe){
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
               JOptionPane.showMessageDialog(mainframe, "Scegli un file di Configurazione Valido", "Errore", JOptionPane.ERROR_MESSAGE);
               return null;
            }
            try{
                ConfigFileHandler.readConfiguration(fileToSave);
                return ConfigFileHandler.readBoard(fileToSave);
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(mainframe, "File di Configurazione non valido o corrotto", "Errore", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e){
                JOptionPane.showMessageDialog(mainframe, "File di Configurazione non valido o corrotto", "Errore", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException n){
                JOptionPane.showMessageDialog(mainframe, "File di Configurazione non valido o corrotto", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
            return null;
            
    }

    public static void writeBoard(File file, GameBoardInterface gboard){

         try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println("GRIGLIA");
            writer.println(gboard != null ? "SI" : "NO");
            if(gboard !=null){
                Cell[][] grid = gboard.getGrid();
                for(int i=0;i<gboard.getGridSizeX();i++){
                    for(int j=0;j<gboard.getGridSizeY();j++){
                        if(grid[i][j].isSpecial()){
                            writer.println(grid[i][j].getType().toString());
                            writer.println(grid[i][j].getPositionX());
                            writer.println(grid[i][j].getPositionY());

                            if(grid[i][j].getType()==SpecialCellType.SCALA){
                                ScalaCell scala = (ScalaCell) grid[i][j];
                                writer.println(scala.getUpperCell().getType().toString());
                                writer.println(scala.getUpperCell().getPositionX());
                                writer.println(scala.getUpperCell().getPositionY());
                            }
                            if(grid[i][j].getType()==SpecialCellType.SERPENTE){
                                SerpenteCell serpente = (SerpenteCell) grid[i][j];
                                writer.println(serpente.getLowerCell().getType().toString());
                                writer.println(serpente.getLowerCell().getPositionX());
                                writer.println(serpente.getLowerCell().getPositionY());
                            }
                        }
                    }
                }
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public static GameBoardInterface readBoard(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { 
            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.equals("GRIGLIA")) {
                    break;
                }
            }
            if (riga == null) {
                return null;
            }

            String secondaRiga = reader.readLine();
            GameBoardInterface gameBoard = secondaRiga.equals("SI") ? new GameBoard(GameConfig.getInstance()) : null;
            if(gameBoard!=null){

                String tipo;
                while((tipo = reader.readLine()) !=null){
                Cell[][] grid = gameBoard.getGrid();
                int posX = Integer.parseInt(reader.readLine());
                int posY = Integer.parseInt(reader.readLine());
                switch(tipo){
                    case "SCALA":
                        ScalaCell scell = new ScalaCell(posX, posY);
                        reader.readLine();
                        int x = Integer.parseInt(reader.readLine());
                        int y = Integer.parseInt(reader.readLine());
                        scell.setUpperCell(grid[x][y]);
                        grid[posX][posY] = scell;
                        System.out.println("Aggiunta una Scala");
                        break;
                    case "SERPENTE":
                        SerpenteCell serpCell = new SerpenteCell(posX, posY);
                        reader.readLine();
                        x = Integer.parseInt(reader.readLine());
                        y = Integer.parseInt(reader.readLine());
                        serpCell.setLowerCell(grid[x][y]);
                        grid[posX][posY] = serpCell;
                        System.out.println("Aggiunta un Serpente");
                        break;
                    case "CARTA":
                        CardCell cardCell = new CardCell(posX, posY);
                        grid[posX][posY] = cardCell;
                        break;
                    case "REROLL":
                        RerollCell rerollCell = new RerollCell(posX, posY);
                        grid[posX][posY] = rerollCell;
                        break;
                    case "MOLLA":
                        MollaCell mollaCell = new MollaCell(posX, posY);
                        grid[posX][posY] = mollaCell;
                        break;
                    case "BLOCCO":
                        StopCell stopCell = new StopCell(posX, posY);
                        grid[posX][posY] = stopCell;
                        break;
                    case "LOCANDA":
                        LocandaCell locandaCell = new LocandaCell(posX, posY);
                        grid[posX][posY] = locandaCell;
                        break;
                    default:
                        break;
                }
            }
                gameBoard.setSuccessivi();
                return gameBoard;
            }else{
                return null;
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
        }
}
