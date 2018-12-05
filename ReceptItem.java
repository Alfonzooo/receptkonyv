package com.example.pozsikmt.receptkonyv_v2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import org.json.JSONArray;

import java.util.ArrayList;

@Entity(tableName = "receptitem")
public class ReceptItem {
    public enum Category {
        APPETIZER, MAIN, DESSERT;

        @TypeConverter
        public static Category getByOrdinal(int ordinal) {
            Category ret = null;
            for (Category cat : Category.values()) {
                if (cat.ordinal() == ordinal) {
                    ret = cat;
                    break;
                }
            }
            return ret;
        }

        @TypeConverter
        public static int toInt(Category category) {
            return category.ordinal();
        }
    }


    @ColumnInfo(name = "rec_id")
    @PrimaryKey(autoGenerate = true)
    public Long rec_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    /*@ColumnInfo(name= "ingredient")
    public ArrayList<String> ingredients;*/


    /*@ColumnInfo(name = "ingredient")
    public JSONArray ingredientJSONArray;*/

    @ColumnInfo(name = "category")
    public Category category;

    @ColumnInfo(name = "is_favourite")
    public boolean isFavourite;
}