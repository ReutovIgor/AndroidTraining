package com.example.ruireutov.androidtrainingproject.DataStorage;

import android.util.Log;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerDataStorage implements IDataStorage {
    private String userKey;
    private URL url;
    public ServerDataStorage(String scheme, String authority, String port, String key){
        try {
            String path = "todos";
            url = new URL(String.format("%s://%s:%s/%s", scheme,authority,port, path));
            userKey = key;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<Task> getTaskList() {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("user", userKey);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                return gson.fromJson(stringBuilder.toString(),new TypeToken<List<Task>>(){}.getType());
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e("ServerDataStorage", "saveTaskList IOException: ", e);
        }

        return new ArrayList<>();
    }

    @Override
    public void saveTaskList(List<Task> taskList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String str = gson.toJson(taskList);


        HttpURLConnection urlConnection = null;
        OutputStream out = null;
        BufferedWriter writer = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("user", userKey);
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            out = new BufferedOutputStream(urlConnection.getOutputStream());
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(str);
            writer.flush();
            writer.close();
            out.close();
        } catch (ProtocolException e) {
            Log.e("ServerDataStorage", "saveTaskList ProtocolException: ", e);
        } catch (IOException e) {
            Log.e("ServerDataStorage", "saveTaskList IOException: ", e);
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
    }
}
