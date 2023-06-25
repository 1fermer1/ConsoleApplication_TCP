package org.example.consoleInputManagers;

import java.io.BufferedReader;
import java.io.File;

public class UserInputService {
    private static BufferedReader bufferedReader;
    private static File scriptFile;

    public static void setScriptFile(File scriptFile) {
        UserInputService.scriptFile = scriptFile;
    }

    public static File getScriptFile() {
        return scriptFile;
    }

    public static void setBufferedReader(BufferedReader bufferedReader) {
        UserInputService.bufferedReader = bufferedReader;
    }

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}