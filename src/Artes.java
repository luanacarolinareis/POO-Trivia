import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Subclasse de 'Pergunta'
 */
class Artes extends Pergunta {
    /**
     * Lista de respostas para a 3.ª, 4.ª e 5.ª pergunta (5 opções)
     */
    private final String[] answers = new String[5];

    /**
     * Construtor default
     */
    Artes() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Artes(String[] splitted) {
        super(splitted);
        System.arraycopy(splitted, 2, answers, 0, 5);
    }

    /**
     * Define uma sublista de tamanho 3, que contém sempre a resposta correta
     * @return Lista de respostas com 3 opções (fácil)
     */
    private String[] defineEasy() {
        String correct = getAns();                                       // Resposta correta (apenas letra)
        for (String s: answers) {
            if (s.contains(correct)) { correct = s; }                    // Define a resposta correta, com a letra e texto
        }
        List<String> list = new ArrayList<>(Arrays.asList(answers));
        list.remove(correct);                                           // Remoção da resposta correta, para randomizar outras 2 erradas
        Collections.shuffle(list);
        List<String> subAnswers = list.subList(0, 2);
        subAnswers.add(correct);                                        // Adição da resposta correta
        Collections.shuffle(subAnswers);
        return subAnswers.toArray(new String[0]);
    }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em x10 pela área de "Artes"
     */
    private int increaseScore() { return super.getScore() * 10; }

    /**
     * Getter
     * @return Lista de respostas com 3 opções (fácil)
     */
    public String[] getEasyAnswers() { return defineEasy(); }

    /**
     * Getter
     * @return Lista de respostas com 5 opções (difícil)
     */
    public String[] getHardAnswers() { return answers; }

    /**
     * Getter
     * @return Pontuação majorada
     */
    public int getScore() { return increaseScore(); }
}
