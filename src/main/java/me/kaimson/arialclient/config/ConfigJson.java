package me.kaimson.arialclient.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigJson {

    public final static ConfigJson INSTANCE = new ConfigJson();

    public JsonObject loadJsonFile(File file) {
        if (file.exists()) {
            JsonElement fileElement = new JsonParser().parse(getFileContents(file));

            if (fileElement == null || fileElement.isJsonNull())
                throw new JsonParseException("File \"" + file.getName() + "\" is null!");

            return fileElement.getAsJsonObject();
        }
        return null;
    }

    public String getFileContents(File file) {
        if (file.exists()) {
            try {
                @Cleanup BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder builder = new StringBuilder();

                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    builder.append(nextLine);
                }
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
