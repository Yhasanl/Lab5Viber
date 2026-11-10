package com.example.lab5viber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allNotes = new ArrayList<>();
        adapter = new NoteAdapter(allNotes, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NoteEditorActivity.class)));

        Button btnSync = findViewById(R.id.btnSync);
        btnSync.setOnClickListener(v -> syncPrimaryToSecondary());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        allNotes.clear();
        allNotes.addAll(dbHelper.getAllNotes());
        allNotes.addAll(FileStorageHelper.getAllNotes(this));
        adapter.notifyDataSetChanged();
    }

    public void deleteNote(Note note) {
        if (note.getStorageType().equals("sqlite")) {
            dbHelper.deleteNote(note.getId());
        } else {
            List<Note> fileNotes = FileStorageHelper.getAllNotes(this);
            fileNotes.removeIf(n -> n.getId() == note.getId());
            FileStorageHelper.saveNotes(this, fileNotes);
        }
        Toast.makeText(this, getString(R.string.msg_deleted), Toast.LENGTH_SHORT).show();
        loadNotes();
    }

    private void syncPrimaryToSecondary() {
        List<Note> sqliteNotes = dbHelper.getAllNotes();
        FileStorageHelper.saveNotes(this, sqliteNotes);
        Toast.makeText(this, getString(R.string.msg_sync_complete), Toast.LENGTH_SHORT).show();
        loadNotes();
    }
    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}