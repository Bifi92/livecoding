package tcc_zanza.no_ip.biz.thirtyminutes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.PINCode;

/**
 * Created by VINICIUS on 1/23/2016.
 */
public class PINDAO extends SQLiteOpenHelper {

    private  static final String DATABASE = "Pins";
    private  static final int VERSION = 1;
    private  static final String TABLE = "PIN";

    public PINDAO (Context ctx){
        super(ctx, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE+" ( "+
                "ID INTEGER PRIMARY KEY, "+
                "PINCODE TEXT"+
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE+";";

        db.execSQL(sql);

        onCreate(db);
    }

    public void insert(PINCode pc){

        ContentValues cv = new ContentValues();

        cv.put("PINCODE", pc.getCode());

        getWritableDatabase().insert(TABLE, null, cv);

    }

    public void delete(PINCode pc){

        String args[] = {pc.getId().toString()};

        getWritableDatabase().delete(TABLE, "ID = ?", args);

    }

    public void deleteAll(){

        getWritableDatabase().delete(TABLE, null, null);

    }

    public void update (PINCode pc){

        ContentValues cv = new ContentValues();

        String args[] = {pc.getId().toString()};

        cv.put("PINCODE", pc.getCode());

        getWritableDatabase().update(TABLE, cv, "ID = ?", args);

    }

    public PINCode getKey(){

        String sql = "SELECT * FROM "+TABLE+";";

        Cursor c = getWritableDatabase().rawQuery(sql, null);

        PINCode pc = new PINCode();

        if (c.moveToNext()){
            pc.setId(c.getLong(c.getColumnIndex("ID")));
            pc.setCode(c.getString(c.getColumnIndex("PINCODE")));
        }

        return pc;

    }

}
