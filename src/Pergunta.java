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
    public int getScore() { return 5; }
}
