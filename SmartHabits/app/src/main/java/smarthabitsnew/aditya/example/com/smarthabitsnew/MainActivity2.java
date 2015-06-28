package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity2 extends Activity implements AdapterView.OnItemSelectedListener {
    TextView textView;
    EditText editText;
    EditText editText1;
    Spinner spinner;
    String activity;
    private CommentsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        final Bundle extras = getIntent().getExtras();
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(extras.getString("value"));
        spinner = (Spinner)findViewById(R.id.spinner);
        Button button = (Button) findViewById(R.id.button);
        datasource = new CommentsDataSource(this);
        datasource.open();
        List<Comment> commentList = datasource.getAllComments(extras.getString("day"),"getActivity");
       // String[] activityList = new String[commentList.size()];
        List<String> activityList = new ArrayList<String>();
        activityList.add("choose activity");
        for(int i=0;i<commentList.size();i++){
            activityList.add(commentList.get(i).getaTable_activity());
        }
        activityList.add("Play");
        activityList.add("Gym");
        activityList.add("Coffee");
        activityList.add("Eat");
        activityList.add("Sleep");
        activityList.add("Other");
        editText = (EditText) findViewById(R.id.textView2);
        //editText1 = (EditText) findViewById(R.id.textView3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String s = spinner.getSelectedItem().toString();

            List<Comment> list = datasource.getAllComments(s,"getActivityName");
            String str =null;

            if(!list.isEmpty()){
              Double i = Double.parseDouble(list.get(0).getaTable_hours());
              i = i+Double.parseDouble(editText.getText().toString());
              str=extras.getString("value") +"|" + spinner.getSelectedItem().toString()
                        +"|"+i.toString()+"|"+extras.getString("day");
                /*Toast.makeText(MainActivity2.this,"first:"+i.toString()
                        ,Toast.LENGTH_LONG).show();*/
            }

            else {

                str = extras.getString("value") + "|" + spinner.getSelectedItem().toString()
                        + "|" + editText.getText().toString() + "|" + extras.getString("day");
                /*Toast.makeText(MainActivity2.this,"second:"+editText.getText().toString()
                        ,Toast.LENGTH_LONG).show();*/
            }
            datasource.insert(str,"activityTable");
            List<Comment> commentList = datasource.getAllComments(extras.getString("day"),"activityTable");
            String value = spinner.getSelectedItem().toString();


            if(value.equals("Other")||value.equals("Eat")||
                    value.equals("Coffee")){
                Calendar rightNow = Calendar.getInstance();
                int hour = rightNow.get(Calendar.HOUR_OF_DAY);

                Toast.makeText(MainActivity2.this,"Check your calendar " +
                        "and get back to study"
                        ,Toast.LENGTH_LONG).show();
            }

            else if(value.equals("Sleep")){
                Toast.makeText(MainActivity2.this,"Brush your teeth have a shower and check your calendar "
                        ,Toast.LENGTH_LONG).show();
                }

            else if(value.equals("Gym")||value.equals("Play")){
                Toast.makeText(MainActivity2.this,"Have a nice shower. Eat Something. Check your calendar" +
                        " and return to studies"
                        ,Toast.LENGTH_LONG).show();

            }

            else{
                Calendar rightNow = Calendar.getInstance();
                int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                if(hour<15 && hour>8){
                    Toast.makeText(MainActivity2.this,"Take a break. Enjoy a cup of coffee or eat something"
                            ,Toast.LENGTH_LONG).show();
                }

                else if(hour>15 && hour<22){
                    Toast.makeText(MainActivity2.this,"Take a break. Go to gym or a walk outside or watch some television "
                            ,Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(MainActivity2.this,"Its after 10 pm. Finish up and brush your teeth and go to bed "
                            ,Toast.LENGTH_LONG).show();
                }





            }



            }
        });


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        activity = parent.getAdapter().getItem(pos).toString();
        Toast.makeText(MainActivity2.this,activity,Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
