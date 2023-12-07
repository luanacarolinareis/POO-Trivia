import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe responsável pela criação da 'interface' gráfica
 */
class GUI extends JFrame {
    /**
     * Card Layout
     */
    private final CardLayout cardLayout;

    /**
     * Card Panel
     */
    private final JPanel cardPanel;

    /**
     * Welcome Panel
     */
    private JPanel welcomePanel;

    /**
     * Main Panel
     */
    private JPanel mainPanel;

    /**
     * End Panel
     */
    private JPanel finalPanel;

    /**
     * TOP 3 Panel
     */
    private JPanel topPanel;

    /**
     * Número de botões necessários
     */
    protected int buttonsNr;

    /**
     * Todas as perguntas
     */
    protected ArrayList<String> allQuestions;

    /**
     * Perguntas escolhidas
     */
    protected ArrayList<Pergunta> chosenQuestions = new ArrayList<>();

    /**
     * Área da pergunta
     */
    protected String area;

    /**
     * Enunciado da pergunta
     */
    protected String phrase;

    /**
     * Lista de respostas
     */
    protected String[] answers;

    /**
     * Mostra ao utilizador se acertou ou errou
     */
    protected JLabel showInfo = new JLabel();

    /**
     * Resposta correta
     */
    protected String ansCorrect;

    /**
     * Resposta do utilizador
     */
    protected String ansUser;

    /**
     * Contador
     */
    protected int count = 0;

    /**
     * Número de pergunta
     */
    protected int questionNr = 2;

    /**
     * Contador auxiliar
     */
    protected int temp = 0;

    /**
     * Escolha de novas perguntas
     */
    protected Escolha choice;

    /**
     * Perguntas acertadas
     */
    protected ArrayList<String> right = new ArrayList<>();

    /**
     * Perguntas erradas
     */
    protected ArrayList<String> wrong = new ArrayList<>();

    /**
     * Construtor
     */
    GUI(ArrayList<String> allQuestions) {
        super("POO Trivia");           // Invoca JFrame
        this.allQuestions = allQuestions;   // Todas as perguntas

        cardLayout = new CardLayout();      // Configura o layout de cartões
        cardPanel = new JPanel(cardLayout);

        createWelcomePanel();
        cardPanel.add(welcomePanel, "Welcome");  // Painel de boas vindas

        createFinalPanel();
        cardPanel.add(finalPanel, "END");       // Painel final

        createTopPanel();
        cardPanel.add(topPanel, "TOP 3");       // Painel de "TOP 3"

        cardLayout.show(cardPanel, "Welcome");      // Mostra o painel de boas vindas

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // Centraliza a janela
        setVisible(true);

        add(cardPanel);     // Adiciona o painel de cartões ao JFrame
        showInfo = createStyledLabel("", 14, Font.PLAIN, Color.decode("#cda142"));
    }

