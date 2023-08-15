package dev.danilosantos.application;

import dev.danilosantos.application.util.ClearScreen;
import dev.danilosantos.domain.util.GenerateMemberCardNumber;
import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.MemberResource;
import dev.danilosantos.infrasctructure.Document;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.enums.DocumentType;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MemberPage {

    GenerateMemberCardNumber generateCardNumber = new GenerateMemberCardNumber();
    MemberResource memberResource = new MemberResource();
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    String name, cardNumber;
    Document document;
    Member member, updatedMember;

    public MemberPage() {
        if (!createFoldersAndFiles.create()) {
            memberResource.getMembers().addAll(memberResource.getAllMembersFromDocument());
        }
    }

    public void registerOfMembers(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println(Strings.MENU_MEMBER_PAGE);

            switch (scanner.nextInt()) {
                case 1:
                    insertMember(scanner);
                    break;

                case 2:
                    findByDocument(scanner);
                    break;

                case 3:
                    findByName(scanner);
                    break;

                case 4:
                    updateMember(scanner);
                    break;

                case 5:
                    deleteMember(scanner);
                    break;
                case 6:
                    ClearScreen.clear();
                    findAll();
                    break;
                case 7:
                    ClearScreen.clear();
                    running = false;
                    break;

                default:
                    ClearScreen.clear();
                    System.out.println(Strings.ERROR_INVALID_VALUE);
                    break;
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
        if (memberResource.getMembers().size() != 0) {
            System.out.println(Strings.MEMBERS_FINDED + memberResource.getMembers().size());
            memberResource.getMembers().forEach(System.out::println);
        } else {
            System.out.println(Strings.ERROR_MEMBER_NOT_FOUND);
        }
    }

    private String getNameFromScanner(Scanner scanner) {
        boolean isSpelledRight = false;
        while (isSpelledRight == false) {
            System.out.print(Strings.WRITE_MEMBER_NAME);
            String localName = scanner.nextLine();
            if (localName.matches("[\\p{L}\\s]+") && localName.trim() != "") {
                isSpelledRight = true;
                return localName.trim();
            } else {
                ClearScreen.clear();
                System.out.println(Strings.ERROR_INVALID_NAME);
            }
        }
        return null;
    }

    private Document verificationToInsertMemberOnDocument(Document document, Scanner scanner) {
        Document documentToInsert = document;
        if (memberResource.verifyIfDocumentAlredyExists(documentToInsert) == false) {
            return documentToInsert;
        } else {
            System.out.println(Strings.ERROR_DOCUMENT_ALREDY_EXISTS);
            System.out.println(Strings.MENU_DOCUMENT_ALREDY_EXISTS);
            switch (scanner.nextInt()) {
                case 1:
                    return getDocumentFromScanner(scanner);
                case 2:
                    break;
                default:
                    System.out.println(Strings.ERROR_INVALID_VALUE);
                    break;
            }
        }
        return null;
    }

    private Document getDocumentFromScanner(Scanner scanner) {
        DocumentType documentType = null;
        String documentValue = null;
        boolean isSpelledRight = false;
        while (isSpelledRight == false) {
            System.out.print(Strings.WRITE_DOCUMENT_TYPE);
            String typeFromScanner = scanner.next().toUpperCase();
            if (typeFromScanner.equals("RG") || typeFromScanner.equals("CPF")) {
                documentType = DocumentType.valueOf(typeFromScanner);
                isSpelledRight = true;
            } else {
                System.out.println(Strings.ERROR_INVALID_VALUE);
            }
        }
        isSpelledRight = false;
        while (isSpelledRight == false) {
            System.out.print(Strings.writeDocumentValue(documentType.toString()));
            documentValue = scanner.next();
            if (documentValue.matches("[+-]?\\d*(\\.\\d+)?")) {
                isSpelledRight = true;
            } else {
                System.out.println(Strings.ERROR_INVALID_VALUE);
            }
        }

        if (documentType != null || documentValue != null) {
            return new Document(documentType, documentValue);
        } else {
            return null;
        }
    }
}
