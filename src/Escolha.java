import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Seleção randomizada de 5 perguntas aleatórias, a cada novo jogo
 */
class Escolha {
    /**
     * Perguntas selecionadas
     */
    private final ArrayList<Pergunta> chosen = new ArrayList<>();

    /**
     * Construtor default
     */
    Escolha() {}

    /**
     * Gera índices aleatórios não repetidos
     * @return Lista de índices
     */
    private List<Integer> generateIndexes() {
        List<Integer> indexes = new ArrayList<>();
        Random random = new Random();
        while (indexes.size() < 5) {
            int randIndex = random.nextInt(30);                         // Índices entre 0 e 29 (inclusivé)
            if (!indexes.contains(randIndex)) { indexes.add(randIndex); }     // Se esse índice ainda não foi selecionado, adiciona-o
        }
        return indexes;
    }

    /**
     * Seleção de 5 perguntas aleatórias
     * @param allQuestions Todas as perguntas
     * @return Perguntas escolhidas
     */
    private ArrayList<Pergunta> defineQuestions(ArrayList<String> allQuestions) {
        // Escolha de 5 perguntas para a 'interface' gráfica
        List<Integer> indexes = generateIndexes();
        String currentQuestion;
        String[] splitted;
        for (int i = 0; i < 5; i++) {
            currentQuestion = allQuestions.get(indexes.get(i));  // Escolhe uma pergunta aleatoriamente
            splitted = currentQuestion.split(";");        // Informação splitada da pergunta
            switch (splitted[0]) {
                case "Artes": chosen.add(new Artes(splitted)); break;
                case "Ciências": chosen.add(new Ciencias(splitted)); break;
                case "Futebol": chosen.add(new Futebol(splitted)); break;
                case "Natação": chosen.add(new Natacao(splitted)); break;
                case "Ski": chosen.add(new Ski(splitted)); break;
                default: System.exit(0);
            }
        }
        return chosen;
    }

    /**
     * Getter
     * @param allQuestions Todas as perguntas
     * @return Perguntas escolhidas
     */
    public ArrayList<Pergunta> chooseQuestions(ArrayList<String> allQuestions) { return defineQuestions(allQuestions);}
}
