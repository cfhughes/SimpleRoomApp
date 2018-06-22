package edu.cnm.deepdive.simpleroomapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.simpleroomapp.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView noteText;
        public TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.note_text);
            dateText = itemView.findViewById(R.id.date_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.noteText.setText(notes.get(i).getNote());
        viewHolder.dateText.setText(notes.get(i).getTimestamp().toString());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
