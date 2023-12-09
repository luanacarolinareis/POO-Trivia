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
     * ObjectOutputStream (para escrita de ficheiro .dat)
     */
    private ObjectOutputStream ooS;

    /**
     * ObjectInputStream oiS (para leitura de ficheiro .dat);
     */
    private ObjectInputStream oiS;

    /**
     * Armazena informação sobre todas as perguntas
     */
    private final ArrayList<String> allQuestions = new ArrayList<>();

    /**
     * Arrmazena a leitura de uma linha de ficheiro
     */
    private String line;

    /**
     * Construtor default
     */
    Ficheiros() {}

    /**
     * Abre o ficheiro para leitura de perguntas
     * @param file Ficheiro a ler
     * @return Todas as perguntas
     * @throws IOException Exceções
     */
    private ArrayList<String> openRead(File file) throws IOException {
        FileReader fR = new FileReader(file);
        bR = new BufferedReader(fR);
        while((line = bR.readLine()) != null) {  // Leitura, enquanto houver conteúdo para ler
            allQuestions.add(line);
        }
        closeRead();                            // Fecha o ficheiro para leitura
        return allQuestions;                    // Devolve todas as perguntas lidas
    }

    /**
     * Fechar o ficheiro para leitura de perguntas
     * @throws IOException Exceções
     */
    private void closeRead() throws IOException { bR.close(); }

    /**
     * Abrir o ficheiro para escrita de resultados
     * @param file Ficheiro a escrever
     * @param dateAndTime Data e hora
     * @param playerName Nome do jogador
     * @param right Perguntas certas
     * @param wrong Perguntas erradas
     * @throws IOException Exceções
     */
    private void openWriteGame(File file, String dateAndTime, String playerName, ArrayList<Pergunta> right, ArrayList<Pergunta> wrong) throws IOException {
        FileOutputStream foS = new FileOutputStream(file);
        ooS = new ObjectOutputStream(foS);
        ooS.writeObject("Data e hora: " + dateAndTime);                                 // Escreve a data e hora de sistema
        ooS.writeObject("Nome do jogador: " + playerName);                              // Escreve o nome do jogador
        ooS.writeObject("Perguntas certas: ");
        for (Pergunta r: right) { ooS.writeObject(r.getArea() + ";"+ r.getPhrase()); }  // Escreve todas as perguntas certas
        ooS.writeObject("Perguntas erradas: ");
        for (Pergunta w: wrong) { ooS.writeObject(w.getPhrase()); }                     // Escreve todas as perguntas erradas
        closeWriteGame();                                                               // Fecha o ficheiro para escrita
    }

    /**
     * Fechar o ficheiro para escrita de resultados
     * @throws IOException Exceções
     */
    private void closeWriteGame() throws IOException { ooS.close(); }

    /**
     * Abrir o ficheiro para leitura de um jogo
     * @param file Ficheiro de jogo a ler
     * @param name Nome do jogador
     * @param dateTime Data e hora
     * @return Perguntas certas
     */
    private ArrayList<String> openReadGame(File file, StringBuilder name, StringBuilder dateTime) {
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
            throw new RuntimeException(ex);
        }
        return rightQuestions;
    }

    /**
     * Fechar o ficheiro para leitura de um jogo
     * @throws IOException Exceções
     */
    private void closeReadGame() throws IOException { oiS.close(); }

    /**
     * Getter
     * @param file Ficheiro a ler
     * @return Todas as perguntas
     * @throws IOException Exceções na leitura
     */
    public ArrayList<String> read(File file) throws IOException { return openRead(file);}

    /**
     * Getter
     * @param file Ficheiro a escrever
     * @param dateAndTime Data e hora
     * @param playerName Nome do jogador
     * @param right Perguntas certas
     * @param wrong Perguntas erradas
     * @throws IOException Exceções
     */
    public void writeGame(File file, String dateAndTime, String playerName, ArrayList<Pergunta> right, ArrayList<Pergunta> wrong) throws IOException {
        openWriteGame(file, dateAndTime, playerName, right, wrong);
    }

    /**
     * Getter
     * @param file Ficheiro de jogo a ler
     * @param name  Nome do jogador
     * @param dateTime Data e hora
     * @return Perguntas certas
     */
    public ArrayList<String> readGame(File file, StringBuilder name, StringBuilder dateTime) { return openReadGame(file, name, dateTime); }
}
