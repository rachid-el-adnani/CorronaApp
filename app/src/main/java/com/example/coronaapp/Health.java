package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Health extends AppCompatActivity {

    //Vars
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //Hooks
        chart = findViewById(R.id.LineChart);


        //Getting the states from the diagnosis
        Bundle b = getIntent().getExtras();
        assert b != null;
        ArrayList<String> states =  b.getStringArrayList("data");

        //Setting the data in the chart
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();
        assert states != null;
        for (int i = 0; i < states.size(); i++){
            yValues.add(new Entry(i,  Float.parseFloat(states.get(i))));
            System.out.println(states);
        }
        //Creating the data set
        LineDataSet set1 = new LineDataSet(yValues, "Temperature");
        set1.setFillAlpha(110);
        //Colors and text size
        set1.setColor(Color.BLUE);
        set1.setLineWidth(3);
        set1.setValueTextSize(8f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        //adding limits
        //Upper one
        LimitLine upperLim = new LimitLine(37.5f, "Danger");
        upperLim.setLineWidth(4f);
        upperLim.enableDashedLine(10f, 10f, 0f);
        upperLim.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLim.setTextSize(12f);
        //lower one
        LimitLine lowerLim = new LimitLine(35f, "Too low");
        lowerLim.setLineWidth(4f);
        lowerLim.enableDashedLine(50f, 100f, 0f);
        lowerLim.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLim.setTextSize(12f);
        lowerLim.setLineColor(Color.rgb(51, 102, 255));

        //Putting it all together
        YAxis leftAxes = chart.getAxisLeft();
        leftAxes.removeAllLimitLines();
        leftAxes.addLimitLine(upperLim);
        leftAxes.addLimitLine(lowerLim);
        leftAxes.enableGridDashedLine(10f, 10f, 0f);
        leftAxes.setDrawLimitLinesBehindData(true);
        //Deleting right axe
        chart.getAxisRight().setEnabled(false);

        chart.setData(data);



    }

}
