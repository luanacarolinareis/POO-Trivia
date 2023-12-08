/**
 * Subclasse de 'Desporto'
 */
class Natacao extends Desporto {
    /**
     * Lista de respostas (Verdadeiro ou Falso)
     */
    protected String[] answers = new String[2];

    /**
     * Construtor default
     */
    Natacao() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Natacao(String[] splitted) {
        super(splitted);
        System.arraycopy(splitted, 2, this.answers, 0, 2);
    }

    /**
     * Getter
     * @return Lista de respostas (Verdadeiro / Falso)
     */
    public String[] getAnswers() { return this.answers; }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em +3 pela área de "Desporto" e majorada em +10 pela subárea de "Natação"
     */
    public int getScore() { return super.getScore() + 10; }
}
