package movie.challengle.samsung.edu.samsungapp;
import android.app.TabActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.TabHost.TabSpec;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
    public static int col_change=0;
    TabHost TabHostWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabSpec tab1 = tabHost.newTabSpec("Now Playing");
        TabSpec tab2 = tabHost.newTabSpec("Upcoming Movies");


        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Now Playing");
        tab1.setContent(new Intent(this,Tb1Act.class));


        tab2.setIndicator("Upcoming Movies");
        tab2.setContent(new Intent(this,Tb2Act.class));



        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

    if(!isOnline())
    {
        Toast.makeText(MainActivity.this,
                "No Internet Connection!",
                Toast.LENGTH_LONG)
                .show();
    }
//s

    }
    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected())
        {

            return true;
        } else {

            return false;
        }
    }


}
