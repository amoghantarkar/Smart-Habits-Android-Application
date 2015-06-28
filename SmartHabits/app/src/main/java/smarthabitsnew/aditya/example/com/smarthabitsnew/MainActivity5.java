package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);
        TextView textView =(TextView) findViewById(R.id.textView11);
        //TextView textView1 =(TextView) findViewById(R.id.textView9);
        TextView textView2 =(TextView) findViewById(R.id.textView12);
        TextView textView3 =(TextView) findViewById(R.id.textView8);
        TextView textView4 =(TextView) findViewById(R.id.textView14);
        final Bundle extras = getIntent().getExtras();
        textView.setText(extras.getString("name"));
       // textView1.setText(extras.getString("day"));
        textView2.setText(extras.getString("time"));
        textView3.setText(extras.getString("description"));
        textView4.setText(extras.getString("difficulty"));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity5, menu);
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
