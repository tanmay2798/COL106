package com.example.tanmaygoyal.quikr_sms;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.tanmaygoyal.quikr_sms.FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY;
import static com.example.tanmaygoyal.quikr_sms.FeedReaderContract.FeedEntry.TABLE_NAME;
import static com.example.tanmaygoyal.quikr_sms.FeedReaderContractbills.FeedEntrybills.TABLE_NAMEBILLS;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int SMS_PERMISSION_CODE = 0;

    Toolbar toolbar;

    Button dailyb;
    Button weeklyb;
    Button monthlyb;

    Button janb,febb,marb,aprb,mayb,junb,julb,augb,sepb,octb,novb,decb;

    Button expenseb,billsb,profileb;

    private RequestQueue mQueue;

    int i=0,ja=0,jf=0,jai=0,jax=0;
    float suma=0,sumf=0,sumai=0,sumax=0;
    float jan=0,feb=0,mar=0,apr=0,may=0,jun=0,jul=0,aug=0,sep=0,oct=0,nov=0,dec=0;
    float w1=0,w2=0,w3=0,w4=0,w5=0;
    float[] arra = new float[10];
    float[] arrf = new float[10];
    float[] arrai = new float[10];
    float[] arrax = new float[10];
    float[] dailySum = new float[31];
    BarGraphSeries<DataPoint> series;
    DataPoint [] statsArray = new DataPoint[10];
    DataPoint [] statsArrayax = new DataPoint[10];
    DataPoint [] week = new DataPoint[6];
    DataPoint [] daily = new DataPoint[32];

    ArrayList<String> expense = new ArrayList<String>();
    ArrayList<String> expense1 = new ArrayList<String>();
    ArrayList<String> expense2 = new ArrayList<String>();
    ArrayList<String> expensebills = new ArrayList<String>();
    ArrayList<String> expense1bills = new ArrayList<String>();
    ArrayList<String> expense2bills = new ArrayList<String>();
    ArrayList<String> expensem = new ArrayList<String>();
    ArrayList<String> expensed = new ArrayList<String>();

    int [] colours = new int[100];

    ArrayList<String> secrorList = new ArrayList<String>();
    ArrayList<String> Spend = new ArrayList<String>();


    BarGraphSeries<DataPoint> seriesf;
    BarGraphSeries<DataPoint> seriesai;
    BarGraphSeries<DataPoint> seriesax;
    LineGraphSeries<DataPoint> monthwise;
    BarGraphSeries<DataPoint> weekwise;
    BarGraphSeries<DataPoint> dailywise;
    DataPoint [] statsArrayf = new DataPoint[10];
    DataPoint [] month = new DataPoint[12];
    DataPoint [] statsArrayai = new DataPoint[10];

    Date datem;
    ContentValues cv = new ContentValues();
    ContentValues cvbills = new ContentValues();
    public Cursor mCursor;
    public Cursor mCursorbills;


    SampleSQLiteDBHelper dbHelper;
    private SQLiteDatabase mdb;

    SampleSQLiteDBHelperbills dbHelperbills;
    private SQLiteDatabase mdbbills;

    long newRowId;
    long newRowIdbills;
    String selection;

    String date;
    String name1;
    //String smsMonth;
    Date finaldate;

    String monthName="jan";
    JSONObject abc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!hasReadSmsPermission()) {
            showRequestPermissionsInfoAlertDialog();
        }
        dbHelper = new SampleSQLiteDBHelper(this);
        mdb = dbHelper.getWritableDatabase();
        mQueue = Volley.newRequestQueue(this);
        dbHelperbills = new SampleSQLiteDBHelperbills(this);
        mdbbills = dbHelperbills.getWritableDatabase();

        mCursor = get();
        mCursorbills = getbills();


      //  Toast.makeText(getApplicationContext(), cursorcheck.getCount()+"", Toast.LENGTH_SHORT).show();


        fetch();
//        lv = (ListView)findViewById(R.id.expenselist);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fetch2());
//        lv.setAdapter(adapter);
//        TextView tv = new TextView(getApplicationContext());
//        tv.setText("Expense List");
//        tv.setTextColor(Color.BLACK);
//        tv.setTextSize(20);
//        lv.addHeaderView(tv);
//        lv.setClickable(true);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
//
//                Object o = lv.getItemAtPosition(position);
//                String st = "sdcard/";
//                File f = new File(st+o.toString());
//                mCursor.moveToPosition(position-1);
//                Toast.makeText(getApplicationContext(), mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY)), Toast.LENGTH_SHORT).show();
//            }
//        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Expenses");
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        dailyb = (Button)findViewById(R.id.button51);
        weeklyb = (Button)findViewById(R.id.button52);
        monthlyb = (Button)findViewById(R.id.button4);

        janb = (Button)findViewById(R.id.january);
        febb = (Button)findViewById(R.id.february);
        marb = (Button)findViewById(R.id.march);
        aprb = (Button)findViewById(R.id.april);
        mayb = (Button)findViewById(R.id.may);
        junb = (Button)findViewById(R.id.june);
        julb = (Button)findViewById(R.id.july);
        augb = (Button)findViewById(R.id.august);
        sepb = (Button)findViewById(R.id.september);
        octb = (Button)findViewById(R.id.october);
        novb = (Button)findViewById(R.id.november);
        decb = (Button)findViewById(R.id.december);

        janb.setTextColor(getResources().getColor(R.color.black));
        janb.setBackgroundResource(R.drawable.border);
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        expenseb = (Button)findViewById(R.id.expenses);
        billsb = (Button)findViewById(R.id.bills);
        profileb = (Button)findViewById(R.id.profile);
        Random rnd = new Random();
        //currentStrokeColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        for(int j=0;j<100;j++){
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
           colours[j]=color;
        }

        fetch2();
        initRecyclerView();
        initRecyclerView2();
        initRecyclerViewBills();
        initRecyclerViewAddExpense();

       // jsonParse();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.expense12: {
                View four = (View)findViewById(R.id.addExpenseView);
                four.setVisibility(View.VISIBLE);
                View one = (View)findViewById(R.id.expensesView);
                one.setVisibility(View.GONE);
                View two = (View)findViewById(R.id.billsView);
                two.setVisibility(View.GONE);
                View three = (View)findViewById(R.id.profileView);
                three.setVisibility(View.GONE);
                toolbar.setTitle("Add Expense");
                expenseb.setTextColor(getResources().getColor(R.color.colorAccent));
                billsb.setTextColor(getResources().getColor(R.color.colorAccent));
                profileb.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            }
            case R.id.bill12: {
                // do your sign-out stuff
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return true;
    }

    ///////////////////permissions//////////////////
    private void showRequestPermissionsInfoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permission_alert_dialog_title);
        builder.setMessage(R.string.permission_dialog_message);
        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestReadAndSendSmsPermission();
            }
        });
        builder.show();
    }



    private boolean hasReadSmsPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_SMS)) {
            Log.d(TAG, "shouldShowRequestPermissionRationale(), no permission requested");
            return;
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                SMS_PERMISSION_CODE);
    }


