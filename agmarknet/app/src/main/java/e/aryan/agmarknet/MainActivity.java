package e.aryan.agmarknet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView weekly;
    ImageView monthly;
    ImageView daily;
    ImageView market;
    StateWise stateWise = new StateWise();
    WeekTabs w = new WeekTabs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weekly = findViewById(R.id.weeklyIm);
        monthly = findViewById(R.id.monthlyIm);
        daily = findViewById(R.id.dailyIm);
        market = findViewById(R.id.markIm);


        weekly.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,WeekTrends.class);
               intent.putExtra("Type","Weekly");
               startActivity(intent);

            }

       });

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WeekTrends.class);
                intent.putExtra("Type","Monthly");
                startActivity(intent);

            }
        });
//      daily.setOnClickListener(new View.OnClickListener() {
//                   @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,graph1.class);
//                startActivity(intent);

//            }
//        });

//        market.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,graph1.class);
//                startActivity(intent);
//            }
//        });



    }


}
