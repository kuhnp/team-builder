package com.orange.sc.soundcloudrand;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.Request;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ActionBarActivity {



    public ApiWrapper apiWrapper;
    public Request resource;
    public HttpResponse response;
    JSONArray jsonresp;
    ArrayList<String> trackIdList;
    Button button;


    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            if(toast==null)
                Log.d("Toast","null");
            else
                Log.d("Toast", toast);

            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public String getString(){
            return "hello";
        }
    }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         final WebView browser = (WebView) findViewById(R.id.web);
         browser.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 return (event.getAction() == MotionEvent.ACTION_MOVE);
             }
         });
         browser.getSettings().setJavaScriptEnabled(true);
         browser.getSettings().setAllowFileAccessFromFileURLs(true);
         browser.getSettings().setAllowUniversalAccessFromFileURLs(true);
         browser.addJavascriptInterface(new WebAppInterface(this), "Android");
         browser.setWebViewClient(new WebViewClient(){
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 if(url.startsWith("file://api")){
                     url.replace("file","http");
                     return true;
                 }
                 return false;
             }
         });
         browser.loadUrl("file:///android_asset/Google.html");

         button = (Button) findViewById(R.id.button);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Random rand = new Random();
                 int pos = rand.nextInt(trackIdList.size());
                 String idTmp = trackIdList.get(pos);
                 browser.loadUrl("javascript:reloadSoundcloudWidget('"+idTmp+"')");
             }
         });


         GetFromServer task = new GetFromServer();
         task.execute();









     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class GetFromServer extends AsyncTask{


        @Override
        protected Object doInBackground(Object[] params) {


            apiWrapper = new ApiWrapper("e13ec08133562b63b1bea39f69c7af9f","af0e8084ed05b785265aa57303d26713",null,null);

            resource = new Request("/tracks?tags=rock");
            try {
                response = apiWrapper.get(resource);
                jsonresp = SoundCloudRandUtil.parseAnswerArray(response);
                trackIdList = SoundCloudRandUtil.getTrackIdListFromResponse(jsonresp);

//
//                int a = jsonresp.length();
//                trackIdList.add(0,"salut");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }




            return null;
        }
    }




}
