/**
 * Subclasse de 'Pergunta'
 */
class Ciencias extends Pergunta {
    /**
     * Lista de respostas fáceis, para a 1.ª e 2.ª pergunta
     */
    protected String[] easyAsnwers = new String[5];

    /**
     * Lista de respostas difíceis, para a 3.ª, 4.ª e 5.ª pergunta
     */
    protected String[] hardAnswers = new String[5];

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Ciencias(String[] splitted) {
        super(splitted);
        System.arraycopy(splitted, 3, this.easyAsnwers, 0, 5);
        System.arraycopy(splitted, 8, this.hardAnswers, 0, 5);
    }

    /**
     * Getter
     * @return Lista de respostas fáceis
     */
    public String[] getEasyAnswers() { return this.easyAsnwers; }

    /**
     * Getter
     * @return Lista de respostas difíceis
     */
    public String[] getHardAnswers() { return this.hardAnswers; }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em +5 pela área de "Ciências"
     */
    public int getPont() { return super.getPont() + 5; }
}
