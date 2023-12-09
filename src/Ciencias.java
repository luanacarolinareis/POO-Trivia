/**
 * Subclasse de 'Pergunta'
 */
class Ciencias extends Pergunta {
    /**
     * Lista de respostas fáceis, para a 1.ª e 2.ª pergunta
     */
    private final String[] easyAsnwers = new String[5];

    /**
     * Lista de respostas difíceis, para a 3.ª, 4.ª e 5.ª pergunta
     */
    private final String[] hardAnswers = new String[5];

    /**
     * Construtor default
     */
    Ciencias() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Ciencias(String[] splitted) {
        super(splitted);
        System.arraycopy(splitted, 2, easyAsnwers, 0, 5);
        System.arraycopy(splitted, 7, hardAnswers, 0, 5);
    }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em +5 pela área de "Ciências"
     */
    private int increaseScore() { return super.getScore() + 5; }

    /**
     * Getter
     * @return Lista de respostas fáceis
     */
    public String[] getEasyAnswers() { return easyAsnwers; }

    /**
     * Getter
     * @return Lista de respostas difíceis
     */
    public String[] getHardAnswers() { return hardAnswers; }

    /**
     * Getter
     * @return Pontuação majorada
     */
    public int getScore() { return increaseScore(); }
}
