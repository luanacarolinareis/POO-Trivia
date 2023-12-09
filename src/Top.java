import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Ordena as pontuações de jogos
 */
class Top extends Ficheiros {
    /**
     * Todas as perguntas corretas, referentes a um jogo
     */
    private ArrayList<String> rightQuestions;

    /**
     * Nome do jogador
     */
    protected StringBuilder name = new StringBuilder();

    /**
     * Pontuação total de um jogo
     */
    private int gameScore;

    /**
     * Data e hora
     */
    protected StringBuilder dateTime = new StringBuilder();

    /**
     * Utilizado para efeitos de leitura e escrita de ficheiros
     */
    protected Ficheiros files = new Ficheiros();

    /**
     * Array de ficheiros
     */
    private File[] gameFiles;

    /**
     * Variável que representa uma pergunta
     * @see Pergunta Pergunta instanciada conforme a área
     */
    private Pergunta p;

    /**
     * Informação final a enviar (TOP 3)
     */
    protected ArrayList<ArrayList<String>> topInfo = new ArrayList<>();

    /**
     * Vai devolver os três melhores jogos
     */
    public ArrayList<ArrayList<String>> getTopScores() {
        defineTopScores();
        return topInfo;
    }

   private void defineTopScores() {
       saveAllToMemory();   // Primeiramente, guarda todos os jogos numa lista temporária
       int actualScore = extractGameScore(gameFiles[gameFiles.length - 1]);   // Pontuação do jogo atual

       // Ordenar os gameFiles com base nas pontuações
       Arrays.sort(gameFiles, Comparator.comparingInt(this::extractGameScore).reversed());

       // Exibir os três melhores jogos
       int topGames = Math.min(3, gameFiles.length);
       ArrayList<String> temp = new ArrayList<>();

       for (int i = 0; i < topGames; i++) {
           gameScore = extractGameScore(gameFiles[i]);
           temp.add(String.valueOf(name));
           temp.add(String.valueOf(gameScore));
           temp.add(String.valueOf(dateTime));
           topInfo.add(temp);
           temp = new ArrayList<>();
       }
       temp.add(String.valueOf(actualScore));
       topInfo.add(temp);
   }

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
     * Calcula a pontuação de um jogo, com base nas perguntas acertadas e erradas presentes no ficheiro
     */
    private int extractGameScore(File gameFile) {
        name.setLength(0);
        dateTime.setLength(0);
        gameScore = 0;
        String[] splitted;

        if (gameFile != null) { rightQuestions = openReadGame(gameFile, name, dateTime); }
        for (String right: rightQuestions) {
            splitted = right.split(";");
            switch (splitted[0]) {
                case "Artes": p = new Artes(); break;
                case "Ciências": p = new Ciencias(); break;
                case "Futebol": p = new Futebol(); break;
                case "Natação": p = new Natacao(); break;
                case "Ski": p = new Ski(); break;
                default: System.exit(0);
            }
            gameScore += p.getScore();
        }
        return gameScore;
    }
}
