package com.conversordemoedas.main;


public class Main {
    public static void main(String[] args) {
        useMenu();
    }

    private static void useMenu(){
        Menus menus = new Menus();
        menus.displayMenu();
    }
}