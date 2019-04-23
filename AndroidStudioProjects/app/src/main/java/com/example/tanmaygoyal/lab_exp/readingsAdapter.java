package com.example.tanmaygoyal.lab_exp;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by User on 2/12/2018.
 */

public class readingsAdapter extends RecyclerView.Adapter<readingsAdapter.ViewHolder> {

    private static final String TAG = "SpenRecyclerViewAdapter";
    public static String str;
    //ArrayList<String> expense = mn.fetch2();



    //vars
    private ArrayList<String> headings = new ArrayList<>();
    private Context mContext;
    public static ArrayList<String> reading = new ArrayList<>();


    public readingsAdapter(Context context, ArrayList<String> names) {
        headings = names;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        reading.clear();
        for(int i=0;i<getItemCount();i++){
            reading.add("0");
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        float[] outerRadii = new float[]{75,75,75,75,75,75,75,75};
        float[] innerRadii = new float[]{75,75,75,75,75,75,75,75};
        ShapeDrawable backgroundShape = new ShapeDrawable(new RoundRectShape(
                outerRadii,
                null,
                innerRadii
        ));


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
//        holder.circleColor.setBackground(layerDrawable);
//
        holder.txt.setText(headings.get(position));

        //holder.edt.setText("0");
        //str = holder.edt.getText().toString();
       // Toast.makeText(mContext, str+"ii", Toast.LENGTH_SHORT).show();

//        holder.spendss.setText(spenditure.get(position));
//        holder.m.setText(months.get(position));
//        holder.d.setText(dates.get(position));
//
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reading.set(position,holder.edt.getText().toString());
            }
        });


       // return reading
    }

    public ArrayList<String> make(){
        return reading;
    }


    @Override
    public int getItemCount() {
        return headings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText edt;
        TextView txt;
        Button b;

        public ViewHolder(View itemView) {
            super(itemView);
            edt=itemView.findViewById(R.id.reading1);
            txt=itemView.findViewById(R.id.textView);
            b=itemView.findViewById(R.id.add);
        }
    }
}