package com.videotutoriales.avisos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AvisosDBAdapter {

//    Nombres de columnas
    public static final String COL_ID ="_id";
    public static final String COL_CONTENT="content";
    public static final String COL_IMPORTANT="important";

//    Indices correspondientes
    public static final int INDEX_ID =0;
    public static final int  INDEX_CONTENT= INDEX_ID+1;
    public static final int  INDEX_IMPORTANT= INDEX_ID+2;


//    For loginging

    public static final String TAG= "AvisosDbAdapter";


    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public static final String DATABASE_NAME = "dba_remdrs";
    public static final String  TABLE_NAME = "tbl_remdrs";
    public static final int DATABASE_VERSION = 1;


    private final Context mCtx;

//    Declaración  SQL usada para crear la base de datos

    public static final String DATABASE_CREATE =
            "CREATE TABLE if not exist "+TABLE_NAME +"("+COL_ID+"INTEGER PRIMARY KEY autoincrement,"
            + COL_CONTENT +"TEXT, "+COL_IMPORTANT +"INTEGER );";


    public AvisosDBAdapter(DatabaseHelper dbHelper, Context ctx){
        mDbHelper = dbHelper;
        this.mCtx = ctx;

    }

    //    abrir

    public void open() throws SQLException{
        mDbHelper =new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }


    //    cerrar
    public void close(){
        if (mDbHelper !=null){
            mDbHelper.close();

        }

    }


//    CREATE , la id se creará automaticamente

    public void createReminder(String name, boolean important){
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT,name);
        values.put(COL_IMPORTANT, important ? 1:0);
        mDb.insert(TABLE_NAME,null,values);
            }

    //Sobrecargado para tomar un Aviso

    public long createReminder(Aviso reminder){
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, reminder.getContent()); //Nombre Contacto
        values.put(COL_IMPORTANT, reminder.getImportant()); // Numero telefonico

        return mDb.insert(TABLE_NAME,null,values);

    }

//    READ

   public Aviso fetchReminderById(int id){
       Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID,COL_CONTENT,COL_IMPORTANT},COL_ID+"=?",
               new String[]{String.valueOf(id)},null,null,null,null);
       if (cursor != null)
            cursor.moveToFirst();

       return new Aviso(
                cursor.getInt(INDEX_ID),
               cursor.getString(INDEX_CONTENT),
               cursor.getInt(INDEX_IMPORTANT)

       );


   }


    public Cursor  fetchAllReminders(){

        Cursor mCursor = mDb.query(TABLE_NAME, new String[]{COL_ID,COL_CONTENT, COL_IMPORTANT},
                null,null,null,null,null);

        if (mCursor != null){
            mCursor.moveToFirst();

        }
        return mCursor;
    }


//    UPDATE

    public void updateReminder (Aviso reminder){

        ContentValues values = new ContentValues();
        values.put(COL_CONTENT,reminder.getContent());
        values.put(COL_IMPORTANT, reminder.getImportant());
        mDb.update(TABLE_NAME,values,COL_ID+"=?",new String[]{String.valueOf(reminder.getId())});
    }

//    DELETE

    public void deleteREminderById(int nID){
        mDb.delete(TABLE_NAME,COL_ID+"?=",new String[]{String.valueOf(nID)});

    }

    public void deleteAllReminders(){
        mDb.delete(TABLE_NAME,null,null);
    }





private static class DatabaseHelper extends SQLiteOpenHelper{ // metodo de respuesta especiales o call back

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.w(TAG, DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TAG, "Upgrading data base from version"+oldVersion+"to"+newVersion+", which will destroy" +
                "all old data");
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);

    }

}









}
