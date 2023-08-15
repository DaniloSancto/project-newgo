package dev.danilosantos.domain;

import com.google.gson.Gson;
import dev.danilosantos.application.util.Strings;
import dev.danilosantos.domain.util.GenerateMemberCardNumber;
import dev.danilosantos.infrasctructure.Document;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.dao.MemberDao;
import dev.danilosantos.infrasctructure.file_management.Routes;
import dev.danilosantos.infrasctructure.util.DateFormat;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberResource {
    MemberDao memberDao = new MemberDao();
    GenerateMemberCardNumber generateCardNumber = new GenerateMemberCardNumber();
    Gson gson = new Gson();

    private final List<Member> members = new ArrayList<>();

    // método verifica se os dados passados pelo usuário estão corretos e faz a inserção no documento
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

    public String findByDocument(Document document) {
        for (Member entity : members) {
            if (entity.getDocument().getType().equals(document.getType())
                    && entity.getDocument().getValue().equals(document.getValue())) {
                return entity.toString();
            }
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    public String findByName(String name) {
        List<Member> membersByName = new ArrayList<>();
        for (Member entity : members) {
            if (entity.getName().toUpperCase().contains(name.toUpperCase())) {
                membersByName.add(entity);
            }
        }
        if (!membersByName.isEmpty()) {
            return Strings.MEMBERS_FINDED + membersByName.size() + "\n" + membersByName.toString();
        }

        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    public Member findByCardNumber(String cardNumber) {
        for (Member entity : members) {
            if (entity.getCardNumber().equalsIgnoreCase(cardNumber)) {
                return entity;
            }
        }
        return null;
    }

    public String findAllMembers() {
        if (!members.isEmpty()) {
            return Strings.MEMBERS_FINDED + members.size() + "\n" + members.toString();
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    public String updateMemberByCardNumber(String cardNumber, String name, Document document) {
        Member entity = findByCardNumber(cardNumber);

        if (entity.getCardNumber().equals(cardNumber)) {
            members.set(members.indexOf(entity), new Member(entity.getCardNumber(), name, new Date(), document));
            memberDao.updateDocument(members);
            return Strings.updatedMember(name);
        }
        return Strings.ERROR_TO_UPDATE_MEMBER;
    }

    public String deleteMemberByCardNumber(String cardNumber) {
        Member entity = findByCardNumber(cardNumber);

        if (entity != null) {
            members.remove(entity);
            memberDao.updateDocument(members);
            return Strings.MEMBER_SUCCESSFULLY_DELETED;
        }
        return Strings.ERROR_MEMBER_NOT_FOUND;
    }

    public List<Member> getAllMembersFromDocument() {
        List<Member> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Routes.MEMBER_FILE_PATH))) {
            String line = reader.readLine();

            while (line != null) {
                Member member = gson.fromJson(line, Member.class);

                list.add(member);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean verifyIfDocumentAlredyExists(Document document) {
        for (Member entity : members) {
            if (entity.getDocument().getType().equals(document.getType())
                    && entity.getDocument().getValue().equals(document.getValue())) {
                return true;
            }
        }
        return false;
    }

    public List<Member> getMembers() {
        return members;
    }
}
