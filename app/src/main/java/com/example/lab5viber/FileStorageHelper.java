package com.example.lab5viber;

import android.content.Context;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileStorageHelper {
    private static final String FILE_NAME = "notes_secondary.txt";
    private static final String DELIMITER = "|||";

    private static String encodeBase64(String text) {
        return Base64.encodeToString(text.getBytes(), Base64.NO_WRAP);
    }

    private static String decodeBase64(String base64) {
        return new String(Base64.decode(base64, Base64.NO_WRAP));
    }

    public static void saveNotes(Context context, List<Note> notes) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            for (Note note : notes) {
                String line = note.getId() + DELIMITER +
                        encodeBase64(note.getTitle()) + DELIMITER +
                        encodeBase64(note.getContent()) + "\n";
                fos.write(line.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendNote(Context context, Note note) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
            String line = note.getId() + DELIMITER +
                    encodeBase64(note.getTitle()) + DELIMITER +
                    encodeBase64(note.getContent()) + "\n";
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
                    notes.add(new Note(Long.parseLong(parts[0]),
                            decodeBase64(parts[1]),
                            decodeBase64(parts[2]), "file"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }
}