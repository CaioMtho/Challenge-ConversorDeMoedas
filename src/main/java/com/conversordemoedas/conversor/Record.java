package com.conversordemoedas.conversor;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Record {
    private final static String FILE_NAME = getAppDataPath() + "/records.txt";

    private static String getAppDataPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String appDataPath;
        if (os.contains("win")) {
            appDataPath = System.getenv("AppData");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            appDataPath = System.getProperty("user.home") + "/.local/share";
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado: " + os);
        }
        return appDataPath + "/ConversorDemoedas";
    }

    private void checkAndCreateFile() {
        try {
            Path path = Paths.get(FILE_NAME);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("ERRO: Nao foi possivel  criar arquivo de persistencia: " + e.getMessage());
        }
    }

    private boolean isFileFormatCorrect() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            return line != null && line.split(",").length == 3;
        } catch (IOException e) {
            return false;
        }
    }

    private void formatFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("currencyConversion,currentValue,finalValue\n");
        } catch (IOException e) {
            System.out.println("ERRO: Nao foi possivel formatar arquivo: " + e.getMessage());
        }
    }

    public void registerLog(String currencyConversion, double currentValue, double finalValue) {
        checkAndCreateFile();
        if (!isFileFormatCorrect()) {
            formatFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(currencyConversion + "," + currentValue + "," + finalValue + "\n");
            System.out.println("Registro armazenado...");
        } catch (IOException e) {
            System.out.println("ERRO: Nao foi possivel armazenar registro: " + e.getMessage());
        }
    }

    public void showRecords() {
        checkAndCreateFile();
        if (!isFileFormatCorrect()) {
            formatFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.println("Conversão: " + parts[0] + ", Valor Atual: " + parts[1] + ", Valor Final: " + parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Nao foi possivel ler os registros: " + e.getMessage());
        }
    }

    // O id do registro e a posicao dele na lista comecando por 0
    public void deleteLogById(int id) {
        checkAndCreateFile();
        if (!isFileFormatCorrect()) {
            formatFile();
        }
        List<String> lines = new ArrayList<>();
        boolean recordFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int currentId = 0;
            while ((line = reader.readLine()) != null) {
                if (currentId != id) {
                    lines.add(line);
                } else {
                    recordFound = true;
                }
                currentId++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler registros: " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            if (recordFound) {
                System.out.println("Registro com ID " + id + " foi apagado...");
            } else {
                System.out.println("ERRO: Nenhum registro encontrado com ID " + id);
            }
        } catch (IOException e) {
            System.out.println("ERRO: Nao foi possivel apagar registro: " + e.getMessage());
        }
    }


    public void deleteAllLogs() {
        checkAndCreateFile();
        formatFile();
        System.out.println("Todos os registros foram apagados...");
    }
}
