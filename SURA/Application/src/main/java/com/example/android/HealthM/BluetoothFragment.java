/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.HealthM;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.bluetoothchat.R;
import com.example.android.common.logger.Log;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.PublicKey;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment controls Bluetooth to communicate with other devices.
 */
public class BluetoothFragment extends Fragment {

    private static final String TAG = "BluetoothFragment";


    int check=0;
    private DatabaseReference databaseReference;
    private Cursor mCursor;
    Cursor cursor;
    public static String flag="",height="",weight="",temperature="",lung="", bp1="",bp2="",heart="",max_speed="",avg_speed="";

    SampleSQLiteDBHelper dbHelper;
    private SQLiteDatabase mdb;
    ContentValues cv = new ContentValues();
    String selection;
    String name1="";
    String age1="";


    long newRowId;

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    // Layout Views
    private ListView mConversationView;
    private EditText mOutEditText;

    private Button mSendButton;
    private Button msubmit;
    private Button mheightButton;
    private Button mweightButton;
    private Button mtemperatureButton;
    private Button mlungButton;
    private Button mbpButton;
    private Button mheartButton;
    private Button mPrintButton;
    private Button mSaveButton;
    private Button mreportButton;


    /**
     * Name of the connected device
     */
    private String mConnectedDeviceName = null;

    /**
     * Array adapter for the conversation thread
     */
    private ArrayAdapter<String> mConversationArrayAdapter;

    /**
     * String buffer for outgoing messages
     */
    private StringBuffer mOutStringBuffer;

    /**
     * Local Bluetooth adapter
     */
    private BluetoothAdapter mBluetoothAdapter = null;

    /**
     * Member object for the chat services
     */
    private BluetoothService mChatService = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        SharedPreferences one = getContext().getSharedPreferences("one", MODE_PRIVATE);
        name1 = one.getString("name","");
        String[] array = name1.split(",",2);
        name1=array[0];
        age1=array[1];
     //   Toast.makeText(getContext(), "tyo"+name1, Toast.LENGTH_LONG).show();


        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            FragmentActivity activity = getActivity();
            activity.finish();
        }

        dbHelper = new SampleSQLiteDBHelper(getActivity());
        mdb = dbHelper.getWritableDatabase();
        cursor = get();
        mCursor = cursor;

        databaseReference = FirebaseDatabase.getInstance().getReference("name");
    }


    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled.

        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else if (mChatService == null) {

                setupChat();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatService != null) {
            mChatService.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_bluetooth, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mConversationView = (ListView) view.findViewById(R.id.in);

        mheightButton = (Button) view.findViewById(R.id.button_height);
        mweightButton = (Button) view.findViewById(R.id.weight);
        mtemperatureButton = (Button) view.findViewById(R.id.temperature);
        mlungButton = (Button) view.findViewById(R.id.lung);
        mbpButton = (Button) view.findViewById(R.id.bp);
        mheartButton = (Button) view.findViewById(R.id.heart);
        mPrintButton = (Button) view.findViewById(R.id.print);
        mSaveButton = (Button) view.findViewById(R.id.save);
        mreportButton = (Button) view.findViewById(R.id.report);


    }

    /**
     * Set up the UI and background operations for chat.
     */
    private void setupChat() {
        Log.d(TAG, "setupChat()");

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.message);

            mConversationView.setAdapter(mConversationArrayAdapter);

        // Initialize the compose field with a listener for the return key
////        mOutEditText.setOnEditorActionListener(mWriteListener);


        // Initialize the send button with a listener that for click events
//        mSendButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Send a message using content of the edit text widget
//                View view = getView();
//                if (null != view) {
//                    TextView textView = (TextView) view.findViewById(R.id.redt);
//                    name1 = textView.getText().toString();
//                    main=false;
//                    Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
//                    Intent in = new Intent(getContext(),MainActivity.class);
//                    startActivity(in);
//                }
//                Toast.makeText(getActivity(), "hi1", Toast.LENGTH_SHORT).show();
//            }
//        });

