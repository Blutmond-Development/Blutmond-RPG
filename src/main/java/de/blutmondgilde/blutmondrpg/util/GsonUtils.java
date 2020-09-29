package de.blutmondgilde.blutmondrpg.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class GsonUtils {
    /**
     * Loads a json file into the given Class Template
     * @param path {@link Path} of the json file
     * @param type {@link Class} to load the information in
     * @param <A>  Gson Class
     * @return an {@link Object} with the loaded information
     */
    public static <A> Optional<A> load(Path path, Class<A> type) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(path);
            A a = gson.fromJson(reader, type);
            return Optional.of(a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Writes a json file using the information from the given {@link Object}
     * @param obj  {@link Object} containing the information
     * @param path {@link Path} to save the json file
     */
    public static void write(Object obj, Path path) {
        if (!path.toFile().getParentFile().exists()) {
            path.toFile().getParentFile().mkdirs();
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(path);
            gson.toJson(obj, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
