package dev.danilosantos.application;

import dev.danilosantos.application.util.ClearScreen;
import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.MemberResource;
import dev.danilosantos.infrasctructure.Document;
import dev.danilosantos.infrasctructure.enums.DocumentType;

import java.util.Scanner;

public class MemberPage {
    MemberResource memberResource = new MemberResource();
    String name, cardNumber;
    Document document;

    public void registerOfMembers(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println(Strings.MENU_MEMBER_PAGE);

            switch (scanner.nextInt()) {
                case 1 -> insertMember(scanner);
                case 2 -> findByDocument(scanner);
                case 3 -> findByName(scanner);
                case 4 -> updateMember(scanner);
                case 5 -> deleteMember(scanner);
                case 6 -> findAll();
                case 7 -> {
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

    private void insertMember(Scanner scanner) {
        scanner.nextLine();
        name = getNameFromScanner(scanner);
        document = verificationToInsertMemberOnDocument(getDocumentFromScanner(scanner), scanner);
        ClearScreen.clear();
        System.out.println(memberResource.insertMember(name, document));
    }

    private void findByDocument(Scanner scanner) {
        document = getDocumentFromScanner(scanner);
        ClearScreen.clear();
        System.out.println(memberResource.findByDocument(document));
    }

    private void findByName(Scanner scanner) {
        System.out.print(Strings.WRITE_MEMBER_NAME);
        scanner.nextLine();
        name = scanner.nextLine();

        ClearScreen.clear();
        System.out.println(memberResource.findByName(name));
    }

    private void updateMember(Scanner scanner) {
        System.out.print(Strings.WRITE_CARD_NUMBER);
        cardNumber = scanner.next().toUpperCase();
        if (memberResource.findByCardNumber(cardNumber) != null) {
            System.out.println(Strings.NEW_MEMBER_INFO);
            scanner.nextLine();
            name = getNameFromScanner(scanner);
            document = getDocumentFromScanner(scanner);

            ClearScreen.clear();
            System.out.println(memberResource.updateMemberByCardNumber(cardNumber, name, document));
        } else {
            ClearScreen.clear();
            System.out.println(Strings.ERROR_MEMBER_NOT_FOUND);
        }
    }

    private void deleteMember(Scanner scanner) {
        System.out.print(Strings.WRITE_CARD_NUMBER);
        cardNumber = scanner.next().toUpperCase();
        ClearScreen.clear();
        System.out.println(memberResource.deleteMemberByCardNumber(cardNumber));
    }

    private void findAll() {
        ClearScreen.clear();
        System.out.println(memberResource.findAllMembers());
    }

    private String getNameFromScanner(Scanner scanner) {
        while (true) {
            System.out.print(Strings.WRITE_MEMBER_NAME);
            String localName = scanner.nextLine().trim();
            if (localName.matches("[\\p{L}\\s]+") && !localName.isEmpty()) {
                return localName;
            } else {
                ClearScreen.clear();
                System.out.println(Strings.ERROR_INVALID_NAME);
            }
        }
    }

    private Document verificationToInsertMemberOnDocument(Document document, Scanner scanner) {
        if (!memberResource.verifyIfDocumentExists(document)) {
            return document;
        } else {
            System.out.println(Strings.ERROR_DOCUMENT_ALREDY_EXISTS);
            System.out.println(Strings.MENU_DOCUMENT_ALREDY_EXISTS);
            switch (scanner.nextInt()) {
                case 1 -> {
                    return getDocumentFromScanner(scanner);
                }
                case 2 -> {
                }
                default -> System.out.println(Strings.ERROR_INVALID_VALUE);
            }
        }
        return null;
    }

    private Document getDocumentFromScanner(Scanner scanner) {
        DocumentType documentType = getDocumentType(scanner);
        String documentValue = getDocumentValue(scanner);
        return new Document(documentType, documentValue);
    }

    private DocumentType getDocumentType(Scanner scanner) {
        while (true) {
            System.out.print(Strings.WRITE_DOCUMENT_TYPE);
            String typeFromScanner = scanner.next().toUpperCase();
            if (typeFromScanner.equals("RG") || typeFromScanner.equals("CPF")) {
                return DocumentType.valueOf(typeFromScanner);
            } else {
                System.out.println(Strings.ERROR_INVALID_VALUE);
            }
        }
    }

    private String getDocumentValue(Scanner scanner) {
        while (true) {
            System.out.print(Strings.WRITE_DOCUMENT_VALUE);
            String documentValue = scanner.next();
            if (documentValue.matches("[+-]?\\d*(\\.\\d+)?")) {
                return documentValue;
            } else {
                System.out.println(Strings.ERROR_INVALID_VALUE);
            }
        }
    }
}
