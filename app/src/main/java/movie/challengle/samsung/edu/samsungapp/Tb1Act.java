package movie.challengle.samsung.edu.samsungapp;

/**
 * Created by naeemaziz on 3/14/18.
 */
import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.DialogInterface;

public class Tb1Act extends Activity {

    ArrayList<Movie> MovieList;
    private ProgressDialog pDialog;
    ListView listView;
    HashMap<Integer,String> gener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);

        MovieList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view1);

        gener=new HashMap<Integer,String>();  //hashmap for diff Type of movies
        gener.put(28,"Action");
        gener.put(12,"Adventure");
        gener.put(16,"Animation");
        gener.put(35,"Comedy");
        gener.put(80,"Crime");
        gener.put(99,"Documentary");
        gener.put(18,"Drama");
        gener.put(10751,"Family");
        gener.put(14,"Fantasy");
        gener.put(36,"History");
        gener.put(27,"Horror");
        gener.put(10402,"Music");
        gener.put(9648,"Mystery");
        gener.put(10749,"Romance");
        gener.put(878,"Science Fiction");
        gener.put(10770,"TV Movie");
        gener.put(53,"Thriller");
        gener.put(10752,"War");
        gener.put(37,"Western");

        if (isOnline()) {  //corner case if internet available than move further
            try
            {
                (new Asychn_parseData()).execute();

            } catch (Exception e)
            {
                Log.d("exp1",e.getMessage());
            }
        }

    }


    private class Asychn_parseData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Tb1Act.this);
            pDialog.setMessage("Fetching the Data...");
            pDialog.setCancelable(false);
            pDialog.show();
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override
                public void onCancel(DialogInterface dialog) {

                    finish();
                }
            });

        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpHandler sh = new HttpHandler();


            String jsonStr = "";
            try {
                // Making a request to url and getting response
                jsonStr = sh.makeServiceCall("http://api.themoviedb.org/3/movie/now_playing?api_key=810de7f88d6bf353b2a32d7541d180ff");
                if (jsonStr != null) {
                    try {
                        //parsing json objects and array
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray sports = jsonObj.getJSONArray("results");

                        for (int i = 0; i < sports.length(); i++)
                        {
                            JSONObject c = sports.getJSONObject(i);

                            String id = c.getString("id");
                            String title = c.getString("title");
                            String pop = c.getString("popularity");
                            String imageurl = c.getString("poster_path");
                            JSONArray gen = c.getJSONArray("genre_ids");
                            String gennn="";
                            for (int j=0;j<gen.length();j++)
                            {
                                gennn=gennn+" "+gener.get(gen.get(j));

                            }
                            Movie Mobj=new Movie();
                            Mobj.setid(id);
                            Mobj.setimagelink(imageurl);
                            Mobj.settitle(title);
                            Mobj.setpopularity(pop);
                            Mobj.setgen(gennn);
                            MovieList.add(Mobj);
                        }

                    } catch (final JSONException e) {
                         Log.e("expe2", "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Tb1Act.this,
                                        "Could'nt get the data from server! ",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Tb1Act.this,
                                    "No Internet Connection!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } catch (Exception e) {
                Log.e("expe2",  e.getMessage());

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            MainActivity.col_change=0;
            //SHow Listview after parsing the jason data
            CustomBaseAdapter adapter = new CustomBaseAdapter(Tb1Act.this,MovieList);
            listView.setAdapter(adapter);


        }

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