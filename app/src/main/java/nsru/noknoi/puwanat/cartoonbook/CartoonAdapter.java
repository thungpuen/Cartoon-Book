package nsru.noknoi.puwanat.cartoonbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by PC on 18/6/2559.
 */
public class CartoonAdapter extends BaseAdapter{

    //explicit
    private Context context;
    private String[] iconStrings, nameStrings, descripStrings,
            priceStrings, stockStrings;

    public CartoonAdapter(Context context,
                          String[] iconStrings,
                          String[] nameStrings,
                          String[] descripStrings,
                          String[] priceStrings,
                          String[] stockStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameStrings = nameStrings;
        this.descripStrings = descripStrings;
        this.priceStrings = priceStrings;
        this.stockStrings = stockStrings;
    }   //constuctor


    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cartoon_listview, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconStrings[position]).resize(150, 180).into(imageView);

        TextView nameTextView = (TextView) view.findViewById(R.id.textView11);
        TextView descripTextView = (TextView) view.findViewById(R.id.textView12);
        TextView priceTextView = (TextView) view.findViewById(R.id.textView13);
        TextView stockTextView = (TextView) view.findViewById(R.id.textView14);

        nameTextView.setText(nameStrings[position]);
        descripTextView.setText(descripStrings[position]);
        priceTextView.setText(priceStrings[position]);
        stockTextView.setText(stockStrings[position]);

        return view;
    }
}   //main class
