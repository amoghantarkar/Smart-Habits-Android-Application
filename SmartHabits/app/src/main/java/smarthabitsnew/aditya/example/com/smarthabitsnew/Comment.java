package smarthabitsnew.aditya.example.com.smarthabitsnew;

/**
 * Created by Shreyas on 4/11/2015.
 */
public class Comment {

    //private long  date;
    private String lTable_location;
    private String lTable_day;

    private long cTable_day;
    private String cTable_name;
    private String cTable_desc;
    private String cTable_diff;
    private String cTable_hours;

    private String aTable_location;
    private String aTable_activity;
    private String aTable_hours;
    private long aTable_date;
    private long dTable_day;
    private String dTable_date;

    public long getdTable_day() {
        return dTable_day;
    }

    public void setdTable_day(long dTable_day) {
        this.dTable_day = dTable_day;
    }

    public String getdTable_date() {
        return dTable_date;
    }

    public void setdTable_date(String dTable_date) {
        this.dTable_date = dTable_date;
    }



    public long getaTable_date() {
        return aTable_date;
    }

    public void setaTable_date(long aTable_date) {
        this.aTable_date = aTable_date;
    }

    public String getaTable_hours() {
        return aTable_hours;
    }

    public void setaTable_hours(String aTable_hours) {
        this.aTable_hours = aTable_hours;
    }

    public String getaTable_activity() {
        return aTable_activity;
    }

    public void setaTable_activity(String aTable_activity) {
        this.aTable_activity = aTable_activity;
    }

    public String getaTable_location() {
        return aTable_location;
    }

    public void setaTable_location(String aTable_location) {
        this.aTable_location = aTable_location;
    }

    public String getcTable_desc() {
        return cTable_desc;
    }

    public void setcTable_desc(String cTable_desc) {
        this.cTable_desc = cTable_desc;
    }

    public String getcTable_hours() {
        return cTable_hours;
    }

    public void setcTable_hours(String cTable_hours) {
        this.cTable_hours = cTable_hours;
    }

    public String getcTable_diff() {
        return cTable_diff;
    }

    public void setcTable_diff(String cTable_diff) {
        this.cTable_diff = cTable_diff;
    }

    public String getcTable_name() {
        return cTable_name;
    }

    public void setcTable_name(String cTable_name) {
        this.cTable_name = cTable_name;
    }

    public long getcTable_day() {
        return cTable_day;
    }

    public void setcTable_day(long cTable_day) {
        this.cTable_day = cTable_day;
    }


    public String getLocation() {
        return lTable_location;
    }

    public void setLocation(String location) {
        lTable_location = location;
    }

    public String getDay() {
        return lTable_day;
    }

    public void setDay(String day) {
        lTable_day = day;
    }




    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {

        return lTable_location;
    }
}