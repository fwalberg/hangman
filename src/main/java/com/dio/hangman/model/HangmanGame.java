package com.dio.hangman.model;

import com.dio.hangman.exception.GameIsFinishedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HangmanGame {

    private final static int HANGMAN_INITIAL_LINE_LENGTH = 9;
    private final static int HANGMAN_INITIAL_LINE_LENGTH_WITH_SEPARATOR = 10;

    private final int lineSize;
    private final int hangmanInitialize;
    private final List<HangmanChar> hangmanPaths;
    private final List<HangmanChar> characters;
    private final List<Character> failAttempts = new ArrayList<>();

    private String hangman;
    private HangmanGameStatus hangmanGameStatus;

    public HangmanGame(final List<HangmanChar> characters) {
        var whiteSpaces = " ".repeat(characters.size());
        var characterSpace = "-".repeat(characters.size());
        this.hangmanGameStatus = HangmanGameStatus.PENDING;
        this.hangmanPaths = buildHangmanPathsPositions();
        this.lineSize = HANGMAN_INITIAL_LINE_LENGTH_WITH_SEPARATOR + whiteSpaces.length();
        this.characters = setCharacterSpacesPositionInGame(characters, whiteSpaces.length());
        buildHangmanDesign(whiteSpaces, characterSpace);
        this.hangmanInitialize = hangman.length();
    }

    private List<HangmanChar> buildHangmanPathsPositions() {
        final var HEAD_LINE = 3;
        final var BODY_LINE = 4;
        final var LEGS_LINE = 5;

        return new ArrayList<>(List.of(
                new HangmanChar('0', this.lineSize * HEAD_LINE + 6),
                new HangmanChar('|', this.lineSize * BODY_LINE + 6),
                new HangmanChar('/', this.lineSize * BODY_LINE + 5),
                new HangmanChar('\\', this.lineSize * BODY_LINE + 7),
                new HangmanChar('/', this.lineSize * LEGS_LINE + 5),
                new HangmanChar('\\', this.lineSize * LEGS_LINE + 7)
        ));

    }

    private void buildHangmanDesign(final String whiteSpace, final String characterSpace) {
        this.hangman = "   -----  " + whiteSpace + System.lineSeparator() +
                        "  |   |  " + whiteSpace + System.lineSeparator() +
                        "  |   |  " + whiteSpace + System.lineSeparator() +
                        "  |      " + whiteSpace + System.lineSeparator() +
                        "  |      " + whiteSpace + System.lineSeparator() +
                        "  |      " + whiteSpace + System.lineSeparator() +
                        "  |      " + whiteSpace + System.lineSeparator() +
                        "=========" + characterSpace + System.lineSeparator();
    }

    public void inputCharacter(final char character) {
        if (this.hangmanGameStatus != HangmanGameStatus.PENDING) {
            var message = this.hangmanGameStatus == HangmanGameStatus.WIN ?
                    "PARABÉNS! Você venceu." :
                    "VOCÊ PERDEU! Tente novamente.";
            throw new GameIsFinishedException(message);
        }

        var found = this.characters.stream()
                .filter(c -> c.getCharacter() == character)
                .toList();

        if (found.isEmpty()) {
            failAttempts.add(character);

            if (failAttempts.size() >= 6) {
                this.hangmanGameStatus = HangmanGameStatus.LOSE;
            }
            rebuildHangman(this.hangmanPaths.removeFirst());
            return;
        }

        this.characters.forEach(c -> {
            if (c.getCharacter() == found.getFirst().getCharacter()) {
                c.enableVisibility();
            }
        });

        if (this.characters.stream().noneMatch(HangmanChar::isInvisible)) {
            this.hangmanGameStatus = HangmanGameStatus.WIN;
        }

        rebuildHangman(found.toArray(HangmanChar[]::new));
    }

    private List<HangmanChar> setCharacterSpacesPositionInGame(final List<HangmanChar> characters, final int whiSpacesAmount) {
        final var LINE_LETTER =6;
        for (int i = 0; i < characters.size(); i++) {
            characters.get(i).setPosition(this.lineSize * LINE_LETTER + HANGMAN_INITIAL_LINE_LENGTH + i);
        }
        return characters;
    }

    private void rebuildHangman(final HangmanChar... hangmanChars) {
        var hangmanBuilder = new StringBuilder(this.hangman);
        Stream.of(hangmanChars)
                .forEach(h -> hangmanBuilder.setCharAt(h.getPosition(), h.getCharacter()));
        var failMessage = this.failAttempts.isEmpty() ?
                "" : "Tentativas" + this.failAttempts;

        this.hangman = hangmanBuilder.substring(0, hangmanInitialize) + failMessage;
    }

    @Override
    public String toString() {
        return hangman;
    }
}
