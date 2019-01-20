package br.com.philippesis.testrest.security;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Security {

    public String authToken(String username, String password) {

        int HTTP_COD_SUCESSO = 200;
        String result = null;
        String target = "Bearer ";

        try {

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"username\":\""+username+"\",\"password\":\"" +
                    password+"\"}");
            Request request = new Request.Builder()
                    .url("http://192.168.56.1:8081/rest-ada/login")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("TestRest-Token", "ad2771a0-30ff-49e1-ace6-c9f4a8e99328")
                    .build();

            Response response = client.newCall(request).execute();

            if(response.code() == HTTP_COD_SUCESSO) {

                String value = response.headers().get("Authorization");

                if(value != null && !value.isEmpty()) {

                    int index = value.indexOf(target);
                    int subIndex = index + target.length();

                    result = value.substring(subIndex);

                }

            }

        } catch (Exception er) {
            Log.e("Error: ", er.toString());
        }

        return result;
    }

}
