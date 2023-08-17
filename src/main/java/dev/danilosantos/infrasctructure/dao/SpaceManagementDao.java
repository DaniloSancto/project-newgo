package dev.danilosantos.infrasctructure.dao;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SpaceManagementDao {
    Gson gson = new Gson();

    public void insert(SpaceManagementDao member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.MEMBER_FILE_PATH, true))) {
            writer.write(gson.toJson(member));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
