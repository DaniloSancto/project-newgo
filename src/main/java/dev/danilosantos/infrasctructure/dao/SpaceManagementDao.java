package dev.danilosantos.infrasctructure.dao;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Member;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SpaceManagementDao {
    private final Gson gson = new Gson();

    public void insert(SpaceManagement spaceManagement) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.SPACE_MANAGEMENT_FILE_PATH, true))) {
            writer.write(gson.toJson(spaceManagement));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
