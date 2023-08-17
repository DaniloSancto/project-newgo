package dev.danilosantos.application;

import dev.danilosantos.application.util.ClearScreen;
import dev.danilosantos.application.util.Strings;

import java.util.Scanner;


public class HomePage {
    MemberPage memberPage = new MemberPage();
    SpaceManagementPage spaceManagementPage = new SpaceManagementPage();

    public HomePage() {
    }

    public void screenWriter(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println(Strings.MENU_HOME_PAGE);

            switch (scanner.nextInt()) {
                case 1:
                    ClearScreen.clear();
                    memberPage.start(scanner);
                    break;

                case 2:
                    ClearScreen.clear();
                    System.out.println("*EM DESENVOLVIMENTO*");
                    break;

                case 3:
                    ClearScreen.clear();
                    System.out.println("*EM DESENVOLVIMENTO*");
                    break;

                case 4:
                    ClearScreen.clear();
                    spaceManagementPage.start(scanner);
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    ClearScreen.clear();
                    System.out.println(Strings.ERROR_INVALID_VALUE);
                    break;
            }
        }

    }

}
