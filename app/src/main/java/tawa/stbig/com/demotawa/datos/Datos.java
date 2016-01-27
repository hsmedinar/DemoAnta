package tawa.stbig.com.demotawa.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import tawa.stbig.com.demotawa.R;
import tawa.stbig.com.demotawa.helper.DBitem;
import tawa.stbig.com.demotawa.object.ImageObject;

/**
 * Created by root on 23/11/15.
 */
public class Datos extends SQLiteOpenHelper {


    private SQLiteDatabase db;
    private Context context;

    private static final String CREATE_IMAGE = "CREATE TABLE " + DBitem.TABLE_IMG + " (" + DBitem.IMG_ID + " INTEGER," + DBitem.IMG_TITLE + " TEXT,"  + DBitem.IMG_DESCRIPTION + " TEXT," + DBitem.IMG_IMAGE + " TEXT," + DBitem.IMG_CREATE +  " TEXT," + DBitem.IMG_UPDATE  + " TEXT);";

    public Datos(Context context){
        super(context, context.getResources().getString(R.string.app_name), null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try {
            db.execSQL(CREATE_IMAGE);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("sql error",e.getMessage());
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerImage(int id, String title, String description, String image, String created_at, String updated_at) throws SQLException {

        try {
            db = getReadableDatabase();

            if(checkRegister(DBitem.TABLE_IMG,DBitem.IMG_ID,String.valueOf(id))==0){
                ContentValues values = new ContentValues();
                values.put(DBitem.IMG_ID, id);
                values.put(DBitem.IMG_TITLE, title);
                values.put(DBitem.IMG_DESCRIPTION, description);
                values.put(DBitem.IMG_IMAGE, image);
                values.put(DBitem.IMG_CREATE, created_at);
                values.put(DBitem.IMG_UPDATE, updated_at);
                db.insert(DBitem.TABLE_IMG, null, values);
            }else{
                String[] args = {String.valueOf(id)};
                ContentValues values = new ContentValues();
                values.put(DBitem.IMG_TITLE, title);
                values.put(DBitem.IMG_DESCRIPTION, description);
                values.put(DBitem.IMG_IMAGE, image);
                values.put(DBitem.IMG_CREATE, created_at);
                values.put(DBitem.IMG_UPDATE, updated_at);
                db.insert(DBitem.TABLE_IMG, null, values);
                db.update(DBitem.TABLE_IMG, values, DBitem.IMG_ID + "=?", args);
            }

            db.close();

        } catch (SQLException e) {

        }
    }

    public ArrayList<ImageObject> listarImages(){
        ArrayList<ImageObject> values = new ArrayList<ImageObject>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + DBitem.TABLE_IMG , null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                ImageObject data = new ImageObject(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                values.add(data);
            } while (c.moveToNext());
        }
        db.close();
        c.close();
        return values;
    }

    public int checkRegister(String table,String field,String data) {
        int total=0;
        String[] param = {data};
        Cursor c = db.rawQuery("select * from " + table + "  where " + field + "=?", param);
        total = c.getCount();
        c.close();
        return total;
    }

}