//////////////////////Main buttons///////////////////////////////

    public void expenses(View v){
        View four = (View)findViewById(R.id.addExpenseView);
        four.setVisibility(View.GONE);
        View one = (View)findViewById(R.id.expensesView);
        one.setVisibility(View.VISIBLE);
        View two = (View)findViewById(R.id.billsView);
        two.setVisibility(View.GONE);
        View three = (View)findViewById(R.id.profileView);
        three.setVisibility(View.GONE);
        toolbar.setTitle("Expenses");
        expenseb.setTextColor(getResources().getColor(R.color.black));
        billsb.setTextColor(getResources().getColor(R.color.colorAccent));
        profileb.setTextColor(getResources().getColor(R.color.colorAccent));

    }

    public void bills(View v){
        View four = (View)findViewById(R.id.addExpenseView);
        four.setVisibility(View.GONE);
        View one = (View)findViewById(R.id.expensesView);
        one.setVisibility(View.GONE);
        View two = (View)findViewById(R.id.billsView);
        two.setVisibility(View.VISIBLE);
        View three = (View)findViewById(R.id.profileView);
        three.setVisibility(View.GONE);
        toolbar.setTitle("Bills");
        expenseb.setTextColor(getResources().getColor(R.color.colorAccent));
        billsb.setTextColor(getResources().getColor(R.color.black));
        profileb.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void profile(View v){
        View four = (View)findViewById(R.id.addExpenseView);
        four.setVisibility(View.GONE);
        View one = (View)findViewById(R.id.expensesView);
        one.setVisibility(View.GONE);
        View two = (View)findViewById(R.id.billsView);
        two.setVisibility(View.GONE);
        View three = (View)findViewById(R.id.profileView);
        three.setVisibility(View.VISIBLE);
        toolbar.setTitle("Profile");
        expenseb.setTextColor(getResources().getColor(R.color.colorAccent));
        billsb.setTextColor(getResources().getColor(R.color.colorAccent));
        profileb.setTextColor(getResources().getColor(R.color.black));

    }


    public void addExpense(View v){
        View four = (View)findViewById(R.id.addExpenseView);
        four.setVisibility(View.GONE);
        View one = (View)findViewById(R.id.expensesView);
        one.setVisibility(View.VISIBLE);
        View two = (View)findViewById(R.id.billsView);
        two.setVisibility(View.GONE);
        View three = (View)findViewById(R.id.profileView);
        three.setVisibility(View.GONE);
        toolbar.setTitle("Expenses");
        expenseb.setTextColor(getResources().getColor(R.color.black));
        billsb.setTextColor(getResources().getColor(R.color.colorAccent));
        profileb.setTextColor(getResources().getColor(R.color.colorAccent));

        EditText companyname = (EditText)findViewById(R.id.exservice);
        EditText datename = (EditText)findViewById(R.id.exdate);
        EditText timename = (EditText)findViewById(R.id.extime);
        EditText amountname = (EditText)findViewById(R.id.examt);

        SharedPreferences expenseSector = getApplicationContext().getSharedPreferences("exe", MODE_PRIVATE);
        String str = expenseSector.getString("sector","");
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER,"");
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY,"");
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE,amountname.getText().toString());
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY,companyname.getText().toString());
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,str);
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH,datename.getText().toString().substring(3,6));
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,datename.getText().toString().substring(0,2));
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI,"manual");

        newRowId = mdb.insert(TABLE_NAME, null, cv);

        companyname.setText("");
        datename.setText("");
        amountname.setText("");

        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
        finish();


//        fetch();
//        fetch2();
//        initRecyclerView();
//        initRecyclerViewBills();
//        initRecyclerView2();
//        initRecyclerViewAddExpense();

    }



//////////////////////Month buttons///////////////////////////////
    public void january(View v){
       monthName="jan";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.black));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }
    public void february(View v){
        monthName="feb";
   //     Toast.makeText(getApplicationContext(), monthName, Toast.LENGTH_SHORT).show();
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.black));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }
    public void march(View v){
        monthName="mar";
    //    Toast.makeText(getApplicationContext(), monthName, Toast.LENGTH_SHORT).show();
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.black));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }
    public void april(View v){
        monthName="apr";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.black));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);
    }
    public void may(View v){
        monthName="may";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.black));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }
    public void june(View v){
        monthName="jun";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.black));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);
    }
    public void july(View v){
        monthName="jul";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.black));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }public void august(View v){
        monthName="aug";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.black));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));
        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }public void september(View v){
        monthName="sept";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.black));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }public void october(View v){
        monthName="oct";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.black));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border2);

    }public void november(View v){
        monthName="nov";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.black));
        decb.setTextColor(getResources().getColor(R.color.colorAccent));


        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border);
        decb.setBackgroundResource(R.drawable.border2);
    }
    public void december(View v){
        monthName="dec";
        fetch();
        fetch2();
        janb.setTextColor(getResources().getColor(R.color.colorAccent));
        febb.setTextColor(getResources().getColor(R.color.colorAccent));
        marb.setTextColor(getResources().getColor(R.color.colorAccent));
        aprb.setTextColor(getResources().getColor(R.color.colorAccent));
        mayb.setTextColor(getResources().getColor(R.color.colorAccent));
        junb.setTextColor(getResources().getColor(R.color.colorAccent));
        julb.setTextColor(getResources().getColor(R.color.colorAccent));
        augb.setTextColor(getResources().getColor(R.color.colorAccent));
        sepb.setTextColor(getResources().getColor(R.color.colorAccent));
        octb.setTextColor(getResources().getColor(R.color.colorAccent));
        novb.setTextColor(getResources().getColor(R.color.colorAccent));
        decb.setTextColor(getResources().getColor(R.color.black));

        janb.setBackgroundResource(R.drawable.border2);
        febb.setBackgroundResource(R.drawable.border2);
        marb.setBackgroundResource(R.drawable.border2);
        aprb.setBackgroundResource(R.drawable.border2);
        mayb.setBackgroundResource(R.drawable.border2);
        junb.setBackgroundResource(R.drawable.border2);
        julb.setBackgroundResource(R.drawable.border2);
        augb.setBackgroundResource(R.drawable.border2);
        sepb.setBackgroundResource(R.drawable.border2);
        octb.setBackgroundResource(R.drawable.border2);
        novb.setBackgroundResource(R.drawable.border2);
        decb.setBackgroundResource(R.drawable.border);

    }


