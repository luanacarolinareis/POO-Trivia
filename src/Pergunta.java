/**
 * Cada jogador terá que responder a 5 perguntas, de diferentes tipologias
 */
class Pergunta {
    /**
     * Área da pergunta
     */
    protected String area;

    /**
     * Enunciado da pergunta
     */
    protected String phrase;

    /**
     * Pontuação base
     */
    protected int score;

    /**
     * Resposta correta
     */
    protected String ans;

    /**
     * Construtor default
     */
    Pergunta() {
        this.score = 5;
    }

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Pergunta(String[] splitted) {
        this.area = splitted[0];                         // Área
        this.phrase = splitted[1];                       // Enunciado
        this.score = 5;                                  // Pontuação base
        this.ans = splitted[splitted.length - 1];        // Resposta correta
    }

    /**
     * Getter
     * @return Área da pergunta
     */
    public String getArea() { return area; }

    /**
     * Getter
     * @return Enunciado da pergunta
     */
    public String getPhrase() { return phrase; }

    /**
     * Getter
     * @return Resposta correta
     */
    public String getAns() { return ans; }

    /**
     * Getter
     * @return null
     */
    public String[] getEasyAnswers() {
        return null;
    }

    /**
     * Getter
     * @return null
     */
    public String[] getHardAnswers() {
        return null;
    }

    /**
     * Getter
     * @return null
     */
    public String[] getAnswers() {
        return null;
    }

    /**
     * Getter
     * @return Pontuação base
     */
    public int getScore() { return score; }
}
