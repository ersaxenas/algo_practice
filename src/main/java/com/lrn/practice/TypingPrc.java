package com.lrn.practice;

import java.util.Random;
import java.util.Scanner;

public class TypingPrc {

    public void start(char[] keys) {
        Random random = new Random();
        Scanner consol = new Scanner(System.in);
        for(int idx=0; idx<100; idx++) {
            char curc = keys[random.nextInt(keys.length)];
            System.out.println(curc + " > ");
            String inpt = consol.next();
            if(curc != inpt.charAt(0)){
                System.out.println("wrong input");
            }
        }
    }

    public static void main(String[] args) {
          char[] keys = new char[] {'z','x','c','v','q','e','r','y','o','p'};
           TypingPrc sol = new TypingPrc();
           sol.start(keys);
    }



}
