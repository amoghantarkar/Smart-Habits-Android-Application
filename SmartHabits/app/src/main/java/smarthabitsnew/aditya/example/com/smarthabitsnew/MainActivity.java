package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


public class MainActivity extends Activity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    private CommentsDataSource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // datasource = new CommentsDataSource(this);
       // datasource.open();
       // datasource.deleteComment();

        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);
        button4 = (Button) findViewById(R.id.stats);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
             startActivity(intent);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
             startActivity(intent);
            }
        });

        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityGraph.class);
                startActivity(intent);
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        /*else if(id == R.id.action_about){
           Intent intent = new Intent(getApplicationContext(),ActivityGraph.class);
           startActivity(intent);


        }*/

        else if(id == R.id.action_exit){
            Intent intent = new Intent(getApplicationContext(),ActivityAbout.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }


   /* private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
            ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
            Comment comment = null;
            try {
                List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
                if (addresses.size() > 0) {
//                    Toast.makeText(MainActivity.this,"location is : " + addresses.get(0).getAddressLine(0)
//                            +","  + addresses.get(0).getAddressLine(1)+","+addresses.get(0).getAddressLine(2),Toast.LENGTH_SHORT).show();
                    datasource.insert(addresses.get(0).getAddressLine(0));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //Toast.makeText(MainActivity.this, provider + "s status changed to",
            //Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderEnabled(String provider) {
            //Toast.makeText(MainActivity.this, "Provider " + provider + " enabled!",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            //Toast.makeText(MainActivity.this, "Provider " + provider + " disabled!",Toast.LENGTH_SHORT).show();

        }

    }


    private class ClientTask extends AsyncTask<Void, Integer, Void> {
        private ListActivity activity;

        public ClientTask(ListActivity activity){
            this.activity=activity;
        }

        @Override
        protected Void doInBackground(Void... a) {
            int i=0;
            myLocationListener = new MyLocationListener();
            while(true){
                i++;
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                criteria.setCostAllowed(false);
                provider = locationManager.getBestProvider(criteria, false);
                Location loc = getLastKnownLocation();
                if (loc != null) {
                    myLocationListener.onLocationChanged(loc);

                } else {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity.getBaseContext(), "Location Unknown", Toast.LENGTH_LONG).show();
                        }
                    });
                    *//*Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);*//*

                }
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        locationManager.requestLocationUpdates(provider, 5000, 100, myLocationListener);
                    }
                });

                publishProgress(0);

                return null;


            }

        }

        protected void onProgressUpdate(Integer... local) {


            List<Comment> values = datasource.getAllComments();
            Toast.makeText(MainActivity.this,"location is : " + values.get(0).toString(),Toast.LENGTH_SHORT).show();
            final ContentResolver mContentResolver = getContentResolver();
            final ContentValues mContentValues = new ContentValues();
            ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, values){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    //view.setBackgroundColor(Color.BLACK);
                    text.setTextColor(Color.BLACK);

                    return view;
                }
            };
            setListAdapter(adapter);


            }*/

   // }
}
