/**
 * Subclasse de 'Desporto'
 */
class Futebol extends Desporto {
    /**
     * Lista de respostas com nomes de jogadores, para a 1.ª e 2.ª pergunta
     */
    private final String[] nameAnswers = new String[5];

    /**
     * Lista de respostas com os números de camisola, para a 3.ª, 4.ª e 5.ª pergunta
     */
    private final String[] shirtAnswers = new String[5];

    /**
     * Construtor default
     */
    Futebol() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Futebol(String[] splitted) {
        super(splitted);
        System.arraycopy(splitted, 2, nameAnswers, 0, 5);
        System.arraycopy(splitted, 7, shirtAnswers, 0, 5);
    }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em +3 pela área de "Desporto" e majorada em +1 pela subárea de "Futebol"
     */
    private int increaseScore() { return super.getScore() + 1; }

    /**
     * Getter
     * @return Lista de respostas com nomes (fácil)
     */
    public String[] getEasyAnswers() { return nameAnswers; }

    /**
     * Getter
     * @return Lista de respostas com números de camisola (difícil)
     */
    public String[] getHardAnswers() { return shirtAnswers; }

    /**
     * Getter
     * @return Pontuação majorada
     */
    public int getScore() { return increaseScore(); }
}