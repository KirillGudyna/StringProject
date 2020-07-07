package com.gudyna.stringtask.reader;

import com.gudyna.stringtask.exception.ProgramException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserReader {
    public String fillArrayFile(String fileName) throws ProgramException {
        if (fileName==null) {
            throw new ProgramException("You enter empty file name!");
        }
        Path path = Paths.get(fileName);
        Scanner scanner;
        String text;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new ProgramException("Wrong file name!");
        }
        text = scanner.nextLine();
        return text;
    }

}
