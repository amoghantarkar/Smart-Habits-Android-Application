package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import smarthabitsnew.aditya.example.com.smarthabitsnew.R;

public class MainActivity3 extends Activity {
     int y,m,d;
     String eText1,eText2,eText3,eText4;
     private CommentsDataSource datasource;
     String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        datasource = new CommentsDataSource(this);
        datasource.open();

        Button button = (Button) findViewById(R.id.button5);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String day = ""+month+dayOfMonth+year;
                date = Long.parseLong(day)+"|"+month+"-"+dayOfMonth+"-"+year;
                datasource.insert(date,"dateTable");
                y=year;
                m=month;
                d=dayOfMonth;

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eText1 = et1.getText().toString();
                eText2 = et2.getText().toString();
                eText3 = et3.getText().toString();
                eText4 = et4.getText().toString();
                String day = ""+m+d+y;
                MainActivity1.hashMap.put(Long.parseLong(day),m+"-"+d+"-"+y);
                String str = eText1+"|"+eText2+"|"+eText3+"|"+eText4+"|"+day;
                datasource.insert(str,"calendarTable");
                Toast.makeText(MainActivity3.this, "success",
                        Toast.LENGTH_SHORT).show();




            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
