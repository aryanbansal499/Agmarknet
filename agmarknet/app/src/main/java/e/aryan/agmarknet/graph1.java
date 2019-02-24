package e.aryan.agmarknet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class graph1 extends AppCompatActivity {

    PieChart pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);
        pi = findViewById(R.id.pichart1);
        pi.setUsePercentValues(true);
        pi.getDescription().setEnabled(false);
        pi.setExtraOffsets(5,10,5,5);
        pi.setDragDecelerationFrictionCoef(0.95f);
        pi.isDrawHoleEnabled();
        pi.setHoleColor(Color.WHITE);
        pi.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(30f,"USA"));
        yValues.add(new PieEntry(35f,"UK"));
        yValues.add(new PieEntry(40f,"USSR"));
        yValues.add(new PieEntry(45f,"URUGUAY"));
        yValues.add(new PieEntry(5f,"UKRAINE"));
        yValues.add(new PieEntry(29f,"UTOPIA"));

        PieDataSet data = new PieDataSet(yValues,"Countries");
        data.setSelectionShift(5f);
        data.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData dataSet = new PieData(data);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.RED);

        pi.setData(dataSet);
    }
}
