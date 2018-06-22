package edu.cnm.deepdive.simpleroomapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface NoteDao {

  @Insert
  long insert(Note note);

  @Query("SELECT * FROM Note ORDER BY timestamp DESC")
  List<Note> select();

}
