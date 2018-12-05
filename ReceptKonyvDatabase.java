package com.example.pozsikmt.receptkonyv_v2.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

@Database(
        entities = {ReceptItem.class},
        version = 1
)
@TypeConverters(value = {ReceptItem.Category.class/*,ReceptItemDao.Converters.class*/})
public abstract class ReceptKonyvDatabase extends RoomDatabase {
    public abstract ReceptItemDao receptItemDao();
}
