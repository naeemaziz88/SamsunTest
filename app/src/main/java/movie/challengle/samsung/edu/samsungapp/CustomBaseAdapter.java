package movie.challengle.samsung.edu.samsungapp;

/**
 * Created by naeemaziz on 3/14/18.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter{
    Context context;
    ArrayList<Movie> rowItems;

    public CustomBaseAdapter(Context context, ArrayList<Movie> items)
        {
            this.context = context;
            this.rowItems = items;
        }

    /* view holder class*/
    private class ViewHolder
    {
        TextView pop;
        TextView title;
        TextView genr;
        ImageView imageView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();
            holder.pop = (TextView) convertView.findViewById(R.id.popularity);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.genr = (TextView) convertView.findViewById(R.id.genr);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie rowItem = (Movie) getItem(position);
        holder.title.setText("Name: "+rowItem.gettitle());
        if(MainActivity.col_change==1)
        {
            holder.title.setTextColor(Color.MAGENTA);
        }
        else
        {
            holder.title.setTextColor(Color.RED);
        }
        holder.pop.setText("Popularity: "+rowItem.getpopularity());
        holder.genr.setText("Type: "+rowItem.getgen());
        String image="https://image.tmdb.org/t/p/w500"+rowItem.getimagelink();
        Picasso.with(context).load(image).into(holder.imageView); // Picasso library use to load the image


        return convertView;
    }

    @Override
    public int getCount()
    {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position)
    {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return rowItems.indexOf(getItem(position));
    }
}
