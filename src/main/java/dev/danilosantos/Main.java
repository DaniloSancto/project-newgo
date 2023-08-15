package dev.danilosantos;

import dev.danilosantos.application.HomePage;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        HomePage homePage = new HomePage();

        homePage.screenWriter(new Scanner(System.in));
    }

}
