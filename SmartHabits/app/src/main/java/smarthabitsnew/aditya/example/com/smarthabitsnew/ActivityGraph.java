package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.HashMap;

public class ActivityGraph extends Activity {
    Button dailyText;
    Button weeklyStats;
    Button smartTips;
    Button markovButton;
    CalendarView cv;
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_graph);
        dailyText = (Button) findViewById(R.id.dailyStats);
       // weeklyStats = (Button) findViewById(R.id.weeklyStats);
        smartTips = (Button) findViewById(R.id.smartButton);
        markovButton = (Button) findViewById(R.id.markovButton);
        cv = (CalendarView) findViewById(R.id.calendarView2);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                day = ""+month+dayOfMonth+year;
                MainActivity1.hashMap.put(Long.parseLong(day),month+"-"+dayOfMonth+"-"+year);

            }

        });

        dailyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DailyStats.class);
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });


      /* weeklyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WeeklyStats.class);
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });
*/
       smartTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SmartTips.class);
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });

       markovButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), MarKovChain.class);
               intent.putExtra("day",day);
               startActivity(intent);
           }
       });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_graph, menu);
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
