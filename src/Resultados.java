import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

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
    protected Ficheiros files;

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
    protected File generateResultsFile(String dateAndTime, String playerName, ArrayList<String> right, ArrayList<String> wrong) {
        files = new Ficheiros();
        joinDate();
        setInitials();

        File fr = new File("pootrivia_jogo_" + this.joinedDate + "_" + this.initials.toUpperCase() + ".dat");
        try {
            files.openWrite(fr, dateAndTime, playerName, right, wrong);
            files.closeWrite();
        } catch(Exception ex1){
            // ex1.printStackTrace();
        }
        return fr;
    }

    /**
     * Leitura de um ficheiro objeto com os resultados
     */
    private void readResultsFile() {

    }

    /**
     * Calcula a pontuação de jogo, através da lista de perguntas acertadas
     */
    private void gameScore() {

    }
}