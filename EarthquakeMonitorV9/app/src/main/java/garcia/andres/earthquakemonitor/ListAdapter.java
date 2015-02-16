package garcia.andres.earthquakemonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/*
 * Created by Andrés on 14/02/2015.
 */
//List_Adapter must either be declared abstract or implement abstract method 'onInput(Object, View)'
// in List_Adapter
public abstract class ListAdapter extends BaseAdapter{
    private ArrayList<?> structure;
    private int R_layout_IdView;
    private Context context;

    //Constructor
    //List adapter must either be abstrac or implement abstract methods 'getCount()' in 'Adapter'
    public ListAdapter(Context context, int R_layout_IdView, ArrayList<?> structure) {
        super();
        this.context = context;
        this.structure = structure;
        this.R_layout_IdView = R_layout_IdView;
    }

    //Generate code (getView)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onInput (structure.get(position), view);
        return view;
    }

    @Override
    public int getCount() {
        return structure.size();
    }

    @Override
    public Object getItem(int position) {
        return structure.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public abstract void onInput (Object input, View view);
}
