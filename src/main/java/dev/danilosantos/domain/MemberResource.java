package dev.danilosantos.domain;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Document;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberResource {

    private List<Member> members = new ArrayList<>();
    Gson gson = new Gson();

    public boolean insertMember(Member member) {
        members.add(member);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH, true))) {

            writer.write(gson.toJson(member));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        return false;
    }

    public Member findByDocument(Document document) {
        for (Member entity : members) {
            if (entity.getDocument().getType().equals(document.getType())
                    && entity.getDocument().getValue().equals(document.getValue())) {
                return entity;
            }
        }
        return null;
    }

    public List<Member> findByName(String name) {
        List<Member> membersFindedByName = new ArrayList<>();
        for (Member entity : members) {
            if (entity.getName().toUpperCase().contains(name.toUpperCase())) {
                membersFindedByName.add(entity);
            }
        }
        return membersFindedByName;
    }

    public Member findByCardNumber(String cardNumber) {
        for (Member entity : members) {
            if (entity.getCardNumber().equalsIgnoreCase(cardNumber)) {
                return entity;
            }
        }
        return null;
    }

    public boolean updateMemberByCardNumber(String cardNumber, Member member) {
        int count = 0;
        boolean hasMember = false;
        for (Member entity : members) {
            if (entity.getCardNumber().equals(cardNumber)) {
                members.set(count, member);
                hasMember = true;
            }
            count++;
        }
        if (hasMember == false) {
            return hasMember;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH))) {

            for (Member entity : members) {
                writer.write(gson.toJson(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        return true;
    }

    public boolean deleteMemberByCardNumber(String cardNumber) {
        Member entity = new Member();
        boolean hasMember = false;
        for (Member member : members) {
            if (member.getCardNumber().equals(cardNumber)) {
                entity = member;
                hasMember = true;
            }
        }
        if (hasMember == true) {
            members.remove(entity);
        } else {
            return hasMember;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH))) {

            for (Member member : members) {
                writer.write(gson.toJson(member));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        return hasMember;
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
