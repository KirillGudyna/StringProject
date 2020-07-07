package com.gudyna.stringtask.console;

import com.gudyna.stringtask.exception.ProgramException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {
    public String consoleFill(){
        Scanner scanner = new Scanner(System.in);
        String line;
        line = scanner.nextLine();
        return line;
    }
}

