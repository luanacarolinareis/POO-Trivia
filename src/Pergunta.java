/**
 * Cada jogador terá que responder a 5 perguntas, de diferentes tipologias
 */
class Pergunta {
    /**
     * Área da pergunta
     */
    private String area;

    /**
     * Enunciado da pergunta
     */
    private String phrase;

    /**
     * Resposta correta
     */
    private String ans;

    /**
     * Construtor default
     */
    Pergunta() {}

    /**
     * Construtor
     * @param splitted Informação dividida
     */
    Pergunta(String[] splitted) {
        this.area = splitted[0];                         // Área
        this.phrase = splitted[1];                       // Enunciado
        this.ans = splitted[splitted.length - 1];        // Resposta correta
    }

    /**
     * Getter
     * @return Área da pergunta
     */
    protected String getArea() { return area; }

    /**
     * Getter
     * @return Enunciado da pergunta
     */
    protected String getPhrase() { return phrase; }

    /**
     * Getter
     * @return Resposta correta
     */
    protected String getAns() { return ans; }

    /**
     * Getter
     * @return null
     */
    protected String[] getEasyAnswers() {
        return null;
    }

    /**
     * Getter
     * @return null
     */
    protected String[] getHardAnswers() {
        return null;
    }

    /**
     * Getter
     * @return null
     */
    protected String[] getAnswers() {
        return null;
    }

    /**
     * Getter
     * @return Pontuação base
     */
    protected int getScore() { return 5; }
}
