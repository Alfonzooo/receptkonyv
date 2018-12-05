package com.example.pozsikmt.receptkonyv_v2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.Update;


//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReceptItemDao {
    @Query("SELECT * FROM receptitem")
    List<ReceptItem> getAll();

    @Query("SELECT * FROM receptitem WHERE is_favourite= 1")
    List<ReceptItem> getfav();

    @Insert
    long insert(ReceptItem receptItems);

    @Update
    void update(ReceptItem receptItem);

    @Delete
    void deleteItem(ReceptItem receptItem);


    /* class Converters {
        @TypeConverter
        public static ArrayList<String> fromString(String value) {
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }
        @TypeConverter
        public static String fromArrayLisr(ArrayList<String> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }*/

}



