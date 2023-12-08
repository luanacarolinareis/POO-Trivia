import java.io.*;
import java.util.ArrayList;

/**
 * Classe auxiliar, para o tratamento de ficheiros
 */
class Ficheiros {
    /**
     * BufferedReader (para leitura de ficheiro .txt)
     */
    private BufferedReader bR;

    /**
     * ObjectOutputStream (para escrita de ficheiro .obj)
     */
    private ObjectOutputStream ooS;

    /**
     * ObjectInputStream oiS;
     */
    private ObjectInputStream oiS;

    /**
     * Armazena informação sobre todas as perguntas
     */
    private final ArrayList<String> allQuestions = new ArrayList<>();

    /**
     * Linha a ler
     */
    private String line;

    /**
     * Abrir o ficheiro para leitura de perguntas
     */
    public ArrayList<String> openRead(File file) throws IOException {
        FileReader fR = new FileReader(file);
        bR = new BufferedReader(fR);
        while((line = bR.readLine()) != null) {  // Leitura, enquanto houver conteúdo para ler
            allQuestions.add(line);
        }
        return allQuestions;
    }

    /**
     * Fechar o ficheiro para leitura de perguntas
     * @throws IOException Exceções
     */
    public void closeRead() throws IOException { bR.close(); }

    /**
     * Abrir o ficheiro para escrita de resultados
     */
    public void openWriteGame(File file, String dateAndTime, String playerName, ArrayList<String> right, ArrayList<String> wrong) throws IOException {
        FileOutputStream foS = new FileOutputStream(file);
        ooS = new ObjectOutputStream(foS);
        ooS.writeObject("Data e hora: " + dateAndTime);
        ooS.writeObject("Nome do jogador: " + playerName);
        ooS.writeObject("Perguntas certas: ");
        for (String r: right) { ooS.writeObject(r); }
        ooS.writeObject("Perguntas erradas: ");
        for (String w: wrong) { ooS.writeObject(w); }
    }

    /**
     * Fechar o ficheiro para escrita de resultados
     * @throws IOException Exceções
     */
    public void closeWriteGame() throws IOException { ooS.close(); }

    /**
     * Abrir o ficheiro para leitura de um jogo
     */
    public ArrayList<String> openReadGame(File file) {
        ArrayList<String> infoGame = new ArrayList<>();
        try {
            FileInputStream fiS = new FileInputStream(file);
            oiS = new ObjectInputStream(fiS);

            // Leitura dos objetos do arquivo
            while ((line = oiS.readObject().toString()) != null) {
                infoGame.add(line);
                System.out.println(line);
            }
            closeReadGame();
        } catch (Exception ex) {
            // ex.printStackTrace();
        }
        return infoGame;
    }

    /**
     * Fechar o ficheiro para leitura de um jogo
     * @throws IOException Exceções
     */
    public void closeReadGame() throws IOException { oiS.close(); }
}
