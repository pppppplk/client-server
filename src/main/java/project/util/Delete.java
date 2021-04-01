package project.util;

import project.spring.models.Ticket;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Delete {

    public void DeleteRest(String link) throws IOException {
        URL url = new URL(link);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestProperty("Content-Type", "utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestMethod("DELETE");
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream()); //получение данных из  источника
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //считывает текст

        String str = " ";

        // считываеи из url  построчно информацию и добавляем ее в строку,
        // затем возвращаем строку уже со всей информацией, которая находится в url
        while ((str = bufferedReader.readLine()) != null) {

            stringBuilder.append(str);

        }
        String response = stringBuilder.toString();
        bufferedReader.close();
    }

}
