package com.gudyna.stringtask.service;

import com.gudyna.stringtask.exception.ProgramException;

import java.awt.*;

public class StringTextService {
    private static final String REGEX_SPLIT_NOT_SPACE_LETTER = "[\\p{L}\\s]";
    private static final String EMPTY_LINE = "";
    private static final String SPLIT_WORDS = "\\s*(\\s|,|!|\\.|;|:|\\?)\\s*";
    private static final String QUOTE = "\\Q%s\\E";
    private static final String SPACE = " ";
    private static final String CONSONANTS = "бвгджзйклмнпрстфхцчшщbcdfghjklmnpqrstvwxz";

    public String replaceLetter(int index, String text, char replaceLetter) throws ProgramException {
        if (text == null || index < 0) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(SPLIT_WORDS);
        for (String word : words) {
            if (word.length() >= index) {
                StringBuilder sb = new StringBuilder(word);
                String regex = "\\b" + word + "\\b";
                sb.setCharAt(index - 1, replaceLetter);
                text = text.replaceFirst(regex, sb.toString());
            }
        }
        return text;
    }

    public String replaceOneWithAnother(String text, char target, char wrongLetter, char replaceLetter) throws ProgramException {
        if (text == null) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(SPLIT_WORDS);
        for (String word : words) {
            boolean isContains = word.toLowerCase().contains(EMPTY_LINE + target + wrongLetter);
            if (isContains) {
                StringBuilder sb = new StringBuilder(word);
                String regex = "\\b" + word + "\\b";
                int index = word.indexOf(wrongLetter);
                sb.setCharAt(index, replaceLetter);
                text = text.replaceAll(regex, sb.toString());
            }
        }
        return text;
    }

    public String replaceOneWordsWithAnother(String text, String target, String replacement) throws ProgramException {
        if (text == null || target == null || replacement == null) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(SPLIT_WORDS);
        for (String word : words) {
            if (word.equalsIgnoreCase(target)) {
                text = text.replaceAll(word, replacement);
            }
        }
        return text;
    }

    public String replaceWordByLength(String text, int wordLength, String replacement) throws ProgramException {
        if (text == null || wordLength < 0 || replacement == null) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(SPLIT_WORDS);
        for (String word : words) {
            if (word.length() == wordLength) {
                text = text.replaceAll(word, replacement);
            }
        }
        return text;
    }

    public String deleteAllNonLetterCharacters(String text) throws ProgramException {
        if (text == null) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(REGEX_SPLIT_NOT_SPACE_LETTER);
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            word = String.format(QUOTE, word);
            text = text.replaceFirst(word, EMPTY_LINE);
        }
        return text;
    }

    private boolean isConsonant(char character) {
        char ch = Character.toLowerCase(character);
        boolean flag = false;
        for (int i = 0; i < CONSONANTS.length(); i++) {
            if (ch == CONSONANTS.charAt(i)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public String deleteSpecificWords(String text, int size) throws ProgramException{
        if (text == null||size<0) {
            throw new ProgramException("Data are not valid!");
        }
        String[] words = text.split(SPLIT_WORDS);
        for (String word : words) {
            if (word.length() == size && isConsonant(word.charAt(0))) {
                text = text.replaceFirst(word, EMPTY_LINE);
            }
        }
        return text;
    }
}
