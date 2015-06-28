package smarthabitsnew.aditya.example.com.smarthabitsnew;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SmartTips extends Activity {
    private CommentsDataSource datasource;
    HashMap<String,List<Double>> map = new HashMap<>();
    HashMap<String,Long> map1 = new HashMap<>();
    HashMap<Long,String> map2 = new HashMap<>();

    List<Double> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_tips);
        datasource = new CommentsDataSource(this);
        datasource.open();
        Bundle bundle = getIntent().getExtras();
        String day = bundle.getString("day");
        List<Comment> commentList1 = datasource.getAllComments(day,"smartTipsCalendar");
        List<Comment> commentList2 = datasource.getAllComments(day,"dateTable");
        List<Comment> commentList = datasource.getAllComments(day,"smartTips");

        for(int i=0;i<commentList2.size();i++){
            map2.put(commentList2.get(i).getdTable_day(),commentList2.get(i).getdTable_date());

        }

        for(int i=0;i<commentList.size();i++){

            String key = commentList.get(i).getaTable_activity();
            Double value = Double.parseDouble(commentList.get(i).getaTable_hours());

            if(key.equals("Gym")||key.equals("Other")||key.equals("Coffee")||key.equals("Eat")
                    ||key.equals("Play")||key.equals("Sleep"))
              continue;

            if(map.get(key)==null){
               list = new ArrayList<>();
               list.add(value);
               map.put(key,list);
            }

            else{
                list = map.get(key);
                Double temp = list.get(0);
                temp = temp+value;
                list = new ArrayList<>();
                list.add(temp);
                map.put(key,list);

            }



        }

        for(int i=0;i<commentList1.size();i++){
            String key = commentList1.get(i).getcTable_name();
            Double value = Double.parseDouble(commentList1.get(i).getcTable_diff());
            Long date = commentList1.get(i).getcTable_day();
            map1.put(key,date);

            if(map.get(key)==null){
                list = new ArrayList<>();
                list.add(value);
                map.put(key,list);
            }

            else{
                list = map.get(key);
                list.add(value);
                map.put(key,list);

            }


        }

        List<String> displayList = new ArrayList<>();
        Set<String> keys = map.keySet();

        for(String s:keys){
            List<Double> list = map.get(s);
            if(list.size()==1){
                displayList.add("You have not yet started the activity "+s+" You have set "+list.get(0)+
                        " hours to complete it. Deadline is on "+map2.get(map1.get(s)));

            }


            else {
                Long l = map1.get(s);
                String string = map2.get(map1.get(s));
                DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                String secDay = map2.get(Long.parseLong(day));
                Date date=null;
                Date secDate=null;
                TimeUnit timeUnit=TimeUnit.DAYS;
                long diffInMillies;
                long difference=0;
                try {
                    date = format.parse(string);
                    secDate=format.parse(secDay);
                    if(date.after(secDate)) {
                        diffInMillies = date.getTime() - secDate.getTime();
                        difference=timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
                    }
                    else{
                        diffInMillies = secDate.getTime() - date.getTime();
                        difference=timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
                    }

                }
                catch(ParseException e){
                   e.printStackTrace();
                }
                 if(list.get(0)<list.get(1)){
                    if(difference<4) {
                        displayList.add("You have completed " + list.get(0) + " hours out of allotted " +
                                list.get(1) + " hours for the activity " + s + ".The deadline is on " + map2.get(map1.get(s))
                        +".Pick up the pace. You just have "+difference+" days to complete.");
                    }

                     else if(difference>7 & difference<10){
                        displayList.add("You have completed " + list.get(0) + " hours out of allotted " +
                                list.get(1) + " hours for the activity " + s + ".The deadline is on " + map2.get(map1.get(s))
                                +". No need to hurry . You have "+difference+" days to complete.");
                    }

                     else{

                        displayList.add("You have completed " + list.get(0) + " hours out of allotted " +
                                list.get(1) + " hours for the activity " + s + ".The deadline is on " + map2.get(map1.get(s))
                                +". You can continue later. Finish other assignments first. You have "+difference+" days to complete.");
                    }
                 }

                else if(list.get(0)>list.get(1)){
                    displayList.add("You have over shot. You have taken "+list.get(0) +"hours. You had allotted" +
                            list.get(1)+" hours for the activity "+s+" .The deadline was on "+map2.get(map1.get(s)));

                }



            }


        }

        final ListView listView = (ListView) findViewById(R.id.smartTipsList);
        String[] str = new String[displayList.size()];
        for(int i=0; i<displayList.size();i++){
            str[i]=displayList.get(i);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smart_tips, menu);
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
