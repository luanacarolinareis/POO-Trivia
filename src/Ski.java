/**
 * Subclasse de 'Desporto'
 */
class Ski extends Desporto {
    /**
     * Lista de respostas (Verdadeiro ou Falso)
     */
    protected String[] answers = new String[2];

    /**
     * Construtor default
     */
    Ski() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Ski(String[] splitted) {
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
     * @return Pontuação majorada em +3 pela área de "Desporto" e majorada em x2 pela subárea de "Ski"
     */
    public int getScore() { return super.getScore() * 2; }
}

