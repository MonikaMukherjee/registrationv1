package com.api;

import java.util.function.Function;

public class D {
    //Function functional interface,it's not filtered the record,its takes input and produced an output
    public static void main(String[]args){
        Function<String,Integer> x= s->s.length();
        //string is present giving input to it
        //Integer is present output of this
        //now supply the method
        Integer val= x.apply("mike");
        System.out.println("val");
    }

}
