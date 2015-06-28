package smarthabitsnew.aditya.example.com.smarthabitsnew;


import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class MainActivity1 extends ListActivity {

    CalendarView cv;
    private LocationManager locationManager;
    private MyLocationListener myLocationListener;
    private String provider;
    public static final Uri uri = Uri.parse("content://com.amoghds1.smarthabits.provider");
    private String latitude;
    private String longitude;
    public static HashMap<Long,String> hashMap = new HashMap<>();
    private CommentsDataSource datasource;
    String day;
    String date;
    //  ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);
        datasource = new CommentsDataSource(this);
        datasource.open();
     //  String[] labels = {“Kit Kat”, “Jelly Bean”, “Ice Cream Sandwich”, “Honeycomb”, “Gingerbread”, “Older”};
        cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                day = ""+month+dayOfMonth+year;
                date = Long.parseLong(day)+"|"+month+"-"+dayOfMonth+"-"+year;
                datasource.insert(date,"dateTable");
                new ClientTask(MainActivity1.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

            }
        });



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        /*Toast.makeText(MainActivity1.this,"id"+l.getAdapter().getItem(position),
                Toast.LENGTH_SHORT).show();*/
        String str = l.getAdapter().getItem(position).toString();
        intent.putExtra("value",str);
        intent.putExtra("day",day);
        startActivity(intent);


    }




    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }


    //new DateTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
            //ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
            Comment comment = null;
            try {
                List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
                if (addresses.size() > 0) {
//                    Toast.makeText(MainActivity.this,"location is : " + addresses.get(0).getAddressLine(0)
//                            +","  + addresses.get(0).getAddressLine(1)+","+addresses.get(0).getAddressLine(2),Toast.LENGTH_SHORT).show();
                    String str= day+"|"+addresses.get(0).getAddressLine(0);
                    datasource.insert(str,"locationTable");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

           //sadapter.notifyDataSetChanged();
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
                    /*Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);*/

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
            List<Comment> values = datasource.getAllComments(day,"locationTable");
          //  Toast.makeText(MainActivity1.this,"location is : " + values.get(0).toString(),Toast.LENGTH_SHORT).show();
            final ContentResolver mContentResolver = getContentResolver();
            final ContentValues mContentValues = new ContentValues();
            String str[]=new String[values.size()];
            /*for(int i=0; i<str.length;i++){
                str =
            }*/
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


        }

    }
}
