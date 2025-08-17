# Jogo da Forca (Hangman Game)

Um jogo da forca implementado em Java utilizando programação orientada a objetos e recursos modernos do Java 24.

## Descrição

Este é um projeto de jogo da forca desenvolvido em Java que permite ao usuário adivinhar palavras selecionadas aleatoriamente. O jogo apresenta uma interface de console interativa com um boneco da forca visual que é construído progressivamente a cada erro.

##  Funcionalidades

- **Interface interativa via console**: Menu com opções claras para o usuário
- **Seleção aleatória de palavras**: Sistema dinâmico de carregamento de palavras
- **Representação visual da forca**: Desenho ASCII que se constrói a cada erro
- **Sistema de tentativas**: Controle de letras já informadas e tentativas restantes
- **Estados do jogo**: Controle de vitória, derrota e jogo em andamento
- **Tratamento de exceções**: Mensagens claras para situações especiais

## Como Jogar

1. Execute a aplicação
2. Uma palavra será selecionada aleatoriamente
3. Escolha uma das opções do menu:
    - **1**: Informar uma letra
    - **2**: Verificar status do jogo
    - **3**: Sair do jogo
4. Tente adivinhar a palavra antes que o boneco da forca seja completado!

##  Estrutura do Projeto
```
src/main/java/com/dio/hangman/ 
├── App.java
├── exception/ 
│ ├── GameIsFinishedException.java 
│ └── LetterAlreadyInputtedException.java
├── model/ 
│ ├── HangmanChar.java 
│ ├── HangmanGame.java
│ └── HangmanGameStatus.java 
└── utils/ 
	└── DictionaryService.java 
```

## Sistema de Dicionário Dinâmico

### DictionaryService
Uma das principais melhorias implementadas no projeto foi a criação da classe `DictionaryService`, que proporciona **dinamicidade** ao jogo através de:

- **Carregamento automático de palavras**: Lê palavras de um arquivo externo
- **Seleção aleatória**: Escolhe automaticamente uma palavra diferente a cada jogo
- **Validação de conteúdo**: Verifica se o arquivo existe e contém palavras válidas
- **Tratamento de erros**: Mensagens claras em caso de problemas no carregamento

### dictionary.txt
Arquivo localizado em `src/main/resources/dictionary.txt` contendo:

- **Mais de 300 palavras** em português
- **Categorias diversas**: animais, objetos, natureza, ciência, profissões, etc.
- **Fácil manutenção**: Adicione ou remova palavras editando o arquivo
- **Codificação UTF-8**: Suporte completo a caracteres especiais e acentos

**Vantagens do sistema dinâmico:**
- ✅ Não há necessidade de recompilar o código para alterar palavras
- ✅ Possibilidade de criar diferentes níveis de dificuldade
- ✅ Fácil personalização para diferentes idiomas ou temas
- ✅ Escalabilidade para milhares de palavras

## Tecnologias Utilizadas

- **Java 24**: Versão mais recente com recursos modernos
- **Lombok**: Redução de código boilerplate
- **Maven**: Gerenciamento de dependências
- **Stream API**: Programação funcional para manipulação de dados

##  Dependências

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.38</version>
</dependency>
```

## Conceitos Aplicados

- **Orientação a Objetos**: Encapsulamento, herança e polimorfismo
- **Tratamento de Exceções**: Exceções customizadas para controle de fluxo
- **Streams e Lambdas**: Programação funcional moderna
- **Padrão de Projeto**: Service para separação de responsabilidades
- **Clean Code**: Código limpo e bem estruturado

## Melhorias Futuras

- Interface gráfica (GUI)
- Diferentes níveis de dificuldade
- Temas personalizáveis