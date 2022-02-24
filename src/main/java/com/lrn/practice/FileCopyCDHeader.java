package com.lrn.practice;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileCopyCDHeader {
    String sourceFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/exampledoc.pdf";
    File sourceFile = new File(sourceFilePath);
    public void asciiPrintable() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/ascii-printable/";

        List<String> filename = Arrays.asList(
                "example"+(char)0x5C+"doc.pdf",
                "example"+(char)0x2F+"doc.pdf",
                "example"+(char)0x40+"doc.pdf",
                "example"+(char)0x7E+"doc.pdf",
                "example"+(char)0x3C+"doc.pdf",
                "example"+(char)0x3F+"doc.pdf",
                "example"+(char)0x20+"doc.pdf",
                "example"+(char)0x2B+"doc.pdf",
                "example"+(char)0x2A+"doc.pdf",
                "example"+(char)0x24+"doc.pdf",
                "example"+(char)0x23+"doc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void asciiNonPrintable() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/ascii-nonprintable/";
        List<String> filename = Arrays.asList(
                "example"+(char)0x7F+"doc.pdf",
                "example"+(char)0x25+"doc.pdf",
                "example"+(char)0x22+"doc.pdf",
                "example"+(char)0xD+"filename.pdf",
                "example"+(char)0xC+"filename.pdf",
                "example"+(char)0x10+"filename.pdf",
                "example"+(char)0x7+"filename.pdf",
                "example"+(char)0x1+"filename.pdf",
                "example"+(char)0x0+"filename.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void chineseChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/chinese/";
        List<String> filename = Arrays.asList(
                 "快乐doc.pdf",
                 "不错doc.pdf",
                 "精细doc.pdf",
                 "工作doc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void japaneseChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/japanese/";
        List<String> filename = Arrays.asList(
                 "ハッピーdoc.pdf",
                 "いいdoc.pdf",
                 "罰金doc.pdf",
                 "作業doc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void koreanChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/korean/";
        List<String> filename = Arrays.asList(
                 "행복doc.pdf",
                 "좋은doc.pdf",
                 "좋아doc.pdf",
                 "작업doc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void russianChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/russian/";
        List<String> filename = Arrays.asList(
                 "счастливыйdoc.pdf",
                 "тяжелая работаdoc.pdf",
                 "самодисциплинаdoc.pdf",
                 "все еще в умеdoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void hindiChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/hindi/";
        List<String> filename = Arrays.asList(
                 "खुशdoc.pdf",
                 "कठोर परिश्रमdoc.pdf",
                 "сअभी भी मनdoc.pdf",
                 "आत्म अनुशासनdoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void thaiChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/thai/";
        List<String> filename = Arrays.asList(
                 "มีความสุขdoc.pdf",
                 "การทำงานอย่างหนักdoc.pdf",
                 "сยังใจdoc.pdf",
                 "วินัยในตนเองdoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void serbianChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/serbian/";
        List<String> filename = Arrays.asList(
                 "самодисциплинаdoc.pdf",
                 "срећанdoc.pdf",
                 "сјош увек умdoc.pdf",
                 "напоран радdoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void germanChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/german/";
        List<String> filename = Arrays.asList(
                 "glücklichdoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void frenchChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/french/";
        List<String> filename = Arrays.asList(
                 "agréabledoc.pdf"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void longLineChars() {
        String destinationFilePath = "/Users/saurabh.saxena/iwork/jirastorydocs/NT-110474/longheader/";
        List<String> filename = Arrays.asList(
                 "Merging can be performed automatically && once the requested changes are addressed.html"
        );
        filename.forEach(fileName -> {
            try {
                FileUtils.copyFile(sourceFile, new File(destinationFilePath+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public static void main(String[] args) {
        FileCopyCDHeader sol = new FileCopyCDHeader();
//        sol.asciiPrintable();
//        sol.serbianChars();
//        sol.thaiChars();
//        sol.hindiChars();
//        sol.longLineChars();
//        sol.frenchChars();
//        sol.germanChars();
//        sol.germanChars();
//        sol.russianChars();
//        sol.koreanChars();
//        sol.koreanChars();
          sol.chineseChars();
          sol.japaneseChars();
    }
}
