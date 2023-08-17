package dev.danilosantos.application;

import dev.danilosantos.application.util.ClearScreen;
import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.MemberResource;
import dev.danilosantos.domain.SpaceManagementResource;
import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;

import java.util.Date;
import java.util.Scanner;

public class SpaceManagementPage {
    private final SpaceManagementResource spaceManagementResource = new SpaceManagementResource();
    private final MemberResource memberResource = new MemberResource();

    public void start(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nSistema de gestão de espaços - SELECIONE ALGUMA OPÇÃO\n\n1- Inserir novo espaço\n2- Reservar espaço para membro\n3- Voltar para página inicial");
            switch (scanner.nextInt()) {
                case 1 -> insertSpace(scanner);
                case 2 -> spaceUse(scanner);
                case 3 -> {
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
        System.out.print("Digite a capacidade do novo espaço: ");
        Integer maxCapacity = scanner.nextInt();
        ClearScreen.clear();
        System.out.println(spaceManagementResource.insertNewSpace(spaceCategory, name, maxCapacity));
    }

    private void spaceUse(Scanner scanner) {
        getAllSpaces();
        System.out.print("\nQual dos espaços foi utilizado: ");
        int spaceValue = scanner.nextInt();
        if (spaceManagementResource.getAllSpaces().size() + 1 > spaceValue) {
            System.out.print("Digite o numero de carteirinha do membro: ");
            String memberCardNumber = scanner.next();
            if (memberResource.findByCardNumber(memberCardNumber) != null) {
                try {
                    System.out.print("Digite a data da utilização(DD/MM/YYYY): ");
                    Date date = spaceManagementResource.stringToDate(scanner.next());
                    System.out.print("Digite qual foi a hora de entrada(HH:MM): ");
                    Date timeEnter = spaceManagementResource.stringToTime(scanner.next());
                    System.out.print("Digite a quantidade de horas que foi utilizado: ");
                    Integer timeInUse = scanner.nextInt();
                    ClearScreen.clear();
                    System.out.println(spaceManagementResource.registerUse(spaceValue, memberCardNumber, date, timeEnter, timeInUse));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else {
                ClearScreen.clear();
                System.out.println("*ERRO: Sócio não encontrado*");
            }
        } else {
            ClearScreen.clear();
            System.out.println("*ERRO: Espaço não encontrado*");
        }
    }

    private void getAllSpaces() {
        int count = 1;
        for (Space space : spaceManagementResource.getAllSpaces()) {
            System.out.println(count + ": " + space);
            count++;
        }
    }

    private SpaceCategory getSpaceCategory(Scanner scanner) {
        System.out.println("Escolha algum espaço:");
        System.out.println("1- Esportes\n2- Recreação\n3- Relaxamento");
        while (true) {
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
