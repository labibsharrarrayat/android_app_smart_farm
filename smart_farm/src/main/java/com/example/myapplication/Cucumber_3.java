package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class Cucumber_3 extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref3 = database.getReference("percentage3");
    final DatabaseReference moist3 = myref3;
    private TextView status, date, time;
    CustomGauge gauge3;
    GraphView graph3;
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    private int j = 0;
    private int val;
    private String moisture3;
    private String mhour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cucumber_3);

        status = (TextView)findViewById(R.id.text13);
        gauge3 = findViewById(R.id.gauge13);
        date = (TextView)findViewById(R.id.textMonth13);
        time = (TextView)findViewById(R.id.textDate13);

        graph3 = (GraphView)findViewById(R.id.graph13);
        series = new LineGraphSeries<DataPoint>();
        graph3.addSeries(series);
        Viewport viewport = graph3.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(100);
        viewport.setScrollable(true);
        graph3.getViewport().setScalable(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        moist3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                status.setText("Moisture: "+ value + "%");
                gauge3.setValue(Integer.valueOf(value));
                moisture3 = value;

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // we add 100 new entries
                        for (int i = 0; i < 10; i++) {
                            i = 0;

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    j = j + 1;
                                    addEntry(moisture3);
                                    Calender();
                                    Date();

                                    series.setDrawDataPoints(true);
                                    series.setDrawDataPoints(true);
                                    series.setDataPointsRadius(10);
                                    series.setThickness(8);



                                }

                            });

                            // sleep to slow down the add of entries
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                // manage error ...
                            }
                        }
                    }
                }).start();

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Calender(){

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        String[] words=currentDateandTime.split(" ");

        time.setText("Time: " + words[0]);
    }

    private void Date(){
        final SimpleDateFormat fDate = new SimpleDateFormat("yyyy:MM:dd");
        String currentDate = fDate.format(new Date());

        date.setText("Date:" + currentDate);
    }



    // add random data to graph
    private void addEntry(String draw) {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        //series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 100d), true, 10);
        //Calendar cal = Calendar.getInstance();
        //long date = System.currentTimeMillis()/1000;

        int date = Integer.valueOf(draw);
        series.appendData(new DataPoint(lastX++, date ), true, 20);
        //series.appendData(new DataPoint(lastX++, date), true, 10);

    }
}
