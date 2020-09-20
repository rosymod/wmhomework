package com.wemakeprice.homework.kst.service;

import com.wemakeprice.homework.kst.bean.AnalysisBean;
import com.wemakeprice.homework.kst.util.WordsComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Service
public class AnalysisSvc {

    @Autowired
    private WordsComparator wordsComparator;

    // a b c d e / f g h i j / k l m n o / p q r s t / u v w x y z
    private final List<String> range = new ArrayList<String>(){{
        add("[^a-eA-E]");
        add("[^f-jF-J]");
        add("[^k-oK-O]");
        add("[^p-tP-T]");
        add("[^u-zU-Z]");
    }};

    public void work(AnalysisBean analysis){

        try (InputStream is = new URL(analysis.getUrl()).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))){

            StringBuilder wordsb = new StringBuilder();
            StringBuilder numsb = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null){
                if("1".equals(analysis.getType())){ // remove html tag
                    line = line.replaceAll("\\<.*?>","");
                }
                wordsb.append(line.replaceAll("[^a-zA-Z]","").trim()); // word
                numsb.append(line.replaceAll("[^0-9]","").trim()); // number
            }

            // sort word
            String temp = wordsb.toString();
            String[] tempArr;
            wordsb.setLength(0);
            for(String target : range){
                tempArr = temp.replaceAll(target,"").split("");
                Arrays.sort(tempArr,wordsComparator);
                wordsb.append(String.join("",tempArr));
            }

            // sort number
            temp = numsb.toString();
            numsb.setLength(0);
            tempArr = temp.split("");
            Arrays.sort(tempArr);
            numsb.append(String.join("",tempArr));

            // mix
            StringBuilder resultsb = new StringBuilder();
            int length = wordsb.length() < numsb.length() ? wordsb.length() : numsb.length();
            for(int index = 0; index < length; index++){
                resultsb.append(wordsb.charAt(index));
                resultsb.append(numsb.charAt(index));
            }
            if(wordsb.length() > length){
                resultsb.append(wordsb.substring(length-1,wordsb.length()));
            }else if(numsb.length() > length){
                resultsb.append(numsb.substring(length-1,numsb.length()));
            }

            // left
            int leftLength = resultsb.length();
            if(analysis.getUnit() > 0){
                leftLength -= (resultsb.length() % analysis.getUnit());
            }
            analysis.setValue(resultsb.substring(0,leftLength));
            analysis.setLeft(resultsb.substring(leftLength,resultsb.length()));

            System.out.println(analysis.getValue().length());
            System.out.println(analysis.getLeft().length());
        } catch (IOException e) {
            analysis.setErrMsg(e.getMessage());
        } finally {

        }
    }
}
