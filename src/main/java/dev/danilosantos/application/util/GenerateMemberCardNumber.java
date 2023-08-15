package dev.danilosantos.application.util;

import java.util.Random;

public class GenerateMemberCardNumber {

    public String generate() {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int cardLength = 5;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(cardLength);

        for (int i = 0; i < cardLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}

