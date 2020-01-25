package com.example.haasyadentalcare.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {PatientData.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
        public abstract PatientDao patientDao();

}