//////////////////////messsage buttons///////////////////////////////
//    public void amazon(View v){
//        SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
//        SharedPreferences.Editor edit = one.edit();
//        edit.clear();
//        edit.putString("name", "amazon");
//        edit.commit();
//        Intent in = new Intent(this,Messages.class);
//        startActivity(in);
//
//    }
//
//    public void flipkart(View v){
//        SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
//        SharedPreferences.Editor edit = one.edit();
//        edit.clear();
//        edit.putString("name", "flipkart");
//        edit.commit();
//        Intent in = new Intent(this,Messages.class);
//        startActivity(in);
//    }
//
//    public void airtel(View v){
//        SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
//        SharedPreferences.Editor edit = one.edit();
//        edit.clear();
//        edit.putString("name", "airtel");
//        edit.commit();
//        Intent in = new Intent(this,Messages.class);
//        startActivity(in);
//    }
//
//    public void axis(View v){
//        SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
//        SharedPreferences.Editor edit = one.edit();
//        edit.clear();
//        edit.putString("name", "axis");
//        edit.commit();
//        Intent in = new Intent(this,Messages.class);
//        startActivity(in);
//    }

    ////////////////////////buttons//////////////////
    public void monthly(View v){
        PieChart pie = (PieChart) findViewById(R.id.piechart);
        pie.setVisibility(View.VISIBLE);
        PieChart pie2 = (PieChart) findViewById(R.id.piechart2);
        pie2.setVisibility(View.GONE);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.GONE);
        GraphView graphdaily = (GraphView) findViewById(R.id.graphdaily);
        graphdaily.setVisibility(View.GONE);
        GraphView graph2 = (GraphView) findViewById(R.id.graphmonth);
        graph2.setVisibility(View.GONE);
        GraphView graph1 = (GraphView) findViewById(R.id.graphweek);
        graph1.setVisibility(View.GONE);
        PieChart pie3 = (PieChart) findViewById(R.id.piechart3);
        pie3.setVisibility(View.GONE);

        monthlyb.setBackgroundResource(R.drawable.border3);
        weeklyb.setBackgroundResource(R.drawable.border2);
        dailyb.setBackgroundResource(R.drawable.border2);


    }

    public void company(View v){
        GraphView graph2 = (GraphView) findViewById(R.id.graphmonth);
        graph2.setVisibility(View.GONE);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.GONE);
        PieChart pie2 = (PieChart) findViewById(R.id.piechart2);
        pie2.setVisibility(View.GONE);
        PieChart pie3 = (PieChart) findViewById(R.id.piechart3);
        pie3.setVisibility(View.GONE);
        PieChart pie = (PieChart) findViewById(R.id.piechart);
        pie.setVisibility(View.VISIBLE);


    }

    public void weekly(View v) {
        GraphView graph2 = (GraphView) findViewById(R.id.graphmonth);
        graph2.setVisibility(View.GONE);
        PieChart pie = (PieChart) findViewById(R.id.piechart);
        pie.setVisibility(View.GONE);
        PieChart pie3 = (PieChart) findViewById(R.id.piechart3);
        pie3.setVisibility(View.GONE);
        PieChart pie2 = (PieChart) findViewById(R.id.piechart2);
        pie2.setVisibility(View.GONE);
        GraphView graphdaily = (GraphView) findViewById(R.id.graphdaily);
        graphdaily.setVisibility(View.GONE);
        GraphView graph = (GraphView) findViewById(R.id.graphweek);
        graph.setVisibility(View.VISIBLE);

        monthlyb.setBackgroundResource(R.drawable.border2);
        weeklyb.setBackgroundResource(R.drawable.border3);
        dailyb.setBackgroundResource(R.drawable.border2);
    }

    public void daily(View v) {
        GraphView graph2 = (GraphView) findViewById(R.id.graphmonth);
        graph2.setVisibility(View.GONE);
        GraphView graph = (GraphView) findViewById(R.id.graphweek);
        graph.setVisibility(View.GONE);
        PieChart pie = (PieChart) findViewById(R.id.piechart);
        pie.setVisibility(View.GONE);
        PieChart pie3 = (PieChart) findViewById(R.id.piechart3);
        pie3.setVisibility(View.GONE);
        PieChart pie2 = (PieChart) findViewById(R.id.piechart2);
        pie2.setVisibility(View.GONE);
        GraphView graphdaily = (GraphView) findViewById(R.id.graphdaily);
        graphdaily.setVisibility(View.VISIBLE);

        monthlyb.setBackgroundResource(R.drawable.border2);
        weeklyb.setBackgroundResource(R.drawable.border2);
        dailyb.setBackgroundResource(R.drawable.border3);
    }

    public void sector(View v){
        GraphView graph2 = (GraphView) findViewById(R.id.graphmonth);
        graph2.setVisibility(View.GONE);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.GONE);
        PieChart pie = (PieChart) findViewById(R.id.piechart);
        pie.setVisibility(View.GONE);
        PieChart pie3 = (PieChart) findViewById(R.id.piechart3);
        pie3.setVisibility(View.GONE);
        PieChart pie2 = (PieChart) findViewById(R.id.piechart2);
        pie2.setVisibility(View.VISIBLE);

    }

    /////////////////////////////fetch////////////////////////////////////
    public ArrayList<String> fetch() {

        mdb.delete(TABLE_NAME, "hi=?", new String[]{"auto"});
        mdbbills.delete(TABLE_NAME, null, null);
        Cursor cc = get();
       // Toast.makeText(getApplicationContext(), cc.getCount()+"jaja", Toast.LENGTH_SHORT).show();
        ArrayList<String> smsa = new ArrayList<String>();
        ArrayList<String> smsf = new ArrayList<String>();
        ArrayList<String> smsai = new ArrayList<String>();
        ArrayList<String> smsax = new ArrayList<String>();
        ArrayList<String> billai = new ArrayList<String>();
        ArrayList<Entry> sms = new ArrayList<Entry>();
        ArrayList<Entry> smstype = new ArrayList<Entry>();
        ArrayList<Entry> monthly = new ArrayList<Entry>();
        ArrayList<Entry> weekly = new ArrayList<Entry>();

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id","address","date","body"},null,null,null);
      //  Toast.makeText(getApplicationContext(), cursor.getCount()+"ji", Toast.LENGTH_SHORT).show();
        cursor.moveToPosition(-1);
        suma=0;
        sumf=0;
        sumai=0;
        sumax=0;
        ja = 0;
        jf = 0;
        jai = 0;
        jax = 0;



        while(cursor.moveToNext()) {
            String ide = cursor.getString(0);
            String address = cursor.getString(1);
            String body = cursor.getString(3);
            String date = cursor.getString(2);

            String dateFormat = "MMM";
            String dateSms = "dd";
            String dateSms1 = "yyyy-MM-dd";
            Calendar calendar = Calendar.getInstance();
            Long timestamp2 = Long.parseLong(date);
            calendar.setTimeInMillis(timestamp2);

            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            String smsMonth = formatter.format(calendar.getTime());

            SimpleDateFormat formatterdate = new SimpleDateFormat(dateSms);
            String smsDate = formatterdate.format(calendar.getTime());

            SimpleDateFormat formatterdater = new SimpleDateFormat(dateSms1);
            String smsDater = formatterdater.format(calendar.getTime());


            //  Toast.makeText(getApplicationContext(), smsDate+"jiji", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), smsMonth.substring(4,7)+"", Toast.LENGTH_SHORT).show();


/////////////////////////////////bills/////////////////////////////////////
            if (body.contains("Airtel No.") && body.contains("due on") && body.contains("ontime to avoid late fee.")) {
                billai.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
                for (i = body.indexOf("Rs ") + 3; Character.isDigit(body.charAt(i)) || body.charAt(i) == ','; i++) {

                }
                String str = body.substring(body.indexOf("Rs ") + 3, i);
                str = str.replace(",", "");
                // Toast.makeText(getApplicationContext(), smsDate+"jiji", Toast.LENGTH_SHORT).show();
                for (i = body.indexOf("due ") + 7; body.charAt(i) != '.'; i++) {

                }
                String str1 = body.substring(body.indexOf("due ") + 7, i);
                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_SENDER, address);
                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_BODY, body);
                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE, str);
                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY, "Airtel Bill");
                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE, str1);
                newRowIdbills = mdbbills.insert(TABLE_NAMEBILLS, null, cvbills);
                //Toast.makeText(getApplicationContext(), str1+"jiji", Toast.LENGTH_SHORT).show();
            }

