package com.example.diarynote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTable1 = "CREATE TABLE if not exists mytable ("
                + "_id integer primary key autoincrement,"
                + "title text,"
                + "contents text,"
                + "date text);";

        String sqlTable2 = "CREATE TABLE if not exists login ("
                + "_id integer primary key autoincrement,"
                + "id text,"
                + "pwd text);";

        db.execSQL(sqlTable1);
        db.execSQL(sqlTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlTable1 = "DROP TABLE if exists mytable";
        String sqlTable2 = "DROP TABLE if exists login";

        db.execSQL(sqlTable1);
        db.execSQL(sqlTable2);

        onCreate(db);
    }

    public Cursor getUserInfo(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "pwd"}; // 조회할 컬럼 목록
        String selection = "id = ?"; // 조건: id가 일치하는 행을 조회
        String[] selectionArgs = {userId}; // id가 userId와 일치하는 경우
        Cursor cursor = db.query("login", columns, selection, selectionArgs, null, null, null);

        return cursor;
    }

    // 날짜에 맞는 레코드를 삭제하는 메서드
    public void deleteRecord(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("mytable", "date = ?", new String[]{date});
        db.close();
    }
}
