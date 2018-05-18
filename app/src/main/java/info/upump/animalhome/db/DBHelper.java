package info.upump.animalhome.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public final static int DATA_BASE_VERSION = 1;
    public final static String DATABASE_NAME = "animal.db";
    public final static String TABLE_ANIMAL = "animal";

    public static final String TABLE_KEY_ID = "_id";
    public static final String TABLE_KEY_NAME = "name";
    public static final String TABLE_KEY_SOUND_ANIMAL = "sound_animal";
    public static final String TABLE_KEY_SOUND_AUTHOR = "sound_author";
    public static final String TABLE_KEY_IMAGE = "image";
    public static final String TABLE_KEY_IMAGE_PREV = "image_prev";
    public static final String TABLE_KEY_QUESTION = "question";
    public static final String TABLE_KEY_WORD = "word";



    private static final String CREATE_TABLE_ANIMAL =
            "CREATE TABLE " + TABLE_ANIMAL +
            "( " + TABLE_KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            TABLE_KEY_NAME + " TEXT NOT NULL, " +
            TABLE_KEY_SOUND_ANIMAL + " TEXT, " +
            TABLE_KEY_SOUND_AUTHOR + " TEXT, " +
            TABLE_KEY_IMAGE + " TEXT, " +
            TABLE_KEY_IMAGE_PREV + " TEXT, " +
            TABLE_KEY_QUESTION + " TEXT, "+
            TABLE_KEY_WORD + " TEXT )";

    private static DBHelper instance;

    private static final String DB_PATH = "data/data/info.upump.animalhome/databases/" + DATABASE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ANIMAL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }
    public void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        if (checkBD()) {
            return;
        }

        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                //получаем локальную бд как поток в папке assets
                this.getReadableDatabase();
                myInput = context.getAssets().open(DATABASE_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;
                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);
                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
                close();

            }
            seVersionDB();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBD() {
        File file = new File(DB_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    private void seVersionDB() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.setVersion(DATA_BASE_VERSION);
            sqLiteDatabase.close();
        } catch (SQLiteException e) {
        }
    }

    private boolean checkBD() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            int version = sqLiteDatabase.getVersion();
            sqLiteDatabase.close();
            if (version < DATA_BASE_VERSION) {
                deleteBD();
                return false;
            } else return true;
        } catch (SQLiteException e) {
            return false;
        }
    }
}
