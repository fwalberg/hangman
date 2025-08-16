package com.dio.hangman.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class HangmanChar {
    private final char character;
    private boolean isVisible;
    private int position;

    public HangmanChar(char character) {
        this.character = character;
        this.isVisible = false;
    }

    public HangmanChar(final char character, final int position) {
        this.character = character;
        this.position = position;
        this.isVisible = true;
    }

    public boolean isInvisible() {
        return !isVisible;
    }

    public void enableVisibility() {
        isVisible = true;
    }
}
