package com.example.lab5viber;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private MainActivity activity;

    public NoteAdapter(List<Note> noteList, MainActivity activity) {
        this.noteList = noteList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(activity, NoteEditorActivity.class);
            intent.putExtra("ID", note.getId());
            intent.putExtra("TITLE", note.getTitle());
            intent.putExtra("CONTENT", note.getContent());
            intent.putExtra("STORAGE", note.getStorageType());
            activity.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> activity.deleteNote(note));
    }

    @Override
    public int getItemCount() { return noteList.size(); }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;
        Button btnEdit, btnDelete;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}