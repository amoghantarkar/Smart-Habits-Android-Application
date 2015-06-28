package smarthabitsnew.aditya.example.com.smarthabitsnew;

/**
 * Created by Shreyas on 4/11/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CommentsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT,MySQLiteHelper.COLUMN_DATE};

    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
       // MySQLiteHelper.DATABASE_VERSION=1;
       // dbHelper.onUpgrade(database,MySQLiteHelper.DATABASE_VERSION,3);

    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String comment,String tableName) {
        long insertId=0;
        if(tableName.equals("locationTable")) {
            String day = comment.split("\\|")[0];
            String location = comment.split("\\|")[1];
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.COLUMN_COMMENT, location);
            values.put(MySQLiteHelper.COLUMN_DATE, day);
            insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);

        }

        else if(tableName.equals("calendarTable")){
          String[] str = comment.split("\\|");
          ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.TABLE_COLUMN_DATE, Long.parseLong(str[4]));
            values.put(MySQLiteHelper.TABLE_COLUMN_DESCRIPTION, str[1]);
            values.put(MySQLiteHelper.TABLE_COLUMN_NAME, str[0]);
            values.put(MySQLiteHelper.TABLE_COLUMN_DIFFICULTY, str[2]);
            values.put(MySQLiteHelper.TABLE_COLUMN_HOURS, str[3]);
            insertId = database.insert(MySQLiteHelper.TABLE_NAME_CALENDAR, null, values);


        }

        else if(tableName.equals("dateTable")){
            String[] str = comment.split("\\|");
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.LONG_DATE, Long.parseLong(str[0]));
            values.put(MySQLiteHelper.C_DATE, str[1]);
            insertId = database.insert(MySQLiteHelper.TABLE_DATE, null, values);


        }

        else if(tableName.equals("activityTable")){
            String[] str = comment.split("\\|");
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.TABLE_COLUMN_ACTIVITY, str[1]);
            values.put(MySQLiteHelper.TABLE_COLUMN_LOCATION, str[0]);
            values.put(MySQLiteHelper.TABLE_COLUMN_HOUR, str[2]);
            values.put(MySQLiteHelper.TABLE_COLUMN_DAY, Long.parseLong(str[3]));
            if(!getAllComments(str[1],"getActivityName").isEmpty()){
                String temp = MySQLiteHelper.TABLE_COLUMN_ACTIVITY+"='"+str[1]+"'";
                insertId = database.update(MySQLiteHelper.TABLE_NAME_ACTIVITY,values,temp,null) ;
            }
            else {
                insertId = database.insert(MySQLiteHelper.TABLE_NAME_ACTIVITY, null, values);
            }

        }

        return insertId;

       }

   /* public Comment query(String comment,String tableName){

            Cursor cursor = database.rawQuery("Select * from " + MySQLiteHelper.TABLE_COMMENTS, null);
            cursor.moveToFirst();
            Comment newComment = cursorToComment(cursor);
            cursor.close();
            return newComment;




    }
*/
    public void deleteComment() {
        database.delete(MySQLiteHelper.TABLE_NAME_CALENDAR,null,null);
       database.delete(MySQLiteHelper.TABLE_NAME_ACTIVITY,null,null);
       database.delete(MySQLiteHelper.TABLE_COMMENTS,null,null);
        database.delete(MySQLiteHelper.TABLE_DATE,null,null);
    }

    public List<Comment>getAllComments(String key,String tableName) {
        Long date=0l;
        if(!tableName.equals("getActivityName")) {
            date = Long.parseLong(key);
        }
        List<Comment> comments = new ArrayList<Comment>();
        Cursor cursor=null;
      /*  Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null,null, null, null, null, null);
*/


        if(tableName.equals("locationTable")) {
            String q = "Select * from " + MySQLiteHelper.TABLE_COMMENTS + " where " + MySQLiteHelper
                    .COLUMN_DATE + "=" + key;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"locationTable");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }

        else if(tableName.equals("calendarTable")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_CALENDAR + " where " + MySQLiteHelper
                    .TABLE_COLUMN_DATE + "=" + date;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"calendarTable");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }

        else if(tableName.equals("dateTable")){
            String q = "Select * from " + MySQLiteHelper.TABLE_DATE;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"dateTable");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }

        else if(tableName.equals("smartTipsCalendar")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_CALENDAR;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"smartTipsCalendar");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }



        else if(tableName.equals("getActivity")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_CALENDAR + " where " + MySQLiteHelper
                    .TABLE_COLUMN_DATE + ">=" + date;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"getActivity");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }



        else if(tableName.equals("activityTable")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_ACTIVITY + " where " + MySQLiteHelper
                    .TABLE_COLUMN_DAY + "=" + date;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"activityTable");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }

        else if(tableName.equals("smartTips")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_ACTIVITY;
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"smartTips");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }

        else if(tableName.equals("getActivityName")){
            String q = "Select * from " + MySQLiteHelper.TABLE_NAME_ACTIVITY + " where " + MySQLiteHelper
                    .TABLE_COLUMN_ACTIVITY + "='"+key+"'";
            cursor = database.rawQuery(q, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Comment comment = cursorToComment(cursor,"getActivityName");
                comments.add(comment);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();

        }





        return comments;
    }

    private Comment cursorToComment(Cursor cursor,String tableName) {

        Comment comment = new Comment();

        if(tableName.equals("locationTable")) {
            comment.setDay(cursor.getString(2));
            comment.setLocation(cursor.getString(1));
        }

       else if(tableName.equals("calendarTable")) {
            String day = cursor.getString(0);
            String name = cursor.getString(1);
            comment.setcTable_day(cursor.getLong(0));
            comment.setcTable_name(cursor.getString(1));
            comment.setcTable_desc(cursor.getString(2));
            comment.setcTable_diff(cursor.getString(3));
            comment.setcTable_hours(cursor.getString(4));
        }

        else if(tableName.equals("dateTable")) {

            comment.setdTable_day(cursor.getLong(0));
            comment.setdTable_date(cursor.getString(1));
        }

            else if(tableName.equals("activityTable")) {
            comment.setaTable_location(cursor.getString(0));
            comment.setaTable_activity(cursor.getString(1));
            comment.setaTable_hours(cursor.getString(2));
            comment.setaTable_date(cursor.getLong(3));
           }

        else if(tableName.equals("smartTipsCalendar")) {
            String day = cursor.getString(0);
            String name = cursor.getString(1);
            comment.setcTable_day(cursor.getLong(0));
            comment.setcTable_name(cursor.getString(1));
            comment.setcTable_desc(cursor.getString(2));
            comment.setcTable_diff(cursor.getString(3));
            comment.setcTable_hours(cursor.getString(4));
        }

        else if(tableName.equals("smartTips")) {
            comment.setaTable_location(cursor.getString(0));
            comment.setaTable_activity(cursor.getString(1));
            comment.setaTable_hours(cursor.getString(2));
            comment.setaTable_date(cursor.getLong(3));
        }

       else if(tableName.equals("getActivity")) {
           // comment.setaTable_location(cursor.getString(0));
            comment.setaTable_activity(cursor.getString(1));
            comment.setaTable_hours(cursor.getString(2));
           // comment.setaTable_date(cursor.getString(3));
        }

        else if(tableName.equals("getActivityName")) {
            // comment.setaTable_location(cursor.getString(0));
            comment.setaTable_activity(cursor.getString(1));
            comment.setaTable_hours(cursor.getString(2));
            // comment.setaTable_date(cursor.getString(3));
        }




        return comment;
    }
}
