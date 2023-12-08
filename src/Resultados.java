import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.nio.file.*;

class Resultados implements Serializable {
    /**
     * Data e hora no formato "yyyy-MM-dd HH:mm"
     */
    protected String dateTime;

    /**
     * Nome do jogador
     */
    protected String playerName;

    /**
     * Juntar data e hora para criar nome de ficheiro
     */
    protected String joinedDate;

    /**
     * Iniciais do nome completo do jogador (uppercase)
     */
    protected String initials;

    /**
     * Utilizado para efeitos de leitura e escrita de ficheiros
     */
    protected Ficheiros files = new Ficheiros();

    /**
     * Ficheiro
     */
    protected File fr;

    /**
     * Pontuação de jogo
     */
    protected int gameScore;

    /**
     * Construtor
     * @param dateTime Data e hora
     * @param playerName Nome do jogador
     */
    Resultados(String dateTime, String playerName) {
        this.dateTime = dateTime;
        this.playerName = playerName;
        joinDate();
        setInitials();

        String folder = "Games";
        String fileName = "pootrivia_jogo_" + this.joinedDate + "_" + this.initials.toUpperCase() + ".dat";

        // Obtém o caminho relativo ao diretório de trabalho atual
        Path relativePath = Paths.get(folder);

        // Se a pasta games ainda não foi criada, cria uma para armazenar os dados de jogo
        if  (!Files.exists(relativePath)) {
            try {
                Files.createDirectories(relativePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        fr = new File(folder + "/" + fileName);
    }

    /**
     * Junta a data e hora
     */
    private void joinDate() { this.joinedDate = dateTime.replaceAll("[^0-9]", ""); }

    /**
     * Define as iniciais do nome
     */
    private void setInitials() {
        char[] nameArray = this.playerName.toCharArray();
        ArrayList<Character> initialsArray = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            char c = nameArray[i];
            if (Character.isLetter(c) && ((i == 0) || (nameArray[i - 1] == ' '))) { initialsArray.add(c); }
        }

        StringBuilder stringBuilder = new StringBuilder(initialsArray.size());
        for (char c : initialsArray) { stringBuilder.append(c); }
        this.initials = stringBuilder.toString();
    }

    /**
     * Criação e escrita de um ficheiro objeto com os resultados
     */
    protected void generateResultFile(String dateAndTime, String playerName, ArrayList<String> right, ArrayList<String> wrong) {
        try {
            files.openWriteGame(fr, dateAndTime, playerName, right, wrong);
            files.closeWriteGame();
        } catch(Exception ex1){
            // ex1.printStackTrace();
        }
    }

    /**
     * Leitura de um ficheiro objeto com os resultados
     */
    protected void readResultFile() {
        if (fr != null) { files.openReadGame(fr); }
    }

    /**
     * Calcula a pontuação de jogo, através da lista de perguntas acertadas
     */
    private void gameScore() {}
}
