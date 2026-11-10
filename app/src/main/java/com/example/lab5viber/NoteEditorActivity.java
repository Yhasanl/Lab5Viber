package com.example.lab5viber;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class NoteEditorActivity extends AppCompatActivity {
    private EditText etTitle, etContent;
    private RadioButton rbSqlite;
    private DatabaseHelper dbHelper;
    private long noteId = -1;
    private String existingStorage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        dbHelper = new DatabaseHelper(this);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        rbSqlite = findViewById(R.id.rbSqlite);
        Button btnSave = findViewById(R.id.btnSave);

        if (getIntent().hasExtra("ID")) {
            noteId = getIntent().getLongExtra("ID", -1);
            etTitle.setText(getIntent().getStringExtra("TITLE"));
            etContent.setText(getIntent().getStringExtra("CONTENT"));
            existingStorage = getIntent().getStringExtra("STORAGE");
        }

        btnSave.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        boolean useSqlite = rbSqlite.isChecked();

        if (noteId == -1) {
            if (useSqlite) {
                dbHelper.insertNote(title, content);
            } else {
                Note newNote = new Note(System.currentTimeMillis(), title, content, "file");
                FileStorageHelper.appendNote(this, newNote);
            }
        } else {
            if (existingStorage.equals("sqlite")) {
                dbHelper.updateNote(noteId, title, content);
            } else {
                List<Note> fileNotes = FileStorageHelper.getAllNotes(this);
                fileNotes.removeIf(n -> n.getId() == noteId);
                fileNotes.add(new Note(noteId, title, content, "file"));
                FileStorageHelper.saveNotes(this, fileNotes);
            }
        }

        Toast.makeText(this, getString(R.string.msg_saved), Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}