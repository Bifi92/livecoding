package tcc_zanza.no_ip.biz.thirtyminutes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;

/**
 * Created by VINICIUS on 12/28/2015.
 */
public class AccountDAO extends SQLiteOpenHelper {

    private  static final String DATABASE = "Accounts";
    private  static final int VERSION = 1;
    private  static final String TABLE = "AccountData";

    public AccountDAO (Context ctx){
        super(ctx, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE+" ( "+
                "ID INTEGER PRIMARY KEY, "+
                "USERNAME TEXT, "+
                "PASSWORD TEXT, "+
                "SITE TEXT"+
                ");";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS "+TABLE+";";

        db.execSQL(sql);

        onCreate(db);
    }

    public void insert(AccountData ad){
        ContentValues cv = new ContentValues();

        cv.put("USERNAME", ad.getUserName());
        cv.put("PASSWORD", ad.getPassword());
        cv.put("SITE", ad.getSite());

        getWritableDatabase().insert(TABLE, null, cv);

    }

    public List<AccountData> getList(){

        ArrayList<AccountData> accountDatas = new ArrayList<>();

        String sql = "SELECT * FROM "+TABLE+" ;";

        Cursor c = getWritableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            AccountData ad = new AccountData();

            ad.setId(c.getLong(c.getColumnIndex("ID")));
            ad.setUserName(c.getString(c.getColumnIndex("USERNAME")));
            ad.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
            ad.setSite(c.getString(c.getColumnIndex("SITE")));

            accountDatas.add(ad);
        }

        return accountDatas;

    }

    public void delete(AccountData accountData){

        String args[] = {accountData.getId().toString()};

        getWritableDatabase().delete(TABLE, "ID = ?", args);

    }

    public void deleteAll(){

        getWritableDatabase().delete(TABLE, null, null);

    }

    public void update (AccountData accountData){

        ContentValues cv = new ContentValues();

        String args[] = {accountData.getId().toString()};

        cv.put("USERNAME", accountData.getUserName());
        cv.put("PASSWORD", accountData.getPassword());
        cv.put("SITE", accountData.getSite());

        getWritableDatabase().update(TABLE, cv, "ID = ?", args);

    }

}
