import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Ordena as pontuações de jogos
 */
class Top {

    /**
     * Lista temporária para armazenar informações dos jogos
     */
    List<File> gameFiles = new ArrayList<>();

    /**
     * Função responsável por guardar todos os ficheiros objeto existentes para uma lista temporária
     */
    public void saveAllToMemory() {
        // Diretório onde estão os arquivos
        String directory = "/caminho/do/diretorio";

        // Obter lista de arquivos no diretório
        File folder = new File(directory);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null) {
            Collections.addAll(gameFiles, files);

            // Ordenar a lista temporária por pontuação em ordem decrescente
            // gameFiles.sort(Comparator.comparingInt(Top::extractScore).reversed());

            // Imprimir a lista ordenada
            for (File jogo : gameFiles) {
                processarJogo(jogo);
            }
        }
    }

    public void processarJogo(File jogo) {
        try {
            // Ler o conteúdo do arquivo
            Scanner scanner = new Scanner(jogo);
            String content = scanner.useDelimiter("\\Z").next();
            scanner.close();

            // Extrair informações do arquivo
            String[] lines = content.split("\n");
            String dataHoraStr = lines[1].split(": ")[1];
            Date dataHora = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dataHoraStr);
            String nomeJogador = lines[2].split(": ")[1];
            int pontuacao = extractScore(content);

            // Imprimir informações
            System.out.println("Nome: " + nomeJogador + ", Data e Hora: " + dataHora +
                    ", Pontuação: " + pontuacao);

        } catch (IOException | ParseException e) {
            // e.printStackTrace();
        }
    }

    /**
     * Calcula a pontuação de um jogo, com base nas perguntas acertadas e erradas presentes no ficheiro
     */
    public static int extractScore(String conteudo) {
        int perguntasCertas = conteudo.split("Perguntas certas:")[1].split("\n")[1].split("\\?").length - 1;
        int perguntasErradas = conteudo.split("Perguntas erradas:")[1].split("\n")[1].split("\\?").length - 1;
        return perguntasCertas - perguntasErradas;
    }
}
