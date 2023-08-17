package dev.danilosantos.application;

import dev.danilosantos.application.util.ClearScreen;
import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.SpaceManagementResource;
import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;

import java.util.Scanner;

public class SpaceManagementPage {
    SpaceManagementResource spaceManagementResource = new SpaceManagementResource();

    public void start(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nSistema de gestão de espaços - SELECIONE ALGUMA OPÇÃO\n\n1- Inserir novo espaço\n2- Reservar espaço para membro");

            switch (scanner.nextInt()) {
                case 1 -> insertSpace(scanner);
                case 2 -> spaceReservation(scanner);
                case 5 -> {
                    ClearScreen.clear();
                    running = false;
                }
                default -> {
                    ClearScreen.clear();
                    System.out.println(Strings.ERROR_INVALID_VALUE);
                }
            }
        }
    }

    private void insertSpace(Scanner scanner) {
        SpaceCategory spaceCategory = getSpaceCategory(scanner);
        scanner.nextLine();
        System.out.print("Digite o nome do novo espaço: ");
        String name = scanner.nextLine();
        ClearScreen.clear();
        System.out.println(spaceManagementResource.insertNewSpace(spaceCategory, name));
    }

    private void spaceReservation(Scanner scanner) {
        System.out.println("\nEscolha algum espaço:");
        getAllSpaces();

    }

    private void getAllSpaces() {
        int count = 1;
        for(Space space : spaceManagementResource.getAllSpaces()) {
            System.out.println(count + ": " + space);
            count ++;
        }
    }

    private SpaceCategory getSpaceCategory(Scanner scanner) {
        System.out.println("Escolha algum espaço:");
        System.out.println("1- Esportes\n2- Recreação\n3- Relaxamento");
        while(true) {
            switch (scanner.nextInt()) {
                case 1 -> {
                    return SpaceCategory.ESPORTES;
                }
                case 2 -> {
                    return SpaceCategory.RECREACAO;
                }
                case 3 -> {
                    return SpaceCategory.RELAXAMENTO;
                }
                default -> {
                    ClearScreen.clear();
                    System.out.println(Strings.ERROR_INVALID_VALUE);
                }
            }
        }
    }
}
