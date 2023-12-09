import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.nio.file.*;

/**
 * Classe responsável pela escrita de resultados para ficheiros '.dat', numa pasta 'Games'
 */
class Resultados implements Serializable {
    /**
     * Data e hora no formato 'yyyy-MM-dd HH:mm'
     */
    private final String dateTime;

    /**
     * Nome do jogador
     */
    private final String playerName;

    /**
     * Junta a data e hora para criar nome de ficheiro
     */
    private String joinedDate;

    /**
     * Iniciais do nome completo do jogador (uppercase)
     */
    private String initials;

    /**
     * Utilizado para efeitos de escrita de ficheiros
     */
    private final Ficheiros files = new Ficheiros();

    /**
     * Ficheiro
     */
    private final File fr;

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

        // Obtém o caminho relativo do diretório de trabalho atual
        Path relativePath = Paths.get(folder);

        // Se a pasta 'Games' ainda não foi criada, cria uma para armazenar os dados de jogo (+ organização)
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
    private void joinDate() { joinedDate = dateTime.replaceAll("[^0-9]", ""); }

    /**
     * Define as iniciais do nome
     */
    private void setInitials() {
        char[] nameArray = playerName.toCharArray();
        ArrayList<Character> initialsArray = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            char c = nameArray[i];
            if (Character.isLetter(c) && ((i == 0) || (nameArray[i - 1] == ' '))) { initialsArray.add(c); }
        }
        StringBuilder stringBuilder = new StringBuilder(initialsArray.size());
        for (char c : initialsArray) { stringBuilder.append(c); }
        initials = stringBuilder.toString();
    }

    /**
     * Criação e escrita de ficheiro objeto ('.dat'), com os resultados
     * @param dateAndTime Data e hora
     * @param playerName Nome do jogador
     * @param right Perguntas certas
     * @param wrong Perguntas erradas
     */
    private void writeResultFile(String dateAndTime, String playerName, ArrayList<Pergunta> right, ArrayList<Pergunta> wrong) {
        try {
            files.writeGame(fr, dateAndTime, playerName, right, wrong);     // Escreve um ficheiro '.dat'
        } catch(Exception ex1){
            throw new RuntimeException(ex1);
        }
    }

    /**
     * Getter
     * @param dateAndTime Data e hora
     * @param playerName Nome do jogador
     * @param right Perguntas certas
     * @param wrong Perguntas erradas
     */
    public void generateResultFile(String dateAndTime, String playerName, ArrayList<Pergunta> right, ArrayList<Pergunta> wrong) {
        writeResultFile(dateAndTime, playerName, right, wrong);
    }
}
