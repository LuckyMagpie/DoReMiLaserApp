package com.example.diego.musica;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MainActivity extends Activity  implements Button.OnClickListener{
    Button play;
    Button stop;
    Button record;
    Button tap;
    Button metro;

    Button instA;
    Button instB;
    Button instC;
    Button instD;
    Button instE;
    Button instF;
    Button instG;
    Button instH;

    Button eff1;
    Button eff2;
    Button eff3;
    Button eff4;


    EditText ip;
    EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);

        record = (Button) findViewById(R.id.record);
        record.setOnClickListener(this);

        tap = (Button) findViewById(R.id.tap);
        tap.setOnClickListener(this);

        metro = (Button) findViewById(R.id.metro);
        metro.setOnClickListener(this);

        instA = (Button) findViewById(R.id.instA);
        instA.setOnClickListener(this);

        instB = (Button) findViewById(R.id.instB);
        instB.setOnClickListener(this);

        instC = (Button) findViewById(R.id.instC);
        instC.setOnClickListener(this);

        instD = (Button) findViewById(R.id.instD);
        instD.setOnClickListener(this);

        instE = (Button) findViewById(R.id.instE);
        instE.setOnClickListener(this);

        instF = (Button) findViewById(R.id.instF);
        instF.setOnClickListener(this);

        instG = (Button) findViewById(R.id.instG);
        instG.setOnClickListener(this);

        instH = (Button) findViewById(R.id.instH);
        instH.setOnClickListener(this);

        eff1 = (Button) findViewById(R.id.eff1);
        eff1.setOnClickListener(this);

        eff2 = (Button) findViewById(R.id.eff2);
        eff2.setOnClickListener(this);

        eff3 = (Button) findViewById(R.id.eff3);
        eff3.setOnClickListener(this);

        eff4 = (Button) findViewById(R.id.eff4);
        eff4.setOnClickListener(this);

        ip = (EditText) findViewById(R.id.ip);
        port = (EditText) findViewById(R.id.port);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        String name = view.getResources().getResourceEntryName(view.getId());
        if(name.equals("play")){
            new UDP().execute("1");
        }else if (name.equals("stop")){
            new UDP().execute("2");
        }else if(name.equals("record")){
            new UDP().execute("3");
        }else if(name.equals("tap")){
            new UDP().execute("4");
        }else if(name.equals("metro")){
            new UDP().execute("5");
        }else if(name.equals("instA")){
            new UDP().execute("6");
        }else if(name.equals("instB")){
            new UDP().execute("7");
        }else if(name.equals("instC")){
            new UDP().execute("8");
        }else if(name.equals("instD")){
            new UDP().execute("9");
        }else if(name.equals("instE")){
            new UDP().execute("0");
        }else if(name.equals("instF")){
            new UDP().execute(",");
        }else if(name.equals("instG")){
            new UDP().execute(".");
        }else if(name.equals("instH")){
            new UDP().execute("/");
        }else if(name.equals("eff1")){
            new UDP().execute("=");
        }else if(name.equals("eff2")){
            new UDP().execute("[");
        }else if(name.equals("eff3")){
            new UDP().execute("]");
        }else if(name.equals("eff4")){
            new UDP().execute("\\");
        }

    }

    private class UDP extends AsyncTask<String, Void, Boolean>{
        @Override
        protected Boolean doInBackground(String... message) {

                byte [] messageBytes = message[0].getBytes();
                try {
                        InetAddress addr = InetAddress.getByName(ip.getText().toString());
                        DatagramPacket packet = new DatagramPacket(messageBytes ,messageBytes.length, addr, Integer.parseInt(port.getText().toString()));
                        DatagramSocket socket = new DatagramSocket();
                        socket.send(packet);
                        socket.disconnect();
                        socket.close();
                        Log.e("UDP -->", message[0]);

                }catch (UnknownHostException e) {
                    Log.e("UnknownHostExceptionUDP", "");
                    return Boolean.FALSE;
                } catch (IOException e) {
                    Log.e("IOException UDP", "");
                    return Boolean.FALSE;
                }

            return Boolean.TRUE;
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                ip.setBackgroundColor(0xFF65FF5F);
                port.setBackgroundColor(0xFF65FF5F);
            }else{
                ip.setBackgroundColor(0xFFFF7367);
                port.setBackgroundColor(0xFFFF7367);
            }
        }
    }
}


