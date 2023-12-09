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
    protected ArrayList<String> openRead(File file) throws IOException {
        FileReader fR = new FileReader(file);
        bR = new BufferedReader(fR);
        while((line = bR.readLine()) != null) {  // Leitura, enquanto houver conteúdo para ler
            allQuestions.add(line);
        }
        closeRead();
        return allQuestions;
    }

    /**
     * Fechar o ficheiro para leitura de perguntas
     * @throws IOException Exceções
     */
    protected void closeRead() throws IOException { bR.close(); }

    /**
     * Abrir o ficheiro para escrita de resultados
     */
    protected void openWriteGame(File file, String dateAndTime, String playerName, ArrayList<Pergunta> right, ArrayList<Pergunta> wrong) throws IOException {
        FileOutputStream foS = new FileOutputStream(file);
        ooS = new ObjectOutputStream(foS);
        ooS.writeObject("Data e hora: " + dateAndTime);
        ooS.writeObject("Nome do jogador: " + playerName);
        ooS.writeObject("Perguntas certas: ");
        for (Pergunta r: right) { ooS.writeObject(r.getArea() + ";"+ r.getPhrase()); }
        ooS.writeObject("Perguntas erradas: ");
        for (Pergunta w: wrong) { ooS.writeObject(w.getPhrase()); }
        closeWriteGame();
    }

    /**
     * Fechar o ficheiro para escrita de resultados
     * @throws IOException Exceções
     */
    protected void closeWriteGame() throws IOException { ooS.close(); }

    /**
     * Abrir o ficheiro para leitura de um jogo
     */
    protected ArrayList<String> openReadGame(File file, StringBuilder name, StringBuilder dateTime) {
        ArrayList<String> rightQuestions = new ArrayList<>();
        try {
            FileInputStream fiS = new FileInputStream(file);
            oiS = new ObjectInputStream(fiS);

            // Leitura dos objetos do arquivo
            while (!(line = oiS.readObject().toString()).equals("Perguntas erradas: ")) {
                if (line.contains("Nome")) name.append(line);
                else if (line.contains("Data")) dateTime.append(line);
                else if (!line.contains("Perguntas")) rightQuestions.add(line);
            }
            closeReadGame();
        } catch (Exception ex) {
            // ex.printStackTrace();
        }
        return rightQuestions;
    }

    /**
     * Fechar o ficheiro para leitura de um jogo
     * @throws IOException Exceções
     */
    protected void closeReadGame() throws IOException { oiS.close(); }
}
