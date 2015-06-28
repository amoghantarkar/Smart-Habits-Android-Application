package smarthabitsnew.aditya.example.com.smarthabitsnew;

/**
 * Created by aditya on 4/14/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "locationTable";
    public static final String COLUMN_ID = "date";
    public static final String COLUMN_COMMENT = "location";
    public static final String COLUMN_DATE = "day";

    public static final String TABLE_DATE = "dateTable";
    public static final String LONG_DATE = "longDate";
    public static final String C_DATE = "realDate";



    public static final String TABLE_NAME_CALENDAR = "calendarTable";
    public static final String TABLE_COLUMN_DATE = "date";
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_DESCRIPTION = "description";
    public static final String TABLE_COLUMN_HOURS = "time";
    public static final String TABLE_COLUMN_DIFFICULTY = "level";

    public static final String TABLE_NAME_ACTIVITY = "activityTable";
    public static final String TABLE_COLUMN_LOCATION = "location";
    public static final String TABLE_COLUMN_ACTIVITY = "activity";
    public static final String TABLE_COLUMN_HOUR = "hours";
    public static final String TABLE_COLUMN_DAY = "day";





    private static final String DATABASE_NAME = "smarthabits.db";
    public static final int  DATABASE_VERSION = 1;

    // Database creation sql statement

    public static final String DATABASE_CREATE_CALENDAR = "create table "
            + TABLE_NAME_CALENDAR + "(" + TABLE_COLUMN_DATE
            + " long, " + TABLE_COLUMN_NAME
            + " text, "+ TABLE_COLUMN_DESCRIPTION+" text, "+ TABLE_COLUMN_HOURS+" text, " +
            TABLE_COLUMN_DIFFICULTY+" text);";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID
            + " long, " + COLUMN_COMMENT
            + " text, "+ COLUMN_DATE+" text);";

    private static final String DATABASE_CREATE_DATE = "create table "
            + TABLE_DATE + "(" + LONG_DATE
            + " long, " + C_DATE+" text);";

    private static final String DATABASE_CREATE_ACTIVITY = "create table "
            + TABLE_NAME_ACTIVITY + "(" + TABLE_COLUMN_LOCATION
            + " text, " + TABLE_COLUMN_ACTIVITY
            + " text, "+ TABLE_COLUMN_HOUR+" text, " + TABLE_COLUMN_DAY +" long);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        /*database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE_CALENDAR);
        database.execSQL(DATABASE_CREATE_ACTIVITY);
       */
        database.execSQL(DATABASE_CREATE_DATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
      //  DATABASE_VERSION=newVersion;
       /* db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CALENDAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACTIVITY);
      */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
        onCreate(db);
    }
}

