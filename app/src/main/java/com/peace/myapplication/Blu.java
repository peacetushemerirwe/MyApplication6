package com.peace.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.Set;


import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Blu extends AppCompatActivity {
    private Set<BluetoothDevice>pairedDevices;
    ListView lv;
    ArrayAdapter aAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

        setContentView(R.layout.layout_main);
        Button on;
        on = (Button)findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button off = (Button)findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bAdapter.disable();
                Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
            }
        });
        Button OFF =(Button)findViewById(R.id.off1);
        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                getVisible.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 100);
                startActivityForResult(getVisible, 0);
                recreate();

            }
        });
        lv = (ListView)findViewById(R.id.listView);
        Button ON =(Button)findViewById(R.id.on1);
        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDevices = bAdapter.getBondedDevices();
                lv = (ListView)findViewById(R.id.listView);
                ArrayList list = new ArrayList();

                for (BluetoothDevice device : pairedDevices) list.add(device.getName());
                Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

                aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);

                lv.setAdapter(aAdapter);

            }
        });
        Button paybtn;
        paybtn=findViewById(R.id.paybtn);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("com.peace.myapplication.CUSTOM_INTENT");
                sendBroadcast(intent);
            }
        });



    }
    // Create a BroadcastReceiver for ACTION_FOUND.
    //final List<String> devices=new ArrayList<String>();
    // private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    //{
    // public void onItemClick (AdapterView av, View v, int arg2, long arg3)
    //{
    // Get the device MAC address, the last 17 chars in the View
    // String info = ((TextView) v).getText().toString();
    // String address = info.substring(info.length() - 17);
    // Make an intent to start next activity.
    // Intent i = new Intent(Blu.this, Blu.class);
    //Change the activity.
    // i.putExtra(Intent.EXTRA_COMPONENT_NAME, address); //this will be received at ledControl (class) Activity
    // startActivity(i);
    //}
    // };

    // private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    //public void onReceive(Context context, Intent intent) {
    // String action = intent.getAction();
    //if (BluetoothDevice.ACTION_FOUND.equals(action)) {
    // Discovery has found a device. Get the BluetoothDevice
    // object and its info from the Intent.
    // BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    // String deviceName = device.getName();
    //String deviceHardwareAddress = device.getAddress(); // MAC address
    // }
    // }
    //};
    // @Override
    //protected void onDestroy() {
    //super.onDestroy();

    // unregister the ACTION_FOUND receiver.
    // unregisterReceiver(mReceiver);
    //}

}



