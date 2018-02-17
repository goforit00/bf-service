package com.goforit.firstapple.service;


import java.util.Arrays;

/**
 * Created by goforit on 17/5/25.
 */
public class FirstAppleServiceTest {

    public static void main(String argv[]){
        System.err.print(Arrays.asList("1","2").stream().anyMatch(d->d.equals("1")));
    }
}
