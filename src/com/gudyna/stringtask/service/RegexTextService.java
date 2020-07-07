package com.gudyna.stringtask.service;

import com.gudyna.stringtask.exception.ProgramException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTextService {
    private static final String PATTERN_ORIGINAL_LETTER = "(\\b\\S{%d})(\\S)(\\S*\\b)";
    private static final String REPLACE_GROUP = "$1%s$3";
    private static final String REGEX_NOT_SPACE_LETTER = "([^\\p{L}\\s]+)";
    private static final String SPACE = " ";
    private static final String REGEX_SPECIFIC_LETTER = "\\b[\\p{L}&&[^\\dауоыиэяюёАУОЫИЭЯЮЁеaeiouAEIOU]][\\S]{%d}\\b";
    private static final String EMPTY_LINE = "";

    public String replaceLetter(int index, String text, char replaceLetter) throws ProgramException {
        if (text == null || index < 0) {
            throw new ProgramException("Data are not valid!");
        }
        String regEx = String.format(PATTERN_ORIGINAL_LETTER, index - 1);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        String group = String.format(REPLACE_GROUP, replaceLetter);
        text = matcher.replaceAll(group);
        return text;
    }

    public String replaceOneWithAnother(String text, char target, char wrongLetter, char replaceLetter) throws ProgramException {
        String wrong = String.valueOf(target) + wrongLetter;
        String correct = String.valueOf(target) + replaceLetter;
        return text.replaceAll(wrong, correct);
    }

    public String replaceOneWordWithAnother(String text, String target, String replacement) throws ProgramException {
        if (text == null || target == null || replacement == null) {
            throw new ProgramException("Data are not valid!");
        }
        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll(replacement);
        return text;
    }

    public String deleteAllNonLetterCharacters(String text) throws ProgramException {
        if (text == null) {
            throw new ProgramException("Input text has null pointer!");
        }
        Pattern pattern = Pattern.compile(REGEX_NOT_SPACE_LETTER, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll(SPACE);
        return text;
    }

    public String deleteSpecificWords(String text, int size) {
        String regex = String.format(REGEX_SPECIFIC_LETTER, size);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(EMPTY_LINE);
    }
}
