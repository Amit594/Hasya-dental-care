package com.example.haasyadentalcare.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("select * from patientData")
    List<PatientData> getAll();

    @Insert
    void insert(PatientData patientData);

    @Delete
    void delete(PatientData patientData);

    @Update
    void update(PatientData patientData);

}
