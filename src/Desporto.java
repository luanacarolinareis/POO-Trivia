/**
 * Subclasse de 'Pergunta'
 */
class Desporto extends Pergunta {
    /**
     * Construtor default
     */
    Desporto() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Desporto (String[] splitted) {
        super(splitted);
    }

    /**
     * Majoração da pontuação
     * @return Pontuação majorada em +3 pela área de "Desporto"
     */
    private int increaseScore() { return super.getScore() + 3; }

    /**
     * Getter
     * @return Pontuação majorada
     */
    public int getScore() { return increaseScore(); }
}
