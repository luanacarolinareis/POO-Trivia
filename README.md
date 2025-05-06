# POOTrivia  
Jogo de Perguntas e Respostas em Java

POO Trivia é uma aplicação desenvolvida em Java no âmbito da unidade curricular de Programação Orientada aos Objetos. O projeto consiste num jogo de perguntas e respostas (trivia) que testa os conhecimentos do jogador em três grandes áreas: Artes, Desporto (Futebol, Natação e Ski) e Ciências. O objetivo é proporcionar uma experiência interativa e desafiante, com interface gráfica intuitiva e sistema de pontuação majorada conforme a dificuldade e tipologia das perguntas.

---

## ✨ Funcionalidades Principais

- **Jogo de Trivia Multitemático**
  - Perguntas sobre Artes, Ciências e Desporto (Futebol, Natação, Ski)
  - Seleção aleatória e sem repetição de 5 perguntas por jogo

- **Interface Gráfica (JFrame)**
  - Navegação por painéis: início, perguntas, resultados e ranking TOP 3
  - Botões, caixas de texto e feedback visual ao utilizador

- **Sistema de Pontuação Majorada**
  - Pontuação base acrescida de bónus conforme área e dificuldade da pergunta

- **Ranking e Histórico**
  - Registo dos melhores jogos (TOP 3) e pontuação atual
  - Possibilidade de reiniciar o jogo após cada ronda

- **Acessibilidade e Usabilidade**
  - Seleção de botões com "Enter"
  - Foco visual dinâmico nos botões
  - Proteção contra nomes inválidos

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Função                        |
|------------|------------------------------|
| Java       | Lógica da aplicação           |
| Swing      | Interface gráfica (JFrame)    |
| Ficheiros TXT/DAT | Armazenamento de perguntas e resultados |

---

## 🛠️ Como Usar

1. Clone o repositório:
   
- git clone https://github.com/luanacarolinareis/POO-Trivia.git
- cd POO-Trivia
  
2. Certifique-se que o ficheiro `pootrivia.txt` está na raiz do projeto (contém as perguntas).
   
3. Compile e execute a classe principal (`Trivia.java`).

---

## 🧩 Fluxo de Utilização

- **Início:**  
O utilizador inicia o jogo e insere o seu nome.

- **Perguntas:**  
São apresentadas 5 perguntas aleatórias, com opções de resposta que variam conforme a área e dificuldade. O utilizador responde através de botões.

- **Pontuação:**  
Cada resposta correta atribui pontos, majorados conforme a tabela de áreas e dificuldade.

- **Resultados e Ranking:**  
No final, é apresentado o TOP 3 das melhores pontuações e a pontuação atual. O utilizador pode optar por reiniciar o jogo.

---

## 📋 Estrutura de Dados e Classes

- `Trivia`: Classe principal, gere o fluxo do jogo e a leitura de perguntas.
- `GUI`: Implementa a interface gráfica e a navegação entre painéis.
- `Escolha`: Seleciona as perguntas para cada ronda.
- `Ficheiros`: Gestão de leitura/escrita de ficheiros `.txt` e `.dat`.
- `Resultados`: Guarda os resultados de cada jogo.
- `Top`: Calcula e apresenta o TOP 3 das melhores pontuações.
- `Pergunta`, `Artes`, `Ciências`, `Desporto`, `Futebol`, `Natação`, `Ski`: Hierarquia de perguntas, com herança e polimorfismo.

---

## 🏆 Sistema de Pontuação

| Área      | Características      | Majoração da Pontuação  |
|-----------|---------------------|------------------------|
| Artes     | -                   | ×10                    |
| Ciências  | -                   | +5                     |
| Desporto  | -                   | +3                     |
| Futebol   | Subárea Desporto    | +1                     |
| Natação   | Subárea Desporto    | +10                    |
| Ski       | Subárea Desporto    | ×2                     |

- Perguntas 1 e 2: opções fáceis
- Perguntas 3, 4 e 5: opções difíceis

---

## 📄 Documentação

- [Relatório do Projeto (PDF)](Relatório.pdf)
- [Diagrama UML (PDF)](uml-final.pdf)

## 📸 Capturas de Ecrã

![image](https://github.com/user-attachments/assets/a4f288ad-7d7e-423a-a7be-c304a05c1cb4)
![image](https://github.com/user-attachments/assets/14879c8f-be69-4d14-b4b0-489c9da7ab3a)
![image](https://github.com/user-attachments/assets/72c73cbe-e997-4368-a83b-c11057268f88)

---

## 📢 Contribuição

Contribuições são bem-vindas!  
Abra uma issue ou envie um pull request para sugerir melhorias.

---

## 👥 Autora

[Carolina Reis](https://github.com/luanacarolinareis)

---

POOTrivia – Aprendizagem e desafios com programação orientada aos objetos!