//        msubmit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Send a message using content of the edit text widget
//                View view = getView();
//                if (null != view) {
//                    TextView textView = (TextView) view.findViewById(R.id.nedt);
//                    name1 = textView.getText().toString();
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
//                                user user = userSnapshot.getValue(user.class);
//                                if(name1.equals(user.getName())) {
//                                   copy=true;
//                                   break;
//                                }
//
//                            }
//                            if(copy==true){
//                                Toast.makeText(getActivity(), "user id already exist", Toast.LENGTH_SHORT).show();
//                                name1="";
//
//                            }
//                            if(copy==false){
//                               main=false;
//                               Intent in = new Intent(getContext(),MainActivity.class);
//                               startActivity(in);
//                        }
//
//                            copy=false;
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//                }
//            }
//        });

        mheightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="height";
                    String message = "height";
                    sendMessage(message);
                }
            }
        });

        mweightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="weight";
                    String message = "weight";
                    sendMessage(message);
                }
            }
        });

        mtemperatureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="temperature";
                    String message = "temperature";
                    sendMessage(message);
                }
            }
        });

        mlungButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="lung";
                    String message = "lung";
                    sendMessage(message);
                }
            }
        });

        mreportButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    SharedPreferences one = getContext().getSharedPreferences("one", MODE_PRIVATE);
                    name1 = one.getString("name","");
                    String[] array = name1.split(",",2);
                    name1=array[0];
                    age1=array[1];


                    //create document object
                    Document doc=new Document();
//output file path
                    String outpath= Environment.getExternalStorageDirectory()+"/name1+age1.pdf";
                    try {
//create pdf writer instance
                        PdfWriter.getInstance(doc, new FileOutputStream(outpath));
//open the document for writing
                        doc.open();
//add paragraph to the document
                        doc.add(new Paragraph(name1));

                        doc.add(new Paragraph(age1));

                        doc.add(new Paragraph(temperature));

                       // if(Float.valueOf(temperature)>37.2)
                            doc.add(new Paragraph("High Fever"));
                        //if(Float.valueOf(temperature)<36.1)
                            doc.add(new Paragraph("low temperature"));
                        //if(Float.valueOf(temperature)<37.2 && Float.valueOf(temperature)>36.1)
                            doc.add(new Paragraph("normal temperature"));

                        doc.add(new Paragraph(lung));

                        doc.add(new Paragraph(bp1+"   "+bp2));

                        doc.add(new Paragraph(heart));

                        doc.add(new Paragraph(height));

                        doc.add(new Paragraph(weight));


//close the document
                        doc.close();

                    } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (DocumentException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    Toast.makeText(getActivity(),"reprot generated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mbpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="bp";
                    String message = "bp";
                    sendMessage(message);
                }
            }
        });

        mheartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    flag="heart";
                    String message = "heart";
                    sendMessage(message);
                }
            }
        });

        mPrintButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    Toast.makeText(getActivity(), "print", Toast.LENGTH_SHORT).show();



                    mCursor.moveToFirst();

