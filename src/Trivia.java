import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * POOTrivia
 *
 * @author Luana Carolina Reis
 * @author Gonçalo Miguel Leitão
 *
 * @version 1.1
 * @since 2023-11-17
 */
public class Trivia {
    /**
     * Main
     * @param args Unused
     */
    public static void main(String[] args) {
        Ficheiros files = new Ficheiros();
        ArrayList<String> allQuestions;         // Todas as perguntas

        // Leitura do ficheiro com as informações de perguntas
        File fr = new File("pootrivia.txt");
        try {
            allQuestions = files.openRead(fr);
            SwingUtilities.invokeLater(() -> {
                GUI gui = new GUI(allQuestions);
                gui.setVisible(true);
            });

        } catch(Exception ex1){
            // ex1.printStackTrace();
        }
    }
}
