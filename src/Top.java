import java.io.File;
import java.util.ArrayList;

/**
 * Ordena as pontuações de jogos
 */
class Top extends Ficheiros {
    /**
     * Todas as perguntas corretas, referentes a um jogo
     */
    private ArrayList<String> rightQuestions;

    /**
     * Pontuação total de um jogo
     */
    private int gameScore;

    /**
     * Utilizado para efeitos de leitura e escrita de ficheiros
     */
    private final Ficheiros files = new Ficheiros();

    /**
     * Array de ficheiros
     */
    protected File[] gameFiles;

    protected Pergunta p;

    /**
     * Vai devolver os três melhores jogos
     */
    public void getTopScores() {
       saveAllToMemory();   // Primeiramente, guarda todos os jogos numa lista temporária
       for (File fr: gameFiles) {                    // Para cada um desses ficheiros, vai processar a sua informação
           gameScore = extractGameScore(fr);         // Processa o jogo atual
           System.out.println(gameScore);
           System.out.println("\nNew game...\n");
       }
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
        gameScore = 0;
        String[] splitted;

        if (gameFile != null) { rightQuestions = files.openReadGame(gameFile); }
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
            System.out.println(right);
        }
        return gameScore;
    }
}
