package com.conversordemoedas.main;

import com.conversordemoedas.conversor.Converter;
import com.conversordemoedas.conversor.ExchangeRateConnect;
import com.conversordemoedas.conversor.Record;

import java.io.IOException;
import java.util.Scanner;

public class Menus extends MenuOperations {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\t===== CONVERSOR DE MOEDAS =====");
            System.out.println("\tDigite o numero de uma das opcoes:");
            System.out.println("1 - Converter moedas");
            System.out.println("2 - Checar registros");
            System.out.println("3 - Sair");

            int userInput = getIntegerValorInput(scanner);
            switch (userInput){
                case 1:
                    displayConverterMenu(scanner);
                    break;
                case 2:
                    displayLogsMenu(scanner);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("ERRO: Verifique o digito");
            }
        }
    }

    private void displayConverterMenu(Scanner scanner) {
        while (true) {
            System.out.println("\t===== CONVERTER =====");
            System.out.println("\tUse o codigo ISO 4217 para as moedas correntes.");

            String userCurrency = getIsoInput(scanner, "Digite o codigo ISO 4217 da moeda corrente base ou digite QUIT para sair: ");
            if (userCurrency == null) break;

            String targetCurrency = getIsoInput(scanner, "Digite o codigo da moeda corrente alvo ou QUIT para sair: ");
            if (targetCurrency == null) break;

            var valor = getDoubleValorInput(scanner);
            Converter converter = new Converter(userCurrency);
            converter.convertCurrency(targetCurrency, valor);
        }
    }

    private void displayLogsMenu(Scanner scanner){
        Record record = new Record();
        boolean status = true;
        while (status) {
            System.out.println("\t===== MENU DE REGISTRO =====");
            System.out.println("\tO registro armazena as operacoes do programa na maquina atual");
            System.out.println("\tDigite o numero de uma das opcoes:");
            System.out.println("1 - Ver meu registro");
            System.out.println("2 - Deletar um registro");
            System.out.println("3 - Formatar registro");
            System.out.println("4 - Sair");

            int userInput = getIntegerValorInput(scanner);
            switch (userInput){
                case 1:
                    record.showRecords(); break;
                case 2:
                    System.out.println("Digite o id do registro: ");
                    int idLog = getIntegerValorInput(scanner);
                    record.deleteLogById(idLog);
                    break;
                case 3:
                    record.deleteAllLogs(); break;
                case 4:
                    System.out.println("Saindo..."); status = false; break;
                default:
                    System.out.println("ERRO: Verifique o digito");
            }
        }
    }
}
    class MenuOperations {

    protected String getIsoInput(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            var input = scanner.next().toUpperCase();

            if (input.equals("QUIT")) {
                System.out.println("Saindo...");
                return null;
            } else if (!isValidCurrencyInput(input)) {
                System.out.println("ERRO: Verifique se o codigo ISO 4217 esta correto");
            } else {
                return input;
            }
        }
    }

    private boolean isValidCurrencyInput(String code) {
        ExchangeRateConnect exchangeRateConnect = new ExchangeRateConnect("629f331637b5e64d9abf4b2e");
        return exchangeRateConnect.isValidIsoCode(code);
    }

    protected int getIntegerValorInput(Scanner scanner){
        while (true) {
            try {
                String input = scanner.next();
                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Digito invalido. Tente novamente.");
            }
        }
    }

    protected double getDoubleValorInput(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Digite o valor na moeda atual ou QUIT para sair.");
                String input = scanner.next();
                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Digito invalido. Tente novamente.");
            }
        }
    }
}
