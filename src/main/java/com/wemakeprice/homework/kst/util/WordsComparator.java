package com.wemakeprice.homework.kst.util;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class WordsComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if(o1.compareToIgnoreCase(o2) == 0 && !o2.equals(o1)){
            if(o2.toUpperCase().equals(o1)){
                return -1;
            }else{
                return 1;
            }
        }else{
            return o1.compareToIgnoreCase(o2);
        }
    }
}
