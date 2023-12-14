import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

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
    private int buttonsNr;

    /**
     * Todas as perguntas
     */
    private final ArrayList<String> allQuestions;

    /**
     * Perguntas escolhidas
     */
    private ArrayList<Pergunta> chosenQuestions = new ArrayList<>();

    /**
     * Enunciado da pergunta
     */
    private String phrase;

    /**
     * Lista de respostas
     */
    private String[] answers;

    /**
     * Label que irá apresentar o resultado de jogo
     */
    private final JLabel showInfo;

    /**
     * Número de pergunta
     */
    private int questionNr = 2;

    /**
     * Contador auxiliar
     */
    private int count = 0;

    /**
     * Perguntas certas
     */
    private final ArrayList<Pergunta> right = new ArrayList<>();

    /**
     * Perguntas erradas
     */
    private final ArrayList<Pergunta> wrong = new ArrayList<>();

    /**
     * Ativação ou desativação dos botões de resposta
     */
    private boolean buttonsEnabled = true;

    /**
     * Construtor
     * @param allQuestions Todas as perguntas
     */
    GUI(ArrayList<String> allQuestions) {
        super("POO Trivia");                            // Invoca JFrame
        this.allQuestions = allQuestions;                   // Todas as perguntas

        cardLayout = new CardLayout();                     // Configura o layout de cartões
        cardPanel = new JPanel(cardLayout);

        createWelcomePanel();
        cardPanel.add(welcomePanel, "Welcome");  // Painel de boas vindas

        createFinalPanel();
        cardPanel.add(finalPanel, "END");       // Painel final

        cardLayout.show(cardPanel, "Welcome");      // Mostra o painel de boas vindas

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                     // Centraliza a janela

        add(cardPanel);                                 // Adiciona o painel de cartões ao JFrame
        showInfo = createStyledLabel("", 14, Font.PLAIN, Color.decode("#cda142"));
    }

    /**
     * Cria um painel com o layout "GridBagLayout"
     * @param panel Painel a ser configurado
     * @param top Top para new Insets()
     * @param bottom Bottom para new Insets()
     * @return GridBagConstraints
     */
    private GridBagConstraints createGridBagPanel(JPanel panel, int top, int bottom) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#06331f"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(top, 0, bottom, 0);     // Adiciona espaço abaixo do rótulo
        return gbc;
    }

    /**
     * Cria um botão personalizado
     * @param answer Texto do botão
     * @param w Comprimento do botão
     * @param h Largura do botão
     * @param event Evento
     * @return Botão
     */
    private JButton createStyledButton(String answer, int w, int h, ActionListener event) {
        JButton button = new JButton(answer);
        button.setPreferredSize(new Dimension(w, h));                                           // Modifica o tamanho do botão
        button.addActionListener(e -> { if (buttonsEnabled) { event.actionPerformed(e); } });   // Adiciona um ActionListener ao botão
        setButton(button, false);                                                        // Alterações adicionais no botão
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
        if (change) { button.setBackground(Color.decode("#cda142")); }  // Quando o rato está sobre o botão, a sua cor muda
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
     * @param index Índice da pergunta
     */
    private void defineInfo(int index) {
        String area = chosenQuestions.get(index).getArea();      // Área
        phrase = chosenQuestions.get(index).getPhrase();        // Enunciado

        if (!area.equals("Natação") && !area.equals("Ski")) {
            buttonsNr = 5;
            if (index < 2) {    // 1.ª e 2.ª pergunta (fáceis)
                if (area.equals("Artes")) { buttonsNr = 3; }
                answers = chosenQuestions.get(index).getEasyAnswers();         // Respostas fáceis
            }
            // 3.ª, 4.ª e 5.ª pergunta (difíceis)
            else { answers = chosenQuestions.get(index).getHardAnswers(); }    // Respostas difíceis
        }
        else {
            buttonsNr = 2;
            answers = chosenQuestions.get(index).getAnswers();                 // Respostas (sem dificuldade)
        }
    }

    /**
     * Inicializa o jogo e limpa variáveis e listas de controlo
     */
    private void startGame() {
        questionNr = 2;
        count = 0;
        right.clear();
        wrong.clear();

        // São randomizadas 5 novas perguntas
        Escolha choice = new Escolha();
        chosenQuestions = choice.chooseQuestions(allQuestions);

        // É definida a nova informação, para a 1.ª pergunta
        defineInfo(0);
        createMainPanel(1);
        cardPanel.add(mainPanel, "Main" + 1);
        cardLayout.show(cardPanel, "Main" + 1);
    }

    /**
     * Cria o painel de boas vindas (inicial)
     */
    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        GridBagConstraints gbc = createGridBagPanel(welcomePanel, 0, 20);

        // Label de boas vindas
        JLabel welcomeLabel = createStyledLabel("Bem vindo ao POO Trivia!", 18, Font.BOLD, Color.decode("#cda142"));
        welcomePanel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); // Zera as margens para o botão

        // Botão de início de jogo
        JButton startButton = createStyledButton("Iniciar", 150, 40, e -> startGame());
        handleMouseHover(startButton);       // Adiciona eventos para quando o rato passa sobre os botões
        welcomePanel.add(startButton, gbc);
    }

    /**
     * Cria o painel de questão (principal)
     * @param index Índice da pergunta
     */
    private void createMainPanel(int index) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.setBackground(Color.decode("#06331f"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));  // Adiciona um espaçamento externo ao JPanel

        // Label com a pergunta
        JLabel questionLabel = createStyledLabel("Pergunta " + index + ": " + phrase , 18, Font.BOLD, Color.decode("#cda142"));
        mainPanel.add(questionLabel);

        // Array de letras e array com os botões de resposta
        String[] letters = {"a) ", "b) ", "c) ", "d) ", "e) "};
        JButton[] answerButtons = new JButton[buttonsNr];

        // Cria e adiciona os botões ao painel
        for (int i = 0; i < buttonsNr; i++) {       // Cria 5, 3 ou 2 botões
            int finalI = i;
            answerButtons[i] = createStyledButton(letters[i] + answers[i].substring(3), 150, 40, e -> handleAnswer(answers[finalI]));
            handleMouseHover(answerButtons[i]);     // Adiciona eventos para quando o rato passa sobre os botões
            mainPanel.add(answerButtons[i]);
        }

        // Label que apresenta ao utilizador o resultado obtido em cada questão
        mainPanel.add(showInfo);
    }

    /**
     * Cria o painel de submissão de nome (final)
     */
    private void createFinalPanel() {
        finalPanel = new JPanel();
        GridBagConstraints gbc = createGridBagPanel(finalPanel, 0, 20);

        // Adiciona a label informativa de fim de jogo
        JLabel title = createStyledLabel("»»»»»»»»    FIM DE JOGO    ««««««««", 18, Font.BOLD, Color.decode("#cda142"));
        finalPanel.add(title, gbc);

        // Adiciona a label informativa de pedido de nome
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;   // Alinha à esquerda
        JLabel askName = createStyledLabel("Nome do jogador: ", 14, Font.PLAIN, Color.WHITE);
        finalPanel.add(askName, gbc);

        // Adiciona a caixa de texto
        gbc.anchor = GridBagConstraints.EAST;   // Alinha à direita
        JTextField playerName = new JTextField(10);
        finalPanel.add(playerName, gbc);

        // Adiciona o botão de confirmação
        gbc.insets = new Insets(-10, 0, 0, 0);  // Novo espaçamento
        gbc.gridy = 2;
        ActionListener endEvent =  e -> onOkButtonClicked(playerName.getText());
        JButton okButton = createStyledButton("OK", 105, 20, endEvent);
        handleMouseHover(okButton);     // Adiciona eventos para quando o rato passa sobre os botões
        finalPanel.add(okButton, gbc);
    }

    /**
     * Cria o painel com o "TOP 3" de pontuações
     */
    private void createTopPanel() {
        topPanel = new JPanel();
        GridBagConstraints gbc = createGridBagPanel(topPanel, 40, 0);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Label informativa de que irão ser apresentadas as melhores pontuações
        JLabel title = createStyledLabel("TOP 3", 25, Font.BOLD, Color.decode("#cda142"));
        topPanel.add(title, gbc);
        gbc.gridy++;

        // Obtém a informação necessária
        Top top = new Top();
        ArrayList<ArrayList<String>> playerInfo = top.getTopScores();

        // Cria um painel que irá ser adicionado ao 'topPanel'
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
        for (int i = 0; i < playerInfo.size(); i++) {
            if (i < playerInfo.size() - 1) {
                // Nome do jogador, pontuação, data e hora
                JLabel nameLabel = createStyledLabel(playerInfo.get(i).get(0), 14, Font.PLAIN, Color.WHITE);
                JLabel scoreLabel = createStyledLabel("Pontuação: " + playerInfo.get(i).get(1), 14, Font.PLAIN, Color.WHITE);
                JLabel dateLabel = createStyledLabel(playerInfo.get(i).get(2), 14, Font.PLAIN, Color.WHITE);

                temp.add(nameLabel);
                temp.add(Box.createRigidArea(new Dimension(0, 5)));
                temp.add(scoreLabel);
                temp.add(Box.createRigidArea(new Dimension(0, 5)));
                temp.add(dateLabel);
                temp.add(Box.createRigidArea(new Dimension(0, 20)));
            } else {
                // Pontuação do jogo atual
                temp.add(Box.createRigidArea(new Dimension(0,10)));
                JLabel gameScore = createStyledLabel("Pontuação do jogo atual: " + playerInfo.get(i).get(0), 14, Font.BOLD, Color.decode("#cda142"));
                temp.add(gameScore);
            }
        }
        temp.setOpaque(false);
        topPanel.add(temp, gbc);
        topPanel.add(Box.createRigidArea(new Dimension(0,60)));

        // Botão de 'Novo Jogo', que dá a possibilidade do utilizador reiniciar o jogo
        JButton newGameButton = createStyledButton("Novo Jogo", 150, 40, e -> startGame());
        handleMouseHover(newGameButton);                             // Adiciona eventos para quando o rato passa sobre os botões
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 0, 80); // Adiciona espaço abaixo do rótulo
        topPanel.add(newGameButton, gbc);
        topPanel.add(Box.createRigidArea(new Dimension(0,50)));
    }

    /**
     * Lida com a resposta do utilizador
     * @param selectedAnswer Resposta selecionada
     */
    private void handleAnswer(String selectedAnswer) {
        buttonsEnabled = false;     // Por segurança, desativa os botões enquanto mostra o resultado

        // Verificação de respostas
        String ansUser = selectedAnswer.substring(0, 2);
        String ansCorrect = chosenQuestions.get(count).getAns();

        if (ansUser.equals(ansCorrect)) {
            showInfo.setText("Resposta correta! [" + chosenQuestions.get(count).getScore() + " pontos]");
            right.add(chosenQuestions.get(count));   // Adiciona à lista de perguntas certas
        } else {
            showInfo.setText("Resposta incorreta! [0 pontos]");
            wrong.add(chosenQuestions.get(count));   // Adiciona à lista de perguntas erradas
        }

        count++;     // Próximo painel

        // Exibe a mensagem por 500 milissegundos, antes de passar para a próxima pergunta
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    showInfo.setText("");       // Reseta o texto da label
                    if (questionNr <= 5) {
                        defineInfo(count);       // Define a informação da próxima pergunta
                        createMainPanel(count + 1);
                        cardPanel.add(mainPanel, "Main" + (count + 1));
                        cardLayout.show(cardPanel, "Main" + (count + 1));
                        questionNr++;
                    } else {
                        // Se todas as perguntas foram respondidas, exibe o ecrã de fim de jogo
                        cardLayout.show(cardPanel, "END");
                    }
                    buttonsEnabled = true;  // Reativa os botões de resposta
                    timer.cancel();         // Cancela o temporizador após a execução
                });
            }
        }, 500);                     // Aguarda 500 milisseegundos antes de executar a tarefa
    }

    /**
     * Ações a efetuar quando o botão de confirmação de envio de nome é pressionado
     * @param playerName Nome do jogador
     */
    private void onOkButtonClicked(String playerName) {
        // O nome só deve contar letras e espaços
        String regex = "^[a-zA-Z\\sÀ-ÿ]*$";

        // Remoção de espaços desnecessários
        if(playerName.trim().isEmpty() || !playerName.matches(regex)) {
            JOptionPane.showMessageDialog(null,
                    "Nome inválido! Apenas letras são aceites e o nome não pode estar vazio.",
                    "Invalid Parameter",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtenção de data e hora de sistema
        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTimeFormat = dateTimeNow.format(format);

        // Escrita do ficheiro com os resultados de cada jogo, com nome, data e hora
        Resultados res = new Resultados(dateTimeFormat, playerName);
        res.generateResultFile(dateTimeFormat, playerName, right, wrong);

        // Exibe o "TOP 3", após o botão "OK" ser clicado
        createTopPanel();
        cardPanel.add(topPanel, "TOP 3");
        cardLayout.show(cardPanel, "TOP 3");
    }

    /**
     * Adiciona eventos relacionados com o movimento do rato ao botão
     * @param button Botão
     */
    private void handleMouseHover(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                // Permite selecionar um botão com a tecla "Enter", quando o rato está por cima dele
                getRootPane().setDefaultButton(button);
                setButton(button, true);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                // Não permite selecionar um botão com a tecla "Enter", quando o rato sai de cima dele
                getRootPane().setDefaultButton(null);
                setButton(button, false);
            }
            @Override
            public void mousePressed(MouseEvent evt) {
                // Volta ao estado original do botão
                button.setContentAreaFilled(false);
            }
        });
    }
}
