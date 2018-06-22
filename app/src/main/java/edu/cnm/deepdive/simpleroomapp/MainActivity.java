package edu.cnm.deepdive.simpleroomapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import edu.cnm.deepdive.simpleroomapp.model.Note;
import edu.cnm.deepdive.simpleroomapp.model.NoteDatabase;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private Button newNote;
  private EditText noteText;
  private ListView notesList;
  private NoteDatabase database;
  private ArrayAdapter<Note> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    newNote = findViewById(R.id.new_note_button);
    noteText = findViewById(R.id.new_note_text);
    notesList = findViewById(R.id.notes_list);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    notesList.setAdapter(adapter);
    newNote.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Note note = new Note();
        note.setNote(noteText.getText().toString());
        note.setTimestamp(new Date());
        new NoteInsert().execute(note);
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    database = NoteDatabase.getInstance(this);
    refreshList();
  }

  @Override
  protected void onStop() {
    if (database != null) {
      database.forgetInstance(this);
      database = null;
    }
    super.onStop();
  }

  private void refreshList() {
    new NotesQuery().execute();
  }

  private class NotesQuery extends AsyncTask<Void, Void, List<Note>> {

    @Override
    protected List<Note> doInBackground(Void... voids) {
      return NoteDatabase.getInstance(MainActivity.this).getNoteDao().select();
    }

    @Override
    protected void onPostExecute(List<Note> notes) {
      adapter.clear();
      adapter.addAll(notes);
    }

  }

  private class NoteInsert extends AsyncTask<Note, Void, Long> {

    @Override
    protected Long doInBackground(Note... notes) {
      return NoteDatabase.getInstance(MainActivity.this).getNoteDao().insert(notes[0]);
    }

    @Override
    protected void onPostExecute(Long aLong) {
      refreshList();
    }

  }

}

