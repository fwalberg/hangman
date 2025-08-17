package com.dio.hangman.utils;

import com.dio.hangman.App;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class DictionaryService {

    private DictionaryService() {
    }

    public static String getRandomWordFromDictionary() throws IOException {
        String word = loadRandomWordFromDictionary();
//        return addSpacesBetweenLetters(word);
        return word;
    }

    private static String loadRandomWordFromDictionary() throws IOException {
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("dictionary.txt")) {
            if (inputStream == null) {
                throw new IOException("Arquivo dictionary.txt não encontrado no diretório resources");
            }

            var content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            String[] words = content.split(System.lineSeparator());

            List<String> validWords = List.of(words).stream()
                    .map(String::trim)
                    .filter(word -> !word.isEmpty())
                    .toList();

            if (validWords.isEmpty()) {
                throw new IOException("Nenhuma palavra válida encontrada no dicionário");
            }

            var random = new Random();
            return validWords.get(random.nextInt(validWords.size())).toLowerCase();
        }
    }

    private static String addSpacesBetweenLetters(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        return word.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining(" "));
    }
}