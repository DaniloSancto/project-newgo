package dev.danilosantos.domain;

import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.util.GenerateMemberCardNumber;
import dev.danilosantos.infrasctructure.Document;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.dao.MemberDao;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberResource {
    MemberDao memberDao = new MemberDao();
    GenerateMemberCardNumber generateCardNumber = new GenerateMemberCardNumber();
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();
    private final List<Member> members = new ArrayList<>();

    public MemberResource() {
        if (!createFoldersAndFiles.createFolderAndMemberFile()) {
            members.addAll(getAllMembersFromDocument());
        }
    }

    // Verifica se os dados passados pelo usuário estão corretos e faz a inserção no documento
    public String insertMember(String name, Document document) {
        if (document != null && name != null && !name.isEmpty()) {
            Member member = new Member(generateCardNumber.generate(), name, new Date(), document);
            memberDao.insert(member);
            members.add(member);
            return Strings.MEMBER_SUCCESSFULLY_INSERTED;
        } else {
            return Strings.ERROR_TO_INSERT_MEMBER;
        }
    }

    // Procura na lista de sócios se existe algum com o documento e retorna se existe ou não
    public String findByDocument(Document document) {
        for (Member entity : members) {
            if (entity.getDocument().getType().equals(document.getType())
                    && entity.getDocument().getValue().equals(document.getValue())) {
                return entity.toString();
            }
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    // Procura na lista de sócios se existe algum com o nome e retorna se existe ou não
    public String findByName(String name) {
        List<Member> membersByName = new ArrayList<>();
        for (Member entity : members) {
            if (entity.getName().toUpperCase().contains(name.toUpperCase())) {
                membersByName.add(entity);
            }
        }
        if (!membersByName.isEmpty()) {
            return Strings.MEMBERS_FINDED + membersByName.size() + "\n" + membersByName;
        }

        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    // procura na lista de membro um membro com a carteirinha se não existe retorna nulo
    public Member findByCardNumber(String cardNumber) {
        for (Member entity : members) {
            if (entity.getCardNumber().equalsIgnoreCase(cardNumber)) {
                return entity;
            }
        }
        return null;
    }

    //procura retorna todos os membros da lista, caso não exista nenhum retorna um erro
    public String findAllMembers() {
        if (!members.isEmpty()) {
            return Strings.MEMBERS_FINDED + members.size() + "\n" + members;
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    //recebe valores e atualiza um membro na lista
    public String updateMemberByCardNumber(String cardNumber, String name, Document document) {
        Member entity = findByCardNumber(cardNumber);

        if (entity != null && entity.getCardNumber().equals(cardNumber)) {
            members.set(members.indexOf(entity), new Member(entity.getCardNumber(), name, new Date(), document));
            memberDao.updateDocument(members);
            return Strings.updatedMember(name);
        }
        return Strings.ERROR_TO_UPDATE_MEMBER;
    }

    //procura um membro caso encotre deleta ele da lista, caso nçao encotre retorna um erro
    public String deleteMemberByCardNumber(String cardNumber) {
        Member entity = findByCardNumber(cardNumber);

        if (entity != null) {
            members.remove(entity);
            memberDao.updateDocument(members);
            return Strings.MEMBER_SUCCESSFULLY_DELETED;
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    // retorna uma lista de membros existente no documento
    public List<Member> getAllMembersFromDocument() {
        return memberDao.readAllMembersFromDocument();
    }

    public boolean verifyIfDocumentExists(Document document) {
        for (Member entity : members) {
            if (entity.getDocument().getType().equals(document.getType())
                    && entity.getDocument().getValue().equals(document.getValue())) {
                return true;
            }
        }
        return false;
    }
}
