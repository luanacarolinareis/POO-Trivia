import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Ordena as pontuações de jogos
 */
class Top {
    /**
     * Todas as perguntas corretas, referentes a um jogo
     */
    private ArrayList<String> rightQuestions;

    /**
     * Nome do jogador
     */
    private final StringBuilder name = new StringBuilder();

    /**
     * Pontuação total de um jogo
     */
    private int gameScore;

    /**
     * Data e hora
     */
    private final StringBuilder dateTime = new StringBuilder();

    /**
     * Utilizado para efeitos de leitura de ficheiros
     */
    private final Ficheiros files = new Ficheiros();

    /**
     * Array de ficheiros
     */
    private File[] gameFiles;

    /**
     * Pergunta instanciada conforme a área
     */
    private Pergunta p;

    /**
     * Informação final a enviar (TOP 3)
     */
    private final ArrayList<ArrayList<String>> topInfo = new ArrayList<>();

    /**
     * Construtor default
     */
    Top() {}

    /**
     * Função responsável por guardar todos os ficheiros objeto existentes para uma lista temporária
     */
    private void saveAllToMemory() {
        // Diretório onde estão os arquivos
        String folder = "Games";
        File fr = new File(folder);

        // Se existe e é diretório, 'gameFiles' vai receber todos os ficheiros nesse diretório
        if (fr.exists() && fr.isDirectory()) { gameFiles = fr.listFiles(); }
    }

    /**
     * Calcula a pontuação de um jogo, com base nas perguntas certas e erradas presentes no ficheiro
     * @param gameFile Ficheiro de jogo
     * @return Pontuação de jogo
     */
    private int extractGameScore(File gameFile) {
        name.setLength(0);      // Reseta o nome
        dateTime.setLength(0);  // Reseta a data e hora
        gameScore = 0;          // Reseta a pontuação
        String[] splitted;

        if (gameFile != null) { rightQuestions = files.readGame(gameFile, name, dateTime); }
        for (String right: rightQuestions) {
            splitted = right.split(";");
            switch (splitted[0]) {  // Verifica o tipo da pergunta
                case "Artes": p = new Artes(); break;
                case "Ciências": p = new Ciencias(); break;
                case "Futebol": p = new Futebol(); break;
                case "Natação": p = new Natacao(); break;
                case "Ski": p = new Ski(); break;
                default: System.exit(0);
            }
            gameScore += p.getScore();  // Obtém a pontuação, segundo as regras de majoração
        }
        return gameScore;
    }

    /**
     * Calcula a pontuação para cada jogo e ordena 'gameFiles' por ordem decrescente da mesma
     */
    private void defineTopScores() {
       saveAllToMemory();   // Primeiramente, guarda todos os jogos numa lista temporária
       int actualScore = extractGameScore(gameFiles[gameFiles.length - 1]);     // Pontuação do jogo atual

       // Ordenar 'gameFiles', com base nas pontuações
       Arrays.sort(gameFiles, Comparator.comparingInt(this::extractGameScore).reversed());

       // Extrair os três melhores jogos (ou menos, caso ainda não existam 3 jogos)
       int topGames = Math.min(3, gameFiles.length);
       ArrayList<String> temp = new ArrayList<>();

       for (int i = 0; i < topGames; i++) {
           gameScore = extractGameScore(gameFiles[i]);
           temp.add(String.valueOf(name));
           temp.add(String.valueOf(gameScore));
           temp.add(String.valueOf(dateTime));
           topInfo.add(temp);
           temp = new ArrayList<>();    // Reseta a lista temporária
       }
       temp.add(String.valueOf(actualScore));   // Adiciona a pontuação de jogo atual à lista
       topInfo.add(temp);
    }

    /**
     * Devolve informação sobre os 3 melhores jogos
     * @return TOP 3 + Pontuação de jogo atual
     */
    public ArrayList<ArrayList<String>> getTopScores() {
        defineTopScores();
        return topInfo;
    }
}
