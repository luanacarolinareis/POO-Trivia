import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * POAO Trivia
 *
 * @author Luana Carolina Reis
 *
 * @version 1.1
 * @since 2023-11-17
 */
public class Trivia {
    /**
     * Construtor default
     */
    Trivia() {}

    /**
     * Main
     * @param args Unused
     */
    public static void main(String[] args) {
        Ficheiros files = new Ficheiros();
        ArrayList<String> allQuestions;                  // Todas as perguntas

        File fr = new File("pootrivia.txt");   // Leitura do ficheiro com as informações de perguntas
        try {
            allQuestions = files.read(fr);
            SwingUtilities.invokeLater(() -> {
                GUI gui = new GUI(allQuestions);       // Invoca a 'interface' gráfica
                gui.setVisible(true);
            });
        } catch(Exception ex1){
            throw new RuntimeException(ex1);
        }
    }
}