//                    Toast.makeText(getActivity(), height, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), weight, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), temperature, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), lung, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "column"+mCursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_HEIGHT), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "column"+mCursor.getColumnName(2), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "column"+mCursor.getColumnName(3), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "column"+mCursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_LUNG), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(),"row"+ mCursor.getCount(), Toast.LENGTH_SHORT).show();


                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                                user user = userSnapshot.getValue(user.class);
                                if(name1.equals(user.getName())) {
                                    mConversationArrayAdapter.add("name: " + user.getName());
                                    mConversationArrayAdapter.add("height: " + user.getHeight());
                                    mConversationArrayAdapter.add("weight: " + user.getWeight());
                                    mConversationArrayAdapter.add("temperature: " + user.getTemperature());
                                    mConversationArrayAdapter.add("lung: " + user.getLung());
                                    mConversationArrayAdapter.add("bp: " + user.getBp());
                                    mConversationArrayAdapter.add("heart: " + user.getHeart());
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                  /*  int i=0;
                    int j= mCursor.getCount();
                    for(i=0;i<j;i++) {
                        if(name1.equals(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME))) {
                            mConversationArrayAdapter.add("name: " + name1);

                            String hname = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_HEIGHT));

                            mConversationArrayAdapter.add("height: " + hname);

                            String wname = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT));

                            mConversationArrayAdapter.add("weight: " + wname);

                            String tname = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TEMPERATURE));

                            mConversationArrayAdapter.add("temperature: " + tname);

                            String lname = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_LUNG));

                            mConversationArrayAdapter.add("lung: " + lname);

                        }
                        mCursor.moveToNext();
                    }*/
                }
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                Toast.makeText(getActivity(), height, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), weight, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), temperature, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), avg_speed+"  "+lung, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), bp1+"  "+bp2, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), heart, Toast.LENGTH_SHORT).show();



                if (null != view) {
                   // Toast.makeText(getActivity(), "save", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "save"+name1, Toast.LENGTH_SHORT).show();
                    user user = new user(name1,height,weight,temperature,lung,bp1+"  "+bp2,heart);
                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(user);
                        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,name1);

                        name1="";
                    SharedPreferences one = getContext().getSharedPreferences("one", MODE_PRIVATE);
                    SharedPreferences.Editor edit = one.edit();
                    edit.putString("name", name1);

                    edit.commit();


                        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HEIGHT,height);

                        height="";

                        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT,weight);

                        weight="";


                        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TEMPERATURE,temperature);

                        temperature="";


                        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_LUNG,avg_speed+"  "+lung);

                        lung="";
                        avg_speed="";
                        max_speed="";

                    cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BP,bp1+"  "+bp2);

                    bp1="";
                    bp2="";

                    cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HEART,heart);

                    heart="";

                    newRowId = mdb.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, cv);
                }
            }
        });


        // Initialize the BluetoothService to perform bluetooth connections
        mChatService = new BluetoothService(getActivity(), mHandler);

        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");
    }

    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }


    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(getActivity(), R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothService to write
            byte[] send = message.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
//            mOutEditText.setText(mOutStringBuffer);

        }
    }

    /**
     * The action listener for the EditText widget, to listen for the return key
     */
    private TextView.OnEditorActionListener mWriteListener
            = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
                String message = view.getText().toString();
                sendMessage(message);
            }
            return true;
        }
    };

    /**
     * Updates the status on the action bar.
     *
     * @param resId a string resource ID
     */
    private void setStatus(int resId) {
        FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(resId);
    }

    /**
     * Updates the status on the action bar.
     *
     * @param subTitle status
     */
    private void setStatus(CharSequence subTitle) {
        FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(subTitle);
    }

    /**
     * The Handler that gets information back from the BluetoothService
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = getActivity();
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
                            mConversationArrayAdapter.clear();
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    if(flag.equals("height")){
                        height=readMessage;
                        Toast.makeText(activity, height, Toast.LENGTH_SHORT).show();
                        flag="";
                    }
                    if(flag.equals("weight")){
                        weight=readMessage;
                        Toast.makeText(activity, weight, Toast.LENGTH_SHORT).show();
                        flag="";
                    }
                    if(flag.equals("temperature")){
                        temperature=readMessage;
                        Toast.makeText(activity, temperature, Toast.LENGTH_SHORT).show();
                        flag="";
                    }
                    if(flag.equals("lung")){
                        if(check==0) {
                            avg_speed = readMessage;
                            check++;
                            Toast.makeText(activity, avg_speed, Toast.LENGTH_SHORT).show();
                        }

                        if(check==1){
                            lung = readMessage;
                            check=0;
                            flag="";
                            Toast.makeText(activity, lung, Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(flag.equals("bp")){
                        if(check==0) {
                            bp1 = readMessage;
                            check++;
                            Toast.makeText(activity, bp1, Toast.LENGTH_SHORT).show();
                        }
                        if(check==1){
                            bp2 = readMessage;
                            check++;

                            Toast.makeText(activity, bp2, Toast.LENGTH_SHORT).show();
                        }
                        if(check==2){
                            heart = readMessage;
                            check=0;
                            flag="";
                            Toast.makeText(activity, heart, Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(flag.equals("heart")) {
                        if (check == 0) {
                            bp1 = readMessage;
                            check++;
                            Toast.makeText(activity, bp1, Toast.LENGTH_SHORT).show();
                        }
                        if (check == 1) {
                            bp2 = readMessage;
                            check++;

                            Toast.makeText(activity, bp2, Toast.LENGTH_SHORT).show();
                        }
                        if (check == 2) {
                            heart = readMessage;
                            check = 0;
                            flag = "";
                            Toast.makeText(activity, bp2, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {

                        setupChat();

                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(getActivity(), R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
        }
    }

    /**
     * Establish connection with other device
     *
     * @param data   An {@link Intent} with {@link DeviceListActivity#EXTRA_DEVICE_ADDRESS} extra.
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bluetooth_chat, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.secure_connect_scan: {
                // Launch the DeviceListActivity to see devices and do scan
                Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                return true;
            }
            case R.id.insecure_connect_scan: {
                // Launch the DeviceListActivity to see devices and do scan
                Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
                return true;
            }
            case R.id.discoverable: {
                // Ensure this device is discoverable by others
                ensureDiscoverable();
                return true;
            }
        }
        return false;
    }

    private Cursor get(){
        return mdb.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                FeedReaderContract.FeedEntry._ID
        );
    }

    public int getItemCount(){
        return mCursor.getCount();
    }


}
