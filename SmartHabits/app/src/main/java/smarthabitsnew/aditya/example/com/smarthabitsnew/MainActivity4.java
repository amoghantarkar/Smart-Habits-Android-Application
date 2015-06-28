package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import smarthabitsnew.aditya.example.com.smarthabitsnew.R;

public class MainActivity4 extends Activity {
    public CommentsDataSource dataSource;
    CalendarView cv;
     List<Comment> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4);
        dataSource = new CommentsDataSource(this);
        dataSource.open();
        cv = (CalendarView) findViewById(R.id.calendarView1);
        final ListView listView = (ListView) findViewById(R.id.listView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String day = ""+month+dayOfMonth+year;
                values = dataSource.getAllComments(day,"calendarTable");

                String str[]=new String[values.size()];
                for(int i=0; i<str.length;i++){
                    str[i]=values.get(i).getcTable_name();
                }

               ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, str){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                listView.setAdapter(adapter);
            }
        });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MainActivity5.class);
                String str = parent.getAdapter().getItem(position).toString();
                intent.putExtra("day",values.get(position).getcTable_day());
                intent.putExtra("name",str);
                intent.putExtra("description",values.get(position).getcTable_desc().toString());
                intent.putExtra("difficulty",values.get(position).getcTable_diff().toString());
                intent.putExtra("time",values.get(position).getcTable_hours().toString());
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity4, menu);
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
