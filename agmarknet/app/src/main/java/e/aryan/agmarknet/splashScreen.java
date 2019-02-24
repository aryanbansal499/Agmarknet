package e.aryan.agmarknet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Splash launch = new Splash();
        launch.start();
    }
    private class Splash extends Thread{
        public void run(){
            try{
                sleep(4000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
           Intent intent = new Intent(splashScreen.this,MainActivity.class);
            startActivity(intent);
            splashScreen.this.finish();
        }
    }
}
