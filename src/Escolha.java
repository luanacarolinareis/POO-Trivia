import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Escolha {

    /**
     * Lista de índices não repetidos
     */
    protected static List<Integer> indexes;

    /**
     * Pergunta atual
     */
    protected String currentQuestion;

    /**
     * Informação splitada da pergunta
     */
    protected String[] splitted;

    /**
     * Perguntas selecionadas
     */
    protected ArrayList<Pergunta> chosen = new ArrayList<>();

    /**
     * Gera índices aleatórios não repetidos
     */
    protected static List<Integer> generateIndexes() {
        List<Integer> indexes = new ArrayList<>();
        Random random = new Random();
        while (indexes.size() < 5) {
            int randIndex = random.nextInt(30);
            if (!indexes.contains(randIndex)) { indexes.add(randIndex); }
        }
        return indexes;
    }

    /**
     * Seleção de 5 perguntas aleatórias
     * @param allQuestions Todas as perguntas
     * @return Perguntas escolhidas
     */
    protected ArrayList<Pergunta> chooseQuestions(ArrayList<String> allQuestions) {
        // Escolha de 5 perguntas para a 'interface' gráfica
        indexes = generateIndexes();
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
}
