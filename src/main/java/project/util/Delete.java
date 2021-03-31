package project.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Delete {

    public void DeleteRest() throws IOException {
        URL url = new URL("http://localhost:8080/api/theater/clients");

        URLConnection urlConnection = url.openConnection(); // предоставляем доступ к атрибутам url

        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream()); //получение данных из  источника
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
        System.out.println("delete delete" + response);
    }

}
