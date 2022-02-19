package com.lrn.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileCopyUtil {

    public void start() throws FileNotFoundException {
        File logFile = new File("/Users/saurabh.saxena/iwork/logs/20201205-open-close.log");
        Set<String> openStream = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            final String line = reader.readLine();
            if(line != null && line.contains("open_stream")) {
                openStream.add(line.substring(line.indexOf("SWIFT")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            final String line = reader.readLine();
            if(line != null && line.contains("close_stream")) {
                openStream.remove(line.substring(line.indexOf("SWIFT")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        openStream.forEach(System.out::println);
    }

    public static void main(String[] args) {
        FileCopyUtil fileCopyUtil = new FileCopyUtil();
        try {
            fileCopyUtil.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