/////////////payments/////////////////////
            if (body.contains("Arriving Today")) {
                smsa.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
                for (i = body.indexOf("Rs.") + 3; Character.isDigit(body.charAt(i)) || body.charAt(i) == ','; i++) {

                }
                String str = body.substring(body.indexOf("Rs.") + 3, i);
                str = str.replace(",", "");
                suma = suma + Float.valueOf(str);
                arra[0] = Float.valueOf(str.replace(",", ""));

                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER, address);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY, body);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE, str);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI, "auto");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY, "amazon");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE, "ecommerce");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH, smsMonth);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, smsDate);

                newRowId = mdb.insert(TABLE_NAME, null, cv);
                ja++;
            }

            if (body.contains("Out for Delivery")) {
                smsf.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
                for (i = body.indexOf("Rs.") + 3; Character.isDigit(body.charAt(i)) || body.charAt(i) == ','; i++) {

                }
                String str = body.substring(body.indexOf("Rs.") + 3, i);
                str = str.replace(",", "");
                sumf = sumf + Float.valueOf(str);
                arrf[0] = Float.valueOf(str.replace(",", ""));
                jf++;
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER, address);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY, body);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE, str);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI, "auto");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY, "flipkart");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE, "ecommerce");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH, smsMonth);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, smsDate);

                newRowId = mdb.insert(TABLE_NAME, null, cv);

            }

            if (body.contains("Thank You For Bill Payment")) {
                smsai.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
                for (i = body.indexOf("Rs ") + 3; Character.isDigit(body.charAt(i)) || body.charAt(i) == ','; i++) {

                }
                String str = body.substring(body.indexOf("Rs ") + 3, i);
                str = str.replace(",", "");
                sumai = sumai + Float.valueOf(str.replace(",", ""));
                arrai[0] = Float.valueOf(str.replace(",", ""));
                jai++;
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER, address);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY, body);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE, str);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI, "auto");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY, "airtel");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE, "mobile");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH, smsMonth);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, smsDate);


                newRowId = mdb.insert(TABLE_NAME, null, cv);

            }
            if (body.contains("Dear Investor") && body.contains("Axis") && body.contains("Payment of Rs.")) {
                smsax.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
                for (i = body.indexOf("Rs.") + 4; Character.isDigit(body.charAt(i)) || body.charAt(i) == ','; i++) {

                }
                String str = body.substring(body.indexOf("Rs.") + 4, i);
                str = str.replace(",", "");
                sumax = sumax + Float.valueOf(str.replace(",", ""));
                arrax[0] = Float.valueOf(str.replace(",", ""));
                jax++;
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER, address);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY, body);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE, str);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI, "auto");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY, "axis");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE, "fund");
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH, smsMonth);
                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, smsDate);


                newRowId = mdb.insert(TABLE_NAME, null, cv);

            }
        }

//        while (cursor.moveToNext()){
//
//            address = cursor.getString(1);
//            body = cursor.getString(3);
//            date = cursor.getString(2);
//            dateFormat = "MMM";
//            dateSms = "dd";
//             dateSms1 = "yyyy-MM-dd";
//            calendar = Calendar.getInstance();
//            timestamp2 = Long.parseLong(date);
//            calendar.setTimeInMillis(timestamp2);
//
//            formatter = new SimpleDateFormat(dateFormat);
//            smsMonth = formatter.format(calendar.getTime());
//
//            formatterdate = new SimpleDateFormat(dateSms);
//            smsDate = formatterdate.format(calendar.getTime());
//
//             formatterdater = new SimpleDateFormat(dateSms1);
//             smsDater = formatterdater.format(calendar.getTime());
//
//            ////////////////////////////////bills/////////////////////////////////////
//            if (body.contains("Airtel No.") && body.contains("due on") && body.contains("ontime to avoid late fee.")) {
//                billai.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
//                for(i=body.indexOf("Rs ")+3;Character.isDigit(body.charAt(i)) || body.charAt(i)==',' ;i++){
//
//                }
//                String str = body.substring(body.indexOf("Rs ")+3,i);
//                str=str.replace(",","");
////            sumai=sumai+Float.valueOf(str.replace(",",""));
////            arrai[0]=Float.valueOf(str.replace(",",""));
////            jai++;
//                for(i=body.indexOf("due ")+7;body.charAt(i)!='.' ;i++){
//
//                }
//                String str1 = body.substring(body.indexOf("due ")+7,i);
//                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_SENDER,address);
//                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_BODY,body);
//                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE,str);
//                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY,"Airtel Bill");
//                cvbills.put(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE,str1);
//                newRowIdbills = mdbbills.insert(TABLE_NAMEBILLS, null, cvbills);
//               // Toast.makeText(getApplicationContext(), str1+"jiji", Toast.LENGTH_SHORT).show();
//            }
//
///////////////payments/////////////////////
//
//
//            if (body.contains("Arriving Today")) {
//                smsa.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
//                for(i=body.indexOf("Rs.")+3;Character.isDigit(body.charAt(i)) || body.charAt(i)==',' ;i++){
//
//                }
//                String str = body.substring(body.indexOf("Rs.")+3,i);
//                str=str.replace(",","");
//                suma=suma+Float.valueOf(str.replace(",",""));
//                arra[ja]=Float.valueOf(str.replace(",",""));
//                ja++;
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER,address);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY,body);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE,str);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI,"auto");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY,"amazon");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,"ecommerce");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH,smsMonth);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,smsDate);
//
//
//                newRowId = mdb.insert(TABLE_NAME, null, cv);
//
//            }
//
//            if (body.contains("Out for Delivery")) {
//                smsf.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
//                for(i=body.indexOf("Rs.")+3;Character.isDigit(body.charAt(i)) || body.charAt(i)==',' ;i++){
//
//                }
//                String str = body.substring(body.indexOf("Rs.")+3,i);
//                str=str.replace(",","");
//                sumf=sumf+Float.valueOf(str.replace(",",""));
//                arrf[jf]=Float.valueOf(str.replace(",",""));
//                jf++;
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER,address);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY,body);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE,str);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI,"auto");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY,"flipkart");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,"ecommerce");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH,smsMonth);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,smsDate);
//
//
//                newRowId = mdb.insert(TABLE_NAME, null, cv);
//
//            }
//
//            if (body.contains("Thank You For Bill Payment")) {
//                smsai.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
//                for(i=body.indexOf("Rs ")+3;Character.isDigit(body.charAt(i)) || body.charAt(i)==',' ;i++){
//
//                }
//                String str = body.substring(body.indexOf("Rs ")+3,i);
//                str=str.replace(",","");
//                sumai=sumai+Float.valueOf(str.replace(",",""));
//                arrai[0]=Float.valueOf(str.replace(",",""));
//                jai++;
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER,address);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY,body);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE,str);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI,"auto");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY,"airtel");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,"mobile");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH,smsMonth);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,smsDate);
//
//
//
//                newRowId = mdb.insert(TABLE_NAME, null, cv);
//
//            }
//
//            if (body.contains("Dear Investor")  && body.contains("Axis") && body.contains("Payment of Rs.")) {
//                smsax.add("Address = " + address + "\n SMS = " + body + "\n id = " + ide + "\n date = " + date);
//                for(i=body.indexOf("Rs.")+4;Character.isDigit(body.charAt(i)) || body.charAt(i)==',' ;i++){
//
//                }
//                String str = body.substring(body.indexOf("Rs.")+4,i);
//                str=str.replace(",","");
//                sumax=sumax+Float.valueOf(str.replace(",",""));
//                arrax[0]=Float.valueOf(str.replace(",",""));
//                jax++;
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER,address);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY,body);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE,str);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HI,"auto");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY,"axis");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,"fund");
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH,smsMonth);
//                cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,smsDate);
//
//
//                newRowId = mdb.insert(TABLE_NAME, null, cv);
//
//            }
//
//
//
//        }


        insertBillOnline();
        insertExpenseOnline();
       ///////////////////pie chart////////////////////////////////////
        Spend.clear();
        secrorList.clear();
        monthlyExpneses();
        weeklyExpneses();
        dailyExpenses();
        weekly.clear();

        mCursor.moveToPosition(-1);
