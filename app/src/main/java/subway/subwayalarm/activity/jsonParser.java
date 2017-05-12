package subway.subwayalarm.activity;

import android.content.Context;


import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import subway.subwayalarm.R;



public class jsonParser{
    Context context;

    public jsonParser(Context context) {
        this.context = context;
    }

    public void getLineNum() {
        String json = null;
        InputStream is = context.getResources().openRawResource(R.raw.line_by_route_info);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = is.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = is.read();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        json = byteArrayOutputStream.toString();
        parseJson(json);
    }

    public void parseJson(String json) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            JSONArray array = (JSONArray) jsonObject.get("DATA");

            for (int i = 0;i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                object.get("");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
