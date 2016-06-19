package nsru.noknoi.puwanat.cartoonbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by PC on 19/6/2559.
 */
public class ConfirmAdapter extends BaseAdapter {

    //explicit
    private Context context;
    private String[] productStrings, priceStrings,
            amountStrings, totalStrings;

    public ConfirmAdapter(Context context,
                          String[] productStrings,
                          String[] priceStrings,
                          String[] amountStrings,
                          String[] totalStrings) {
        this.context = context;
        this.productStrings = productStrings;
        this.priceStrings = priceStrings;
        this.amountStrings = amountStrings;
        this.totalStrings = totalStrings;
    }   //constructor

    @Override
    public int getCount() {
        return productStrings.length;
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
        View view = layoutInflater.inflate(R.layout.confirm_listview, parent, false);

        //bind
        TextView productTextView = (TextView) view.findViewById(R.id.textView29);
        TextView priceTextView = (TextView) view.findViewById(R.id.textView30);
        TextView amountTextView = (TextView) view.findViewById(R.id.textView31);
        TextView totalTextView = (TextView) view.findViewById(R.id.textView32);

        productTextView.setText(productStrings[position]);
        priceTextView.setText(priceStrings[position]);
        amountTextView.setText(amountStrings[position]);
        totalTextView.setText(totalStrings[position]);

        return view;
    }
}   //main class