//        if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("airtel"))
//            sumai=sumai+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));

        while (mCursor.moveToPosition(-1)){
            if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("airtel"))
                sumai=sumai+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
        }

        mCursor.moveToPosition(-1);
//        if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("amazon"))
//            suma=suma+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));

        while (mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("amazon"))
                suma=suma+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
        }

        mCursor.moveToPosition(-1);
//        if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("axis"))
//            sumax=sumax+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));

        while (mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("axis"))
                sumax=sumax+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
        }

        mCursor.moveToPosition(-1);
//        if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("flipkart"))
//            sumf=sumf+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));

        while (mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)).equalsIgnoreCase("manual") && mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)).equalsIgnoreCase("flipkart"))
                sumf=sumf+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
        }

        sms.add(new Entry(suma,0) );
        sms.add(new Entry(sumf,1) );
        sms.add(new Entry(sumai,2) );
        sms.add(new Entry(sumax,3) );

        smstype.add(new Entry(suma+sumf,0) );
        smstype.add(new Entry(sumai,1) );
        smstype.add(new Entry(sumax,2) );

        Spend.add(suma+sumf+"");
        Spend.add(sumai+"");
        Spend.add(sumax+"");


        secrorList.add("E-commerce");
        secrorList.add("Mobile");
        secrorList.add("Funds");

        initRecyclerView();

        monthly.add(new Entry(jan,0));
        monthly.add(new Entry(feb,1));
        monthly.add(new Entry(mar,2));
        monthly.add(new Entry(apr,3));
        monthly.add(new Entry(may,4));
        monthly.add(new Entry(jun,5));
        monthly.add(new Entry(jul,6));
        monthly.add(new Entry(aug,7));
        monthly.add(new Entry(sep,8));
        monthly.add(new Entry(oct,9));
        monthly.add(new Entry(nov,10));
        monthly.add(new Entry(dec,11));

        weekly.add(new Entry(w1,0));
        weekly.add(new Entry(w2,1));
        weekly.add(new Entry(w3,2));
        weekly.add(new Entry(w4,3));
        weekly.add(new Entry(w5,4));
        PieDataSet dataSet = new PieDataSet(sms, "count");
        PieDataSet dataSet2 = new PieDataSet(smstype, "count");
        PieDataSet dataSet3 = new PieDataSet(monthly, "count");
        PieDataSet dataSet4 = new PieDataSet(weekly, "count");
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Amazon");
        xVals.add("Flipkart");
        xVals.add("Airtel");
        xVals.add("Axis");

        ArrayList<String> yVals = new ArrayList<String>();
        yVals.add("E-Commerce");
        yVals.add("Mobile");
        yVals.add("Funds");

        ArrayList<String> monthVals = new ArrayList<String>();
        monthVals.add("January");
        monthVals.add("February");
        monthVals.add("March");
        monthVals.add("April");
        monthVals.add("May");
        monthVals.add("June");
        monthVals.add("July");
        monthVals.add("August");
        monthVals.add("September");
        monthVals.add("October");
        monthVals.add("Novemeber");
        monthVals.add("December");

        ArrayList<String> weekVals = new ArrayList<String>();
        weekVals.clear();
        weekVals.add("01-07");
        weekVals.add("08-14");
        weekVals.add("15-21");
        weekVals.add("22-28");
        weekVals.add("29-31");

        PieData data = new PieData(xVals, dataSet);
        PieData data2 = new PieData(yVals, dataSet2);
        PieData data3 = new PieData(monthVals, dataSet3);
        PieData data4 = new PieData(weekVals, dataSet4);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        PieChart pieChart2 = (PieChart) findViewById(R.id.piechart2);
        PieChart pieChart3 = (PieChart) findViewById(R.id.piechart3);
        PieChart pieChart4 = (PieChart) findViewById(R.id.piechart4);
        pieChart4.clear();

        pieChart.setCenterText("company");
        pieChart2.setCenterText("sectors");
        pieChart3.setCenterText("monthly");
        pieChart4.setCenterText("weekly "+monthName);

        pieChart.setTouchEnabled(false);
        pieChart2.setTouchEnabled(false);
        pieChart3.setTouchEnabled(false);
        pieChart4.setTouchEnabled(false);
        pieChart.setData(data);
        pieChart.setDescription("This is Pie Chart");
        pieChart.setUsePercentValues(true);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart2.setData(data2);
        pieChart2.setDescription("This is Pie Chart");
        pieChart2.setUsePercentValues(true);
        dataSet2.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data2.setValueTextSize(13f);
        data2.setValueTextColor(Color.DKGRAY);

        pieChart3.setData(data3);
        pieChart3.setDescription("This is Pie Chart");
        pieChart3.setUsePercentValues(true);
        dataSet3.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data3.setValueTextSize(13f);
        data3.setValueTextColor(Color.DKGRAY);

        pieChart4.setData(data4);
        pieChart4.setDescription("This is Pie Chart");
       // pieChart.setUsePercentValues(true);
        dataSet4.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data4.setValueTextSize(13f);
        data4.setValueTextColor(Color.DKGRAY);


