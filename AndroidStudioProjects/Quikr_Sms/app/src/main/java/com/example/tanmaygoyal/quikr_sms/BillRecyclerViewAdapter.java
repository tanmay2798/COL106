package com.example.tanmaygoyal.quikr_sms;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/12/2018.
 */

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "bilRecyclerViewAdapter";
    //vars
    private ArrayList<String> company = new ArrayList<>();
    private ArrayList<String> spenditure = new ArrayList<>();
    private ArrayList<String> due_date = new ArrayList<>();
    int[] colours = new int[100];

    private Context mContext;

    public BillRecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> spends, ArrayList<String> body,int[]color) {
        company = names;
        spenditure=spends;
        due_date=body;
        colours=color;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitembills, parent, false);
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

        holder.name.setText(company.get(position));
        holder.spendss.setText(spenditure.get(position));
        holder.dueDate.setText(due_date.get(position));
//        holder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on an image: " + company.get(position));
//
//                Toast.makeText(mContext, due_date.get(position), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return company.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        TextView spendss;
        TextView dueDate;
        Button circleColor;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            spendss = itemView.findViewById(R.id.spendss);
            dueDate = itemView.findViewById(R.id.due);
            circleColor = (Button) itemView.findViewById(R.id.button202);
        }
    }
}