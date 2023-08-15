package dev.danilosantos.application.util;

public class ClearScreen {

    public static void clear() {
        for (int i = 0; i < 40; i++) {
            System.out.println("\n");
        }
    }
}
