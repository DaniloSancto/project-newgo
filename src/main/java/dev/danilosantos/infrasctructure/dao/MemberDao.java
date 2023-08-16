package dev.danilosantos.infrasctructure.dao;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    Gson gson = new Gson();

    public void insert(Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH, true))) {
            writer.write(gson.toJson(member));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<Member> readAllMembersFromDocument() {
        List<Member> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Routes.MEMBER_FILE_PATH))) {
            String line = reader.readLine();

            while (line != null) {
                Member member = gson.fromJson(line, Member.class);

                list.add(member);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public void updateDocument(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH))) {

            for (Member entity : members) {
                writer.write(gson.toJson(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
