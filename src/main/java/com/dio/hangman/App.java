package com.dio.hangman;

import com.dio.hangman.model.HangmanChar;
import com.dio.hangman.model.HangmanGame;

import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();

        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);
        hangmanGame.inputCharacter('t');
        System.out.println(hangmanGame);
    }
}
