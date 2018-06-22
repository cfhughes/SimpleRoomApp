package edu.cnm.deepdive.simpleroomapp;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class NoteApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }

}
