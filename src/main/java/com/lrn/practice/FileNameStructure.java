package com.lrn.practice;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class FileNameStructure {

    private static final Logger logger = LoggerFactory.getLogger(FileNameStructure.class);

    public void renameFiles(String directory) {
        File dir = new File(directory);
        if (dir.isDirectory() & dir.canRead() & dir.canWrite()) {
            String[] fileNames = dir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("Q") && name.endsWith(".java");
                }
            });
            Arrays.sort(fileNames);
            StringBuilder sbr = new StringBuilder();
            Arrays.stream(fileNames).distinct().forEach(fileName -> {
                sbr.setLength(0);
                for (int idx = 1; idx < fileName.length(); idx++) {
                    char ch = fileName.charAt(idx);
                    if (ch >= '0' && ch <= '9') {
                        sbr.append(ch);
                    } else {
                        break;
                    }
                }
                String num = sbr.toString();
                sbr.setLength(0);
                sbr.append(fileName);
                if (!num.startsWith("0") && NumberUtils.isParsable(num) && Integer.parseInt(num) <= 99) {
                    //logger.info("Selected file: " + fileName);
                    sbr.insert(1,"0");
                    String sourceFle = directory+"\\"+fileName;
                    String destFile = directory+"\\"+sbr.toString();
                   logger.info("Source file: {} \n Dest File: {}", sourceFle, destFile);
                    try {
                        FileUtils.copyFile(new File(sourceFle), new File(destFile));
                    } catch (IOException e) {
                       logger.error("Exception occurred while copying file : " + fileName + " : " + e.getMessage(), e);
                    }
                }
            });
        }
    }


    public static void main(String[] args) {
        FileNameStructure fileNameStructure = new FileNameStructure();
        fileNameStructure.renameFiles("D:\\work\\learnanddev\\code\\github_repo\\algo_practice\\src\\main\\java\\com\\lrn\\leetcode\\google");
    }


}