    /**
     * Cria um painel com o layout "GridBagLayout"
     * @param panel Painel a ser configurado
     * @return GridBagConstraints
     */
    private GridBagConstraints createGridBagPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#06331f"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Adiciona espaço abaixo do rótulo
        return gbc;
    }

    /**
     * Cria um botão personalizado
     * @param answer Texto do botão
     * @param listener Evento
     * @return Botão
     */
    private JButton createStyledButton(String answer, int w, int h, ActionListener listener) {
        JButton button = new JButton(answer);
        setButton(button, false);
        button.setPreferredSize(new Dimension(w, h));
        button.addActionListener(listener);
        return button;
    }

    /**
     * Altera a estética do botão
     * @param button Botão a alterar
     * @param change Mudança a efetuar
     */
    private void setButton(JButton button, boolean change) {
        button.setPressedIcon(null);
        button.setContentAreaFilled(change);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMargin(new Insets(10, 20, 10, 20));
        button.setFocusPainted(false);
        if (change) { button.setBackground(Color.decode("#cda142")); }
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#cda142"), 2, true));
        button.setForeground(Color.WHITE);
    }

    /**
     * Cria uma label personalizada
     * @param text Texto da label
     * @param fontSize Tamanho do texto
     * @param fontStyle Estilo do texto
     * @param textColor Cor do texto
     * @return Label
     */
    private JLabel createStyledLabel(String text, int fontSize, int fontStyle, Color textColor) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(textColor);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        return label;
    }

    /**
     * Define a informação a ser apresentada
     */
    private void defineInfo(int index) {
        this.area = chosenQuestions.get(index).getArea();      // Área
        this.phrase = chosenQuestions.get(index).getPhrase();  // Enunciado

        if (!this.area.equals("Natação") && !this.area.equals("Ski")) {
            this.buttonsNr = 5;
            // 1.ª e 2.ª pergunta (fáceis)
            if (index < 2) {
                if (this.area.equals("Artes")) { this.buttonsNr = 3; }
                this.answers = chosenQuestions.get(index).getEasyAnswers();    // Respostas fáceis
            }
            // 3.ª, 4.ª e 5.ª pergunta (difíceis)
            else { this.answers = chosenQuestions.get(index).getHardAnswers(); }    // Respostas difíceis
        }
        else {
            this.buttonsNr = 2;
            this.answers = chosenQuestions.get(index).getAnswers();            // Respostas (sem dificuldade)
        }
    }

    /**
     * Método para iniciar o jogo
     */
    private void startGame() {
        // As variáveis responsáveis pelo funcionamento da GUI são resetadas
        count = 0;
        questionNr = 2;
        temp = 0;

        // Listas de perguntas acertadas e erradas são limpas
        right.clear();
        wrong.clear();

        // São randomizadas 5 novas perguntas
        choice = new Escolha();
        this.chosenQuestions = choice.chooseQuestions(allQuestions);

        // Cria todos os painéis principais antecipadamente
        for (int i = 1; i <= chosenQuestions.size(); i++) {
            defineInfo(i - 1);
            createMainPanel(i);
            cardPanel.add(mainPanel, "Main" + i);
        }
        cardLayout.show(cardPanel, "Main" + 1);
    }

    /**
     * Cria o painel de boas vindas (inicial)
     */
    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        GridBagConstraints gbc = createGridBagPanel(welcomePanel);

        JLabel welcomeLabel = createStyledLabel("Bem vindo ao POO Trivia!", 18, Font.BOLD, Color.decode("#cda142"));
        welcomePanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); // Zera as margens para o botão

        // Botão de início de jogo
        JButton startButton = createStyledButton("Iniciar", 150, 40, e -> startGame());
        welcomePanel.add(startButton, gbc);
    }

    /**
     * Cria o painel de questão (principal)
     */
    private void createMainPanel(int index) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.setBackground(Color.decode("#06331f")); // Define a cor de fundo
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));  // Adiciona um espaçamento externo ao JPanel

        // Label com a pergunta
        JLabel questionLabel = createStyledLabel("Pergunta " + index + ": " + phrase , 18, Font.BOLD, Color.decode("#cda142"));
        mainPanel.add(questionLabel);

        // Array com os botões de resposta
        JButton[] answerButtons = new JButton[buttonsNr];

        for (int i = 0; i < buttonsNr; i++) {    // Cria 5 ou 2 botões
            answerButtons[i] = createStyledButton(answers[i], 150, 40, e -> onAnswerButtonClicked(((JButton) e.getSource()).getText()));
            int finalI = i;
            answerButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) { setButton(answerButtons[finalI], true); }
                @Override
                public void mouseExited(MouseEvent evt) { setButton(answerButtons[finalI], false); }
                @Override
                public void mousePressed(MouseEvent evt) { answerButtons[finalI].setContentAreaFilled(false); }
            });
            mainPanel.add(answerButtons[i]);
        }
        mainPanel.add(this.showInfo);
    }

    /**
     * Cria o painel de fim de jogo
     */
    private void createFinalPanel() {
        finalPanel = new JPanel();
        GridBagConstraints gbc = createGridBagPanel(finalPanel);

        // Adiciona a label informativa de fim de jogo
        JLabel title = createStyledLabel("»»»»»»»»    FIM DE JOGO    ««««««««", 18, Font.BOLD, Color.decode("#cda142"));
        finalPanel.add(title, gbc);

        // Adiciona a label informativa de pedido de nome
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        JLabel askName = createStyledLabel("Nome do jogador: ", 14, Font.PLAIN, Color.WHITE);
        finalPanel.add(askName, gbc);

        // Adiciona a caixa de texto
        gbc.anchor = GridBagConstraints.EAST; // Alinha à esquerda
        JTextField playerName = new JTextField(10);
        finalPanel.add(playerName, gbc);

        // Adiciona o botão de confirmação
        gbc.insets = new Insets(-10, 0, 0, 0); // Novo espaçamento
        gbc.gridy = 2;
        JButton okButton = createStyledButton("OK", 105, 20, e -> onOkButtonClicked(playerName.getText()));
        finalPanel.add(okButton, gbc);
    }

    /**
     * Cria o painel com o "TOP 3" de pontuações
     */
    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setOpaque(true);
        topPanel.setBackground(Color.decode("#06331f"));
        topPanel.setBorder(new EmptyBorder(100, 200, 100, 200));  // Ajuste os valores conforme necessário
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = createStyledLabel("TOP 3", 18, Font.BOLD, Color.decode("#cda142"));
        topPanel.add(title);

        topPanel.add(Box.createRigidArea(new Dimension(0,50)));

        // Informações dos 3 melhores jogos (exemplo a ser alterado)
        String[][] playerInfo = {{"Jogador 1", "100", "01/01/2023 10:00"},
                {"Jogador 2", "90", "02/01/2023 11:00"},
                {"Jogador 3", "80", "03/01/2023 12:00"}};

        for (String[] player : playerInfo) {
            JLabel nameLabel = createStyledLabel("Nome: " + player[0], 14, Font.PLAIN, Color.WHITE);
            JLabel scoreLabel = createStyledLabel("Pontuação: " + player[1], 14, Font.PLAIN, Color.WHITE);
            JLabel dateLabel = createStyledLabel("Data e hora: " + player[2], 14, Font.PLAIN, Color.WHITE);
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            topPanel.add(nameLabel);
            topPanel.add(Box.createRigidArea(new Dimension(0,5)));
            topPanel.add(scoreLabel);
            topPanel.add(Box.createRigidArea(new Dimension(0,5)));
            topPanel.add(dateLabel);
            topPanel.add(Box.createRigidArea(new Dimension(0,20)));
        }

        topPanel.add(Box.createRigidArea(new Dimension(0,50)));

        JButton newGameButton = createStyledButton("NOVO JOGO", 150, 40, e -> startGame());
        topPanel.add(newGameButton);
    }

    /**
     * Ações a efetuar quando uma das opções de resposta é pressionada
     */
    private void onAnswerButtonClicked(String answer) {
        // Verificação de respostas
        ansUser = answer.substring(0, 2);
        ansCorrect = chosenQuestions.get(temp).getAns();
        String questionToAdd = chosenQuestions.get(temp).getPhrase();
        if (ansUser.equals(ansCorrect)) { right.add(questionToAdd); }
        else { wrong.add(questionToAdd); }

        // Apresentação do resultado
        showInfo.setText("Resposta correta!");

        // Próximo painel
        temp++;
        questionNr++;

        // Atualiza o conteúdo do painel principal atual
        if (temp < chosenQuestions.size()) {
            cardLayout.show(cardPanel, "Main" + (temp + 1));
        } else {
            cardLayout.show(cardPanel, "END");
        }
    }

    /**
     * Ações a efetuar quando o botão de confirmação de envio de nome é pressionado
     * @param playerName Nome do jogador
     */
    private void onOkButtonClicked(String playerName) {
        // Obtenção de data e hora de sistema
        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTimeFormat = dateTimeNow.format(format);

        // Escrita do ficheiro com os resultados de cada jogo, com data, hora e nome
        Resultados res = new  Resultados(dateTimeFormat, playerName);
        res.generateResultsFile(dateTimeFormat, playerName, right, wrong);

        // Exibe o "TOP 3", após o botão "OK" ser clicado
        cardLayout.show(cardPanel, "TOP 3");
    }
}
