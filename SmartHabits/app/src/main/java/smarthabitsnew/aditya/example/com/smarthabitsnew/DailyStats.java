package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import smarthabitsnew.aditya.example.com.smarthabitsnew.R;

public class DailyStats extends Activity {
    CalendarView cv;
    private CommentsDataSource datasource;
    HashMap<String,Double> map = new HashMap<>();
    String previousActivity=null;
    Integer previousHour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stats);
        datasource = new CommentsDataSource(this);
        datasource.open();
      //  cv = (CalendarView) findViewById(R.id.dailyCalendarView);
           /*  Toast.makeText(DailyStats.this, "id"+cv.getFirstDayOfWeek(),
                        Toast.LENGTH_SHORT).show();
           */
                Bundle bundle = getIntent().getExtras();
                String day = bundle.getString("day");
                List<Comment> commentList = datasource.getAllComments(day,"activityTable");
                String[] labels = new String[commentList.size()];
                String[] labels1 = new String[commentList.size()];;
                double[] values = new double[commentList.size()];
                double[] values1 = new double[commentList.size()];
                int hours=0;
                double total = 0;





        for(int j=0;j<commentList.size();j++){
           String key = commentList.get(j).getaTable_activity();
           Double value = Double.parseDouble(commentList.get(j).getaTable_hours());
            if(map.get(key)==null){
                map.put(key,value);
            }
           else{
                Double temp = map.get(key)+value;
                map.put(key,temp);

            }


        }

        Set<String> keySet = map.keySet();
        Double total1 = 0.0;

        for(String s:keySet){
         total1=total1+map.get(s);

        }
        int count=0;
        for(String s:keySet){
            String key = s;
            Double value = (map.get(s)/total1)*100;
            labels[count] = key;
            values1[count] = value;
            count++;

        }



       /* for(int i=0;i<commentList.size();i++) {
            int flag =0;
            for(int j=i-1;j>=0;j--){
                String s = labels[j];
                String s1 = commentList.get(i).getaTable_activity();
                if(s.equals(s1)){
                    labels[i]="";
                    total = total +Double.parseDouble(commentList.get(i).getaTable_hours());
                    values1[i]=0;
                    values1[j] = values[j]+Double.parseDouble(commentList.get(i).getaTable_hours());
                    flag = 1;
                    break;
                }

            }

            if(flag==1){
                continue;
            }

            labels[i] = commentList.get(i).getaTable_activity();
            values1[i] = Double.parseDouble(commentList.get(i).getaTable_hours());
            total = total +values1[i];
        }

        for(int i=0;i<commentList.size();i++) {

            values1[i]=(values1[i]/total)*100;
        }*/


           /* for(int i=0;i<commentList.size();i++){
         //           labels[i] = commentList.get(i).getaTable_activity();
                    int a = Integer.parseInt(commentList.get(i).getaTable_hours());

                    if(previousActivity!=null){

                        if(labels[i].equals("Gym")||labels[i].equals("Play")) {
                           if(previousActivity.equals("Gym")||previousActivity.equals("Play")){
                               Double temp = map.get("R-R");
                               if(temp==null){
                                   temp = 1.0;
                               }
                               else{
                                   temp=temp+1;
                               }
                               map.put("R-R",temp);

                           }

                            else if(previousActivity.equals("Other")){
                               Double temp = map.get("R-O");
                               if(temp==null){
                                   temp = 1.0;
                               }
                               else{
                                   temp=temp+1;
                               }
                               map.put("R-O",temp);
                           }

                           else{
                               Double temp = map.get("R-S");
                               if(temp==null){
                                   temp = 1.0;
                               }
                               else{
                                   temp=temp+1;
                               }

                               map.put("R-S",temp);
                           }

                        }

                        else if(labels[i].equals("Other")){
                            if(previousActivity.equals("Gym")||previousActivity.equals("Play")){
                                Double temp = map.get("O-R");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }
                                map.put("O-R",temp);

                            }

                            else if(previousActivity.equals("Other")){
                                Double temp = map.get("O-O");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }
                                map.put("O-O",temp);
                            }

                            else{
                                Double temp = map.get("O-S");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }
                                map.put("O-S",temp);
                            }



                        }



                        else{

                            if(previousActivity.equals("Gym")||previousActivity.equals("Play")){
                                Double temp = map.get("S-R");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }

                                map.put("S-R",temp);

                            }

                            else if(previousActivity.equals("Other")){
                                Double temp = map.get("S-O");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }
                                map.put("S-O",temp);
                            }

                            else{
                                Double temp = map.get("S-S");
                                if(temp==null){
                                    temp = 1.0;
                                }
                                else{
                                    temp=temp+1;
                                }
                                map.put("S-S",temp);
                            }



                        }
        }

                    values[i] = a;
                    hours = hours + a;

                    previousActivity=commentList.get(i).getaTable_activity();
                    previousHour=Integer.parseInt(commentList.get(i).getaTable_hours());

                }

                Set<String> keys = map.keySet();
                double count=0;
                for(String s :keys){
                    count = count + map.get(s);
                }

                for(String s : keys){
                    double h = map.get(s);
                    h = h/count;
                    map.put(s,h);

                }



                for(int i=0;i<commentList.size();i++){
                    values[i]= values[i]/hours*100;


                }

                double[][] matrix = new double[3][3];

                if(map.get("S-S")==null){
                    matrix[0][0]=0.0;
                }
                else {
                    matrix[0][0] = map.get("S-S");
                }

                if(map.get("S-R")==null){
                    matrix[0][1]=0.0;
                }
                else {
                    matrix[0][1] = map.get("S-R");
                }
                if(map.get("S-O")==null){
                    matrix[0][2]=0.0;
                }
                else {
                    matrix[0][2] = map.get("S-O");
                }
                if(map.get("R-S")==null){
                    matrix[1][0]=0.0;
                }
                else {
                    matrix[1][0] = map.get("R-S");
                }

                if(map.get("R-R")==null){
                    matrix[1][1]=0.0;
                }

                else {
                    matrix[1][1] = map.get("R-R");
                }

                if(map.get("R-O")==null){
                    matrix[1][2]=0.0;
                }
                else {
                    matrix[1][2] = map.get("R-O");
                }

                if(map.get("O-S")==null){
                    matrix[2][0]=0.0;
                }
                else {
                    matrix[2][0] = map.get("O-S");
                }
                if(map.get("O-R")==null){
                    matrix[2][1]=0.0;
                }
                else {
                    matrix[2][1] = map.get("O-R");
                }
                if(map.get("O-O")==null){
                    matrix[2][2]=0.0;
                }
                else {
                    matrix[2][2] = map.get("O-O");
                }

                double[] p = {0.45,0.3,0.25};
                double[] fin = new double[3];
                for(int i=0;i<3;i++){
                    for(int j=0;j<matrix.length;j++){
                        fin[i]+=(p[i]*matrix[j][i]);
                    }

                }
*/

                int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.GRAY,Color.CYAN,Color.LTGRAY
                ,Color.WHITE,Color.CYAN,Color.BLUE,Color.DKGRAY,Color.GREEN};

                CategorySeries categorySeries = new CategorySeries("Daily Statistics");


                for(int i=0; i<labels.length; i++) {
                    categorySeries.add(labels[i], values1[i]);

                }


                /*Double[] s = new Double[fin.length];

                for (int i=0;i<fin.length;i++){
                 fin[i]=fin[i]*100;
                }

                for(int i=0; i<labels1.length; i++) {
                    categorySeries1.add(labels1[i], fin[i]);

                }
*/

                DefaultRenderer defaultRenderer = new DefaultRenderer();
                defaultRenderer.setChartTitle("Daily Statistics");
                defaultRenderer.setChartTitleTextSize(100);
                defaultRenderer.setBackgroundColor(Color.BLACK);
                defaultRenderer.setShowLegend(false);
                defaultRenderer.setLabelsTextSize(50);
                defaultRenderer.setAxesColor(Color.BLACK);
                defaultRenderer.setApplyBackgroundColor(true);
                defaultRenderer.setZoomButtonsVisible(false);

               /* DefaultRenderer defaultRenderer1 = new DefaultRenderer();
                defaultRenderer1.setChartTitle("Markov Chain");
                defaultRenderer1.setChartTitleTextSize(40);
                defaultRenderer1.setBackgroundColor(Color.BLACK);
                defaultRenderer1.setLegendTextSize(50);
                defaultRenderer1.setAxesColor(Color.BLACK);
                defaultRenderer1.setZoomButtonsVisible(false);*/

               /* for(int i=0; i<labels1.length; i++) {
                    SimpleSeriesRenderer simpleSeriesRenderer1 = new SimpleSeriesRenderer();
                    simpleSeriesRenderer1.setColor(colors[i]);
                    simpleSeriesRenderer1.setDisplayChartValues(true);
                    simpleSeriesRenderer1.setChartValuesTextSize(40);
                    defaultRenderer1.addSeriesRenderer(simpleSeriesRenderer1);
                }*/

                for(int i=0; i<labels.length; i++) {
                    SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
                    simpleSeriesRenderer.setColor(colors[i]);
                    simpleSeriesRenderer.setDisplayChartValues(true);
                    simpleSeriesRenderer.setChartValuesTextSize(80);
                   // simpleSeriesRenderer.setChartValuesTextSize(40);
                    defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
                }

                GraphicalView graphicalView=ChartFactory.getPieChartView(getApplicationContext(),categorySeries,defaultRenderer);
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutId);
                layout.addView(graphicalView,0);


               /* Intent pieChart = ChartFactory.getPieChartIntent(getBaseContext(), categorySeries,
                        defaultRenderer, "Daily Statistics");
                startActivity(pieChart);
*/




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_stats, menu);
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
