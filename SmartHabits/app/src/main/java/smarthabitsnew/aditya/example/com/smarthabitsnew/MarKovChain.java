package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class MarKovChain extends Activity {
    private CommentsDataSource datasource;
    HashMap<String,Double> map = new HashMap<>();
    String previousActivity=null;
    Double previousHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mar_kov_chain);
        datasource = new CommentsDataSource(this);
        datasource.open();
        Bundle bundle = getIntent().getExtras();
        String day = bundle.getString("day");
        List<Comment> commentList = datasource.getAllComments(day,"activityTable");
        String[] labels1 = {"Study","Recreation","Other"};
        String[] labels = new String[commentList.size()];
        double[] values = new double[commentList.size()];
        double hours = 0;

        for(int i=0;i<commentList.size();i++) {
            labels[i] = commentList.get(i).getaTable_activity();
            double a = Double.parseDouble(commentList.get(i).getaTable_hours());

            if(previousActivity!=null){

                if(labels[i].equals("Gym")||labels[i].equals("Play")||labels[i].equals("Coffee")
                        ||labels[i].equals("Eat")) {
                    if(previousActivity.equals("Gym")||previousActivity.equals("Play")||
                    labels[i].equals("Coffee") ||labels[i].equals("Eat")){
                        Double temp = map.get("R-R");
                        if(temp==null){
                            temp = 1.0;
                        }
                        else{
                            temp=temp+1;
                        }
                        map.put("R-R",temp);

                    }

                    else if(previousActivity.equals("Other")||previousActivity.equals("Sleep")){
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

                else if(labels[i].equals("Other")||previousActivity.equals("Sleep")){
                    if(previousActivity.equals("Gym")||previousActivity.equals("Play")||
                            labels[i].equals("Coffee") ||labels[i].equals("Eat")){
                        Double temp = map.get("O-R");
                        if(temp==null){
                            temp = 1.0;
                        }
                        else{
                            temp=temp+1;
                        }
                        map.put("O-R",temp);

                    }

                    else if(previousActivity.equals("Other")||previousActivity.equals("Sleep")){
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

                    if(previousActivity.equals("Gym")||previousActivity.equals("Play")||
                            labels[i].equals("Coffee") ||labels[i].equals("Eat")){
                        Double temp = map.get("S-R");
                        if(temp==null){
                            temp = 1.0;
                        }
                        else{
                            temp=temp+1;
                        }

                        map.put("S-R",temp);

                    }

                    else if(previousActivity.equals("Other")||previousActivity.equals("Sleep")){
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
            previousHour=Double.parseDouble(commentList.get(i).getaTable_hours());

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


        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.GRAY,Color.CYAN,Color.LTGRAY
                ,Color.WHITE,Color.CYAN,Color.BLUE,Color.DKGRAY,Color.GREEN};




        Double[] s = new Double[fin.length];

        for (int i=0;i<fin.length;i++){
            fin[i]=fin[i]*100;
        }

        CategorySeries categorySeries = new CategorySeries("Markov Chain");
        for(int i=0; i<labels1.length; i++) {
            categorySeries.add(labels1[i], fin[i]);

        }

        DefaultRenderer defaultRenderer = new DefaultRenderer();
        defaultRenderer.setChartTitle("Markov Chain");
        defaultRenderer.setChartTitleTextSize(100);
        defaultRenderer.setBackgroundColor(Color.BLACK);
        defaultRenderer.setShowLegend(false);
        defaultRenderer.setLabelsTextSize(50);
        defaultRenderer.setAxesColor(Color.BLACK);
        defaultRenderer.setApplyBackgroundColor(true);
        defaultRenderer.setZoomButtonsVisible(false);

        for(int i=0; i<labels1.length; i++) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            simpleSeriesRenderer.setDisplayChartValues(true);
            simpleSeriesRenderer.setChartValuesTextSize(80);
            // simpleSeriesRenderer.setChartValuesTextSize(40);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
        }

        GraphicalView graphicalView= ChartFactory.getPieChartView(getApplicationContext(), categorySeries, defaultRenderer);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.markovLayout);
        layout.addView(graphicalView,0);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mar_kov_chain, menu);
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
