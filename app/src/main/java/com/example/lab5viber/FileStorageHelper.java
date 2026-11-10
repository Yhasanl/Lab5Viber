package com.example.lab5viber;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileStorageHelper {
    private static final String FILE_NAME = "notes_secondary.txt";
    private static final String DELIMITER = "|||";

    public static void saveNotes(Context context, List<Note> notes) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            for (Note note : notes) {
                String line = note.getId() + DELIMITER + note.getTitle() + DELIMITER + note.getContent() + "\n";
                fos.write(line.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendNote(Context context, Note note) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
            String line = note.getId() + DELIMITER + note.getTitle() + DELIMITER + note.getContent() + "\n";
            fos.write(line.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Note> getAllNotes(Context context) {
        List<Note> notes = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|\\|\\|");
                if (parts.length == 3) {
                    notes.add(new Note(Long.parseLong(parts[0]), parts[1], parts[2], "file"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }
}