///////////////////////////////////////graph///////////////////////////////////////
        GraphView graph = (GraphView) findViewById(R.id.graph);
        GraphView graphmonth = (GraphView) findViewById(R.id.graphmonth);
        GraphView graphweek = (GraphView) findViewById(R.id.graphweek);
        GraphView graphdaily = (GraphView) findViewById(R.id.graphdaily);
        graphdaily.removeAllSeries();
        graphweek.removeAllSeries();

        graphdaily.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graphweek.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graphdaily.getGridLabelRenderer().setGridColor(Color.parseColor("#a8abab"));
        graphweek.getGridLabelRenderer().setGridColor(Color.parseColor("#a8abab"));
        statsArray=new DataPoint[10];

        for(int k=0;k<statsArray.length;k++){
            statsArray[k]=new DataPoint(k+1,0);
            statsArrayf[k]=new DataPoint(k+1,0);
            statsArrayai[k]=new DataPoint(k+1,0);
            statsArrayax[k]=new DataPoint(k+1,0);
        }

        for (ja=0;arra[ja]!=0;ja++) {
           statsArray[ja]= new DataPoint(ja+1, arra[ja]);
           // Toast.makeText(getApplicationContext(), arra[ja]+"B"+ja+"ji", Toast.LENGTH_SHORT).show();
        }
        for (jf=0;arrf[jf]!=0;jf++) {
            statsArrayf[jf]= new DataPoint(jf+1, arrf[jf]);
           // Toast.makeText(getApplicationContext(), arra[ja]+"B"+ja+"ji", Toast.LENGTH_SHORT).show();
        }
        for (jai=0;arrai[jai]!=0;jai++) {
            statsArrayai[jai]= new DataPoint(jai+1, arrai[jai]);
            // Toast.makeText(getApplicationContext(), arra[ja]+"B"+ja+"ji", Toast.LENGTH_SHORT).show();
        }
        for (jax=0;arrax[jax]!=0;jax++) {
            statsArrayax[jax]= new DataPoint(jax+1, arrax[jax]);
            // Toast.makeText(getApplicationContext(), arra[ja]+"B"+ja+"ji", Toast.LENGTH_SHORT).show();
        }


        month[0] = new DataPoint(0,jan);
        month[1] = new DataPoint(1,feb);
        month[2] = new DataPoint(2,mar);
        month[3] = new DataPoint(3,apr);
        month[4] = new DataPoint(4,may);
        month[5] = new DataPoint(5,jun);
        month[6] = new DataPoint(6,jul);
        month[7] = new DataPoint(7,aug);
        month[8] = new DataPoint(8,sep);
        month[9] = new DataPoint(9,oct);
        month[10] = new DataPoint(10,nov);
        month[11] = new DataPoint(11,dec);

        week[0] = new DataPoint(0,0);
        week[1] = new DataPoint(2,w1);
        week[2] = new DataPoint(4,w2);
        week[3] = new DataPoint(6,w3);
        week[4] = new DataPoint(8,w4);
        week[5] = new DataPoint(10,w5);

        int count=0;
        daily[count] = new DataPoint(count,count);
        for(count=1;count<=31;count++){
            daily[count] = new DataPoint(count,dailySum[count-1]);
        }

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"jan", "feb", "mar","apr", "may", "jun","jul", "aug", "sep","oct", "nov", "dec"});
        graphmonth.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graphweek);
        staticLabelsFormatter1.setHorizontalLabels(new String[] {"","01-07", "08-14", "15-21","22-28", "29-31"});
        graphweek.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
        graphweek.getGridLabelRenderer().setTextSize(18);

        series = new BarGraphSeries<>(statsArray);
        seriesf = new BarGraphSeries<>(statsArrayf);
        seriesai = new BarGraphSeries<>(statsArrayai);
        seriesax = new BarGraphSeries<>(statsArrayax);

        monthwise = new LineGraphSeries<>(month);
        weekwise = new BarGraphSeries<>(week);
        dailywise = new BarGraphSeries<>(daily);


        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxY(12000);
        graph.getViewport().setMaxX(Math.max(ja+1,jf+1));

        graphmonth.getViewport().setXAxisBoundsManual(true);
        graphmonth.getViewport().setYAxisBoundsManual(true);

        graphmonth.getViewport().setMinX(-1);
        graphmonth.getViewport().setMaxY(50000);
        graphmonth.getViewport().setMaxX(14);

        graphweek.getViewport().setXAxisBoundsManual(true);
        graphweek.getViewport().setYAxisBoundsManual(true);

     //   graphweek.getViewport().setMinX(-1);
        graphweek.getViewport().setMaxY(50000);
        graphweek.getViewport().setMaxX(10);
        graphweek.setScaleX(1);
       // graphweek.

        graphdaily.getViewport().setXAxisBoundsManual(true);
        graphdaily.getViewport().setYAxisBoundsManual(true);

        //   graphweek.getViewport().setMinX(-1);
        graphdaily.getViewport().setMaxY(20000);
        graphdaily.getViewport().setMaxX(32);
        graphdaily.setScaleX(1);
        graphdaily.getViewport().setScrollable(true);
        // graphweek.

        graph.addSeries(series);
        graph.addSeries(seriesf);
        graph.addSeries(seriesai);
        graph.addSeries(seriesax);
        graphmonth.addSeries(monthwise);
        graphweek.addSeries(weekwise);
        graphdaily.addSeries(dailywise);
        series.setColor(Color.BLUE);
        series.setSpacing(25);
        seriesf.setSpacing(25);
        seriesai.setSpacing(25);
        seriesax.setSpacing(25);
        weekwise.setSpacing(25);
        dailywise.setSpacing(30);

        dailywise.setColor(R.color.lightgrey);
        seriesf.setColor(Color.RED);
        seriesax.setColor(Color.GREEN);
        weekwise.setColor(Color.GREEN);
        weekwise.setDrawValuesOnTop(true);
        weekwise.setValuesOnTopColor(Color.parseColor("#5DA0C3"));
        weekwise.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.parseColor("#f0eded");
            }
        });

        dailywise.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.parseColor("#f0eded");
            }
        });


        seriesai.setColor(Color.BLACK);

        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
        graph.setScaleX(1);

        graphmonth.getViewport().setScrollable(true);
        graphmonth.getViewport().setScalableY(true);


        SharedPreferences one = getApplicationContext().getSharedPreferences("one", MODE_PRIVATE);
        String name1 = one.getString("name","");

        if(name1.equalsIgnoreCase("amazon"))
            return smsa;
        else if(name1.equalsIgnoreCase("flipkart"))
            return smsf;
        else if(name1.equalsIgnoreCase("flipkart"))
            return smsai;
        else
            return smsax;

    }



    public ArrayList<String> fetch2(){

        expense.clear();
        expense1.clear();
        expense2.clear();
        expense1bills.clear();
        expensebills.clear();
        expense2bills.clear();
        expensem.clear();
        expensed.clear();

        mCursor=get();
        mCursorbills=getbills();
        mCursor.moveToPosition(-1);
        mCursorbills.moveToPosition(-1);
      //  Toast.makeText(getApplicationContext(), mCursor.getCount()+"", Toast.LENGTH_SHORT).show();

        while (mCursor.moveToNext()) {
            String company = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY));
            String dates = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE));
            String price = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE));
            String month = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH));
            String body = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY));

            expense.add(company);
            expense1.add("Rs. " +price);
            expense2.add(body);
            expensem.add(month);
            expensed.add(dates);
        }

        while (mCursorbills.moveToNext()) {
            String companybills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY));
            String pricebills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE));
            String due_datebills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE));
            //Toast.makeText(getApplicationContext(), due_datebills.substring(3,6), Toast.LENGTH_SHORT).show();

            if (due_datebills.substring(3, 6).equalsIgnoreCase(monthName)) {
                expensebills.add(companybills);
                expense1bills.add("Rs. " + pricebills);
                expense2bills.add(due_datebills);
            }
        }

