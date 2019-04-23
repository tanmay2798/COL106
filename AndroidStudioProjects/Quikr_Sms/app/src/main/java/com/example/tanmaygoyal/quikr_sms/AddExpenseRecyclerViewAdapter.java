package com.example.tanmaygoyal.quikr_sms;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 2/12/2018.
 */

public class AddExpenseRecyclerViewAdapter extends RecyclerView.Adapter<AddExpenseRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "SecRecyclerViewAdapter";
   // MainActivity mn = new MainActivity();
    //ArrayList<String> expense = mn.fetch2();


    //vars
    private ArrayList<String> sector = new ArrayList<>();
    private ArrayList<String> spenditure = new ArrayList<>();
    int[] colours = new int[100];
    public String str;
    private Context mContext;


    public AddExpenseRecyclerViewAdapter(Context context, ArrayList<String> names, int[]color) {
        sector = names;
        colours=color;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listaddexpense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        float[] outerRadii = new float[]{75,75,75,75,75,75,75,75};
        float[] innerRadii = new float[]{75,75,75,75,75,75,75,75};
        ShapeDrawable backgroundShape = new ShapeDrawable(new RoundRectShape(
                outerRadii,
                null,
                innerRadii
        ));

        backgroundShape.getPaint().setColor(colours[position]); // background color
        backgroundShape.getPaint().setStyle(Paint.Style.FILL); // Define background
        backgroundShape.getPaint().setAntiAlias(true);

        // Initialize an array of drawables
        Drawable[] drawables = new Drawable[]{
                backgroundShape
        };
        // Set shape padding
        backgroundShape.setPadding(150,150,150,150);

        // Initialize a layer drawable object
        LayerDrawable layerDrawable = new LayerDrawable(drawables);

        // Finally, set the button background drawable
        holder.circleColor.setBackground(layerDrawable);

        holder.name.setText(sector.get(position));
        holder.circleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + sector.get(position));
                Toast.makeText(mContext, sector.get(position), Toast.LENGTH_SHORT).show();
                str=sector.get(position);
                SharedPreferences expenseSector = mContext.getSharedPreferences("exe", MODE_PRIVATE);
                SharedPreferences.Editor edit = expenseSector.edit();
                edit.clear();
                edit.putString("sector", str);
                edit.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sector.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        Button circleColor;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.exp);
            circleColor = (Button) itemView.findViewById(R.id.round2);
        }
    }
}