package edu.uml.android.adventurersarchive.info;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.uml.android.adventurersarchive.R;

/**
 * Created by Darin on 11/10/2016.
 */
public class DiceRoller {
    public static Random rand = new Random();
    private static RequestQueue queue = null;
    private static ProgressDialog pDialog;
    public static Context context;
    public static final String TAG = DiceRoller.class.getSimpleName();


    public static void setContext(Context thecontext) {
        context = thecontext;
    }

    public static int roll(final int sides) { return roll(sides, 0, context); }

    public static int roll(final int sides, final int bonus) { return roll(sides, bonus, context); }

    public static int roll(final int sides, final int bonus, final Context thecontext) {
        //return (rand.nextInt(sides) + 1);
        if (null == context) {
            context = thecontext;
        }
        String url = " https://api.random.org/json-rpc/1/invoke";

        if (null == queue) {
            queue = Volley.newRequestQueue(thecontext);
        }

        if (null == pDialog) {
            pDialog = new ProgressDialog(context);
        }
        pDialog.setMessage("Rolling...");
        pDialog.show();

        String requestJson = String.format("{\n  \"jsonrpc\": \"2.0\"," +
                "  \"method\": \"generateIntegers\",\n  \"params\": {\n" +
                "    \"apiKey\": \"30e79cbf-de06-410f-ab71-6415b79324cb\",\n" +
                "    \"n\": 1,\n" +
                "    \"min\": 1,\n" +
                "    \"max\": %d,\n" +
                "    \"replacement\": true\n" +
                "  },\n" +
                "  \"id\": 18\n" +
                "}", sides);

        JSONObject reqbody = null;
        try {
            reqbody = new JSONObject(requestJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest( Request.Method.POST, url, reqbody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.hide();
                        // INSERT JSON PARSING HERE
                        Log.d(DiceRoller.TAG, response.toString());
                        int roll = 1;
                        try {
                            JSONObject result = response.getJSONObject("result");
                            JSONObject rand = result.getJSONObject("random");
                            JSONArray data = rand.getJSONArray("data");
                            roll = data.getInt(0);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(thecontext, "Roll: " + roll +((bonus < 0)?"":"+")+ bonus + " = " + (roll + bonus), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(DiceRoller.TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }

        })
        {

        };

        // Adding request to request queue
        queue.add(req);
        return 1;

    }
 /*
    public static int roll(int sides, int times) {
        int sum = 0;
        for(int i = 0; i < times; i++) {
            sum += DiceRoller.roll(sides);
        }
        return sum;
    }

    public static int [] getRolls(int sides, int times) {
        int [] rolls = new int[times];

        for(int i = 0; i < times; i++) {
            rolls[i] = DiceRoller.roll(sides);
        }

        return rolls;
    }
    */
}