//        while (mCursor.moveToNext()){
//
//            company = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY));
//            price = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE));
//            body = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY));
//            month = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH));
//            dates = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE));
//
//            expense.add(company);
//            expense1.add("Rs. " +price);
//            expense2.add(body);
//            expensem.add(month);
//            expensed.add(dates);
//        }

//        while (mCursorbills.moveToNext()){
//            companybills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY));
//            pricebills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE));
//            due_datebills = mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE));
//            //Toast.makeText(getApplicationContext(), due_datebills.substring(3,6), Toast.LENGTH_SHORT).show();
//
//            if(due_datebills.substring(3,6).equalsIgnoreCase(monthName)) {
//                expensebills.add(companybills);
//                expense1bills.add("Rs. " + pricebills);
//                expense2bills.add(due_datebills);
//            }
//        }

        initRecyclerViewBills();

        return expense;
    }

    public Cursor get(){
        return mdb.query(
                TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                FeedReaderContract.FeedEntry._ID
        );
    }

    public Cursor getbills(){
        return mdbbills.query(
                TABLE_NAMEBILLS,
                null,
                selection,
                null,
                null,
                null,
                FeedReaderContractbills.FeedEntrybills._ID
        );
    }

    public void dailyExpenses(){

        for(int j=0;j<31;j++){
            dailySum[j]=0;
        }

        for(int c=0;c<31;c++){
            mCursor.moveToPosition(-1);

//            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase(monthName)){
//                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))==c+1)) {
//                    dailySum[c] = dailySum[c] + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//                }
//            }

            while(mCursor.moveToNext()) {
                if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase(monthName)){
                    if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))==c+1)) {
                        dailySum[c] = dailySum[c] + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                    }
                }
            }
        }
    }



    public void weeklyExpneses() {
        w1=0;w2=0;w3=0;w4=0;w5=0;
        mCursor.moveToPosition(-1);
        //Toast.makeText(getApplicationContext(), monthName+"ji", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))+"hi", Toast.LENGTH_SHORT).show();
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase(monthName)){
//          //  Toast.makeText(getApplicationContext(), monthName+"ji", Toast.LENGTH_SHORT).show();
//            if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=07) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=1)) {
//                w1 = w1 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//            }
//            if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=14) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=8)) {
//                w2 = w2 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//            }
//            if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=21) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=15)) {
//                w3 = w3 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//            }
//            if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=28) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=22)) {
//                w4= w4 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//            }
//            if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=31) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=29)) {
//                w5= w5 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//            }
//        }

        while(mCursor.moveToNext()) {
           // Toast.makeText(getApplicationContext(), monthName+"ji", Toast.LENGTH_SHORT).show();
          //  Toast.makeText(getApplicationContext(), Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))+"hi", Toast.LENGTH_SHORT).show();
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase(monthName)){
             //   Toast.makeText(getApplicationContext(), monthName+"ji", Toast.LENGTH_SHORT).show();
                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=7) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=1)) {
                    w1 = w1 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                }
                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=14) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=8)) {
                    w2 = w2 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                }
                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=21) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=15)) {
                    w3 = w3 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                }
                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=28) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=22)) {
                    w4= w4 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                }
                if ((Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))<=31) && (Integer.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)))>=29)) {
                    w5= w5 + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                }
            }
        }
    }


    public void monthlyExpneses(){
        mCursor.moveToPosition(-1);
       // Toast.makeText(getApplicationContext(), mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)), Toast.LENGTH_SHORT).show();

//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jan")){
//            jan=jan+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("feb")){
//            feb=feb+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("mar")){
//            mar=mar+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("apr")){
//            apr=apr+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("may")){
//            may=may+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jun")){
//            jun=jun+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jul")){
//            jul=jul+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }
//        if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("aug")){
//            aug=aug+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("sep")){
//            sep=sep+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("oct")){
//            oct=oct+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("nov")){
//            nov=nov+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("dec")) {
//            dec = dec + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
//        }

        while(mCursor.moveToNext()){
          //  Toast.makeText(getApplicationContext(), mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)), Toast.LENGTH_SHORT).show();

            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jan")){
                jan=jan+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("feb")){
                feb=feb+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("mar")){
                mar=mar+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("apr")){
                apr=apr+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("may")){
                may=may+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jun")){
                jun=jun+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("jul")){
                jul=jul+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
            if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("aug")){
                aug=aug+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("sep")){
                sep=sep+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("oct")){
                oct=oct+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("nov")){
                nov=nov+Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }if (mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)).equalsIgnoreCase("dec")) {
                dec = dec + Float.valueOf(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }

            }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        SectorRecyclerViewAdapter adapter = new SectorRecyclerViewAdapter(this, secrorList,Spend,colours);
        recyclerView.setAdapter(adapter);

    }

    private void initRecyclerView2(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(layoutManager);
        SpendRecyclerViewAdapter adapter = new SpendRecyclerViewAdapter(this, expense,expense1,expense2,colours,expensem,expensed);
        recyclerView.setAdapter(adapter);

    }

    private void initRecyclerViewBills(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewbills);
        recyclerView.setLayoutManager(layoutManager);
        BillRecyclerViewAdapter adapter = new BillRecyclerViewAdapter(this, expensebills,expense1bills,expense2bills,colours);
        recyclerView.setAdapter(adapter);

    }

    private void initRecyclerViewAddExpense(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(layoutManager);
        AddExpenseRecyclerViewAdapter adapter = new AddExpenseRecyclerViewAdapter(this, secrorList,colours);
        recyclerView.setAdapter(adapter);

    }

    private void insertDataOnline() {
        String url = "http://192.168.124.55:7191/mqdp/v1/expenseTracker";
        JSONObject j = new JSONObject();
        JSONObject jj = new JSONObject();
            try {
                j.put("sender", "one");
                j.put("body", "two");
                j.put("price", "3");
                j.put("company", "4");
                j.put("type", "");
                j.put("date", "6");
                j.put("month", "7");
                j.put("expenseType","8");
                j.put("due", "hi");
                JSONArray ja = new JSONArray();
                ja.put(j);
                jj.put("expenses", ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    System.out.println(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("cache-control", "no-cache");
                    headers.put("content-type", "application/json");
                    headers.put("postman-token", "83f34052-2d94-3da0-9d99-b93d26b56996");
                    headers.put("user-agent", "QuikrConsumer");
                    headers.put("x-quikr-app-id", "65");
                    headers.put("x-quikr-client", "Android.10_11");
                    headers.put("x-quikr-client-demail", "qaeklijsxtij8@quikr.com");
                    headers.put("x-quikr-client-device-id", "9dfbe31a1abe4396");
                    headers.put("x-quikr-client-lang-code", "en");
                    headers.put("x-quikr-client-signature", "zXcv80386Mdp1hs0q7o0p9uiLZV37TdF");
                    headers.put("x-quikr-client-version", "10.11");
                    headers.put("x-quikr-signature-v2", "97e9eba460bd00683d71aa9ca4a6fef97bafa8a8");
                    headers.put("x-quikr-token-id", "19447");
                    return headers;
                }
            };
            mQueue.add(request);

    }


    private void insertExpenseOnline() {
        String url = "http://192.168.124.55:7191/mqdp/v1/expenseTracker";
        JSONObject j = new JSONObject();
        JSONObject jj = new JSONObject();
        mCursor.moveToPosition(-1);
        while (mCursor.moveToNext()) {
            try {
                j.put("sender", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER)));
                j.put("body", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY)));
                j.put("price", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
                j.put("company", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)));
                j.put("type", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE)));
                j.put("date", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)));
                j.put("month", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH)));
                j.put("expenseType", mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HI)));
                j.put("due", "hi");
                JSONArray ja = new JSONArray();
                ja.put(j);
                jj.put("expenses", ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    System.out.println(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("cache-control", "no-cache");
                    headers.put("content-type", "application/json");
                    headers.put("postman-token", "83f34052-2d94-3da0-9d99-b93d26b56996");
                    headers.put("user-agent", "QuikrConsumer");
                    headers.put("x-quikr-app-id", "65");
                    headers.put("x-quikr-client", "Android.10_11");
                    headers.put("x-quikr-client-demail", "qaeklijsxtij8@quikr.com");
                    headers.put("x-quikr-client-device-id", "9dfbe31a1abe4396");
                    headers.put("x-quikr-client-lang-code", "en");
                    headers.put("x-quikr-client-signature", "zXcv80386Mdp1hs0q7o0p9uiLZV37TdF");
                    headers.put("x-quikr-client-version", "10.11");
                    headers.put("x-quikr-signature-v2", "97e9eba460bd00683d71aa9ca4a6fef97bafa8a8");
                    headers.put("x-quikr-token-id", "19447");
                    return headers;
                }
            };
            mQueue.add(request);
        }
    }


    private void insertBillOnline() {
        String url = "http://192.168.124.55:7191/mqdp/v1/expenseTracker";
        JSONObject j = new JSONObject();
        JSONObject jj = new JSONObject();
        mCursorbills.moveToPosition(-1);
        while (mCursorbills.moveToNext()) {
            try {
                j.put("sender", mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_SENDER)));
                j.put("body", mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_BODY)));
                j.put("price", mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE)));
                j.put("company", mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY)));
                j.put("type", "");
                j.put("date", "");
                j.put("month", "");
                j.put("expenseType", "");
                j.put("due", mCursorbills.getString(mCursorbills.getColumnIndex(FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE)));
                JSONArray ja = new JSONArray();
                ja.put(j);
                jj.put("expenses", ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    System.out.println(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("cache-control", "no-cache");
                    headers.put("content-type", "application/json");
                    headers.put("postman-token", "83f34052-2d94-3da0-9d99-b93d26b56996");
                    headers.put("user-agent", "QuikrConsumer");
                    headers.put("x-quikr-app-id", "65");
                    headers.put("x-quikr-client", "Android.10_11");
                    headers.put("x-quikr-client-demail", "qaeklijsxtij8@quikr.com");
                    headers.put("x-quikr-client-device-id", "9dfbe31a1abe4396");
                    headers.put("x-quikr-client-lang-code", "en");
                    headers.put("x-quikr-client-signature", "zXcv80386Mdp1hs0q7o0p9uiLZV37TdF");
                    headers.put("x-quikr-client-version", "10.11");
                    headers.put("x-quikr-signature-v2", "97e9eba460bd00683d71aa9ca4a6fef97bafa8a8");
                    headers.put("x-quikr-token-id", "19447");
                    return headers;
                }
            };
            mQueue.add(request);
        }
    }


    private void jsonParse() {
        String url = "http://192.168.124.55:7191/mqdp/v1/expenseTracker";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("GetExpensesApplicationResponse").getJSONObject("GetExpensesApplication").getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                abc=employee;
                                Toast.makeText(getApplicationContext(),employee+"" , Toast.LENGTH_SHORT).show();
                               // mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String,String > headers = new HashMap<String, String>();
                headers.put("cache-control","no-cache");
                headers.put("content-type","application/json");
                headers.put("postman-token","83f34052-2d94-3da0-9d99-b93d26b56996");
                headers.put("user-agent","QuikrConsumer");
                headers.put("x-quikr-app-id","65");
                headers.put("x-quikr-client","Android.10_11");
                headers.put("x-quikr-client-demail","qaeklijsxtij8@quikr.com");
                headers.put("x-quikr-client-device-id","9dfbe31a1abe4396");
                headers.put("x-quikr-client-lang-code","en");
                headers.put("x-quikr-client-signature","zXcv80386Mdp1hs0q7o0p9uiLZV37TdF");
                headers.put("x-quikr-client-version","10.11");
                headers.put("x-quikr-signature-v2","97e9eba460bd00683d71aa9ca4a6fef97bafa8a8");
                headers.put("x-quikr-token-id","19447");

                return headers;
            }
        };

        mQueue.add(request);
    }

}