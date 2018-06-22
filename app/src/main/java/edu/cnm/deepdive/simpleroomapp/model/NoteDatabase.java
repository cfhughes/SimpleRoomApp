package edu.cnm.deepdive.simpleroomapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import java.util.Date;

@Database(entities = {Note.class}, version = 1, exportSchema = true)
@TypeConverters(Converters.class)
public abstract class NoteDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "notes_db";

  private static NoteDatabase instance = null;

  public static NoteDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(
          context.getApplicationContext(), NoteDatabase.class, DATABASE_NAME).build();
    }
    return instance;
  }

  public abstract NoteDao getNoteDao();

  public void forgetInstance(Context context) {
    instance = null;
  }

}

class Converters {

  @TypeConverter
  public static Date dateFromTimestamp(Long timestamp) {
    return (timestamp != null) ? new Date(timestamp) : null;
  }

  @TypeConverter
  public static Long timestampFromDate(Date date) {
    return (date != null) ? date.getTime() : null;
  }

}
