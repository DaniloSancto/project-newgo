package dev.danilosantos.infrasctructure.dao;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SpaceDao {
    Gson gson = new Gson();

    public void insert(Space space) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.SPACE_FILE_PATH, true))) {
            writer.write(gson.toJson(space));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
