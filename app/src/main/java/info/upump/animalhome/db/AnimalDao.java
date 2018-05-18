package info.upump.animalhome.db;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import info.upump.animalhome.entity.Animal;

/**
 * Created by explo on 18.05.2018.
 */

public class AnimalDao extends DBDao {
    public AnimalDao(Context context) {
        super(context);
    }
    private final String[] keys = new String[]{
            DBHelper.TABLE_KEY_ID,
            DBHelper.TABLE_KEY_NAME,
            DBHelper.TABLE_KEY_SOUND_ANIMAL,
            DBHelper.TABLE_KEY_SOUND_AUTHOR,
            DBHelper.TABLE_KEY_IMAGE,
            DBHelper.TABLE_KEY_IMAGE_PREV,
            DBHelper.TABLE_KEY_QUESTION,
            DBHelper.TABLE_KEY_WORD};

    private Animal getSetsFromCursor(Cursor cursor) {
        Animal animal = new Animal();
        animal.setId(cursor.getInt(0));
        animal.setName(cursor.getString(1));
        animal.setSoundAnimal(cursor.getString(2)+".mp3");
        animal.setSoundAuthor(cursor.getString(3)+".mp3");
        animal.setImage(cursor.getString(4));
        animal.setImagePrev(cursor.getString(5));
        animal.setQuestion(cursor.getString(6)+".mp3");
        animal.setWord(cursor.getString(7));
        return animal;
    }


    public List<Animal> getAll() {
        Cursor cursor = null;
        List<Animal> setsList = new ArrayList<>();
        try {
            cursor = sqLiteDatabase.query(DBHelper.TABLE_ANIMAL,
                    keys, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Animal sets = getSetsFromCursor(cursor);
                    setsList.add(sets);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return setsList;
    }

    public List<Animal> randomFour(){
        Cursor cursor = null;
        List<Animal> setsList = new ArrayList<>();
        try {
            cursor = sqLiteDatabase.query(DBHelper.TABLE_ANIMAL,
                    keys, null, null, null, null, " random() limit 4");
            if (cursor.moveToFirst()) {
                do {
                    Animal sets = getSetsFromCursor(cursor);
                    setsList.add(sets);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return setsList;
    }
}
