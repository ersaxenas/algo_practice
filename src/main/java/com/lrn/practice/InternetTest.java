package com.lrn.practice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class InternetTest {

    List<String> webList;
    Random random = new Random();
    String webFileList = "weblist.txt";

    public InternetTest() throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(webFileList);
        if (resourceAsStream == null) {
            throw new IllegalStateException("File " + webFileList + " not found. ");
        }
        webList = IOUtils.readLines(resourceAsStream, StandardCharsets.UTF_8);
    }

    public void testInternet() throws UnirestException {
        String web = webList.get(random.nextInt(webList.size()));
       // System.out.println("trying : " + web);
        HttpResponse<String> jsonNodeHttpResponse = Unirest.head(web).asString();
        if (jsonNodeHttpResponse.getStatus() != 200) {
            System.out.println("failed to reach " + web + " : " + jsonNodeHttpResponse.getStatus());
            //throw new IllegalStateException("Failed to reach web" + web);
        } else {
            //System.out.println("Reached " + web);
        }
    }


    public static void main(String[] args) throws Exception {
        InternetTest internetTest = new InternetTest();
        HttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build();
        Unirest.setHttpClient(httpClient);
        while (true) {
            internetTest.testInternet();
            Thread.sleep(1 * 1000);
        }
    }


}
