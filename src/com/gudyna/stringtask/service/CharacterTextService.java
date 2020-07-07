package com.gudyna.stringtask.service;

import com.gudyna.stringtask.exception.ProgramException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CharacterTextService {
    private static final String PUNCT = " ,.!?;:";
    private static final char SPACE = ' ';

    public String replaceLetter(int index, String text, char replaceLetter) throws ProgramException {
        if (text == null || index < 0) {
            throw new ProgramException("Data are not valid!");
        }
        char[] chars = text.toCharArray();
        int count = 0;
        Pattern pattern = Pattern.compile(PUNCT);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != SPACE && !pattern.matcher(Character.toString(chars[i])).matches()) {
                count++;
                if (count == index) {
                    chars[i] = replaceLetter;
                }
            } else {
                count = 0;
            }
        }
        return new String(chars);
    }

    public String replaceOneWithAnother(String text, char target, char wrongLetter, char replaceLetter) throws ProgramException {
        if (text == null) {
            throw new ProgramException("Data are not valid!");
        }
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1 && Character.toLowerCase(chars[i]) == target &&
                    Character.toLowerCase(chars[i + 1]) == wrongLetter) {
                chars[i + 1] = replaceLetter;
            }
        }
        return new String(chars);
    }

    public String replaceOneWordsWithAnother(String text, String target, String replacement) throws ProgramException {
        if (text == null || target == null || replacement == null) {
            throw new ProgramException("Data are not valid!");
        }
        char[] chars = text.toCharArray();
        char[] charsTarget = target.toCharArray();
        char[] charsReplacement = replacement.toCharArray();
        int delta = charsTarget.length - charsReplacement.length;
        for (int i = 0; i < chars.length; i++) {
            boolean equals = true;
            for (int j = 0; j < target.length(); j++) {
                if (chars[i + j] != charsTarget[j]) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                chars = replace(chars, i, i + target.length(), replacement.toCharArray());
                i += delta;
            }
        }
        return new String(chars);
    }

    public String replaceWordsBySubstring(String text, int size, String substring) {
        char[] chars = text.toCharArray();
        List<String> result = new ArrayList();
        Pattern pattern = Pattern.compile(PUNCT);
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            if (aChar != SPACE && !pattern.matcher(Character.toString(aChar)).matches()) {
                builder.append(aChar);
            } else if (builder.toString().length() == size) {
                result.add(substring);
                result.add(Character.toString(aChar));
                builder.setLength(0);
            } else {
                result.add(builder.toString());
                result.add(Character.toString(aChar));
                builder.setLength(0);
            }
        }
        for (String s : result) {
            builder.append(s);
        }
        return builder.toString();
    }

    private char[] replace(char[] data, int start, int end, char[] sequence) {
        int delta = sequence.length - (end - start + 1);
        int length = data.length + delta + 1;
        char[] newData = new char[length];
        char[] head = Arrays.copyOfRange(data, 0, start);
        char[] tail = Arrays.copyOfRange(data, end, data.length);
        System.arraycopy(head, 0, newData, 0, head.length);
        System.arraycopy(sequence, 0, newData, head.length, sequence.length);
        System.arraycopy(tail, 0, newData, head.length + sequence.length,
                tail.length);
        return newData;
    }
}
