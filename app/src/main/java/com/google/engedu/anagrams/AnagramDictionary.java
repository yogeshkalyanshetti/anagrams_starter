/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    int wordLength=DEFAULT_WORD_LENGTH;
    HashSet<String> wordSet=new HashSet<String>();
    HashMap<String,ArrayList<String>> lettersToWords=new HashMap<String, ArrayList<String>>();
    HashMap<Integer,ArrayList<String>> sizeToWords = new HashMap<Integer, ArrayList<String>>();
    ArrayList<String> wordList=new ArrayList<String>();
//    ArrayList<String> wordsSizeList=new ArrayList<String>();

    ArrayList<String> valuesTemp=new ArrayList<String>();
    ArrayList<String> valuesTempMap=new ArrayList<String>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            String keyWordSort = sortLetters(word);
            wordSet.add(word);
            //if(lettersToWords.isEmpty())
            //{
                // valuesTemp=new ArrayList<String>();
            //sizeToWords.put(word.length(),word);
            wordList.add(word);
            //wordsSizeList.add(word);
               // lettersToWords.put(keyWordSort,wordList);
            //}

            //else {
                if (lettersToWords.containsKey(keyWordSort)) {

                    valuesTemp = lettersToWords.get(keyWordSort);
                    //valuesTempMap = sizeToWords.get(word.length());

                    //valuesTemp.add(word);
                   // lettersToWords.put(keyWordSort, valuesTemp);
                }
                else
                {
                   // wordList.clear();
                    //wordList.add(word);
                    valuesTemp=new ArrayList<String>();
                  //  valuesTempMap=new ArrayList<String>();

                    //lettersToWords.put(keyWordSort,wordList);
                }
                valuesTemp.add(word);
                if (sizeToWords.containsKey(word.length()))
                {
                    valuesTempMap = sizeToWords.get(word.length());

                }
                else{
                    valuesTempMap=new ArrayList<String>();

                }
                valuesTempMap.add(word);
            lettersToWords.put(keyWordSort, valuesTemp);
            sizeToWords.put(word.length(),valuesTempMap);
//            }
        }
    }

    String sortLetters(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sortedString = new String(chars);
        return sortedString;

        /*for (int i = 0; i <s.length() ; i++) {
            for (int j = 1; j <s.length() ; j++) {
                if(chars[i]>chars[j]){
                    char temp=chars[i];
                    chars[i]=chars[j];
                    chars[j]=temp;
                }
            }

        }*/


    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word)) {
            if(word.contains(base)){
                return false;
            }

            return true;
        }
        return false;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String sortTargetWord = sortLetters(targetWord);

        /*
        Iterator itr=wordList.iterator();

        while(itr.hasNext()){
                String s=new String(String.valueOf(itr.next()));
            if(s.length() == sortTargetWord.length()){
                String sortedArrayWord =sortLetters(s);
                if(sortedArrayWord.equalsIgnoreCase(sortTargetWord)){
                    result.add(s);
                }
            }
        }
        */
        /*
        //for(Map.Entry<String, ArrayList<String>> map:lettersToWords.entrySet()) {
          //  ArrayList<String> val = map.getValue();
           // String sortW = map.getKey();

           // String s= new String(val);
            if((sortW.equalsIgnoreCase(sortTargetWord)))
            {
                Iterator itr=val.iterator();
                while(itr.hasNext()) {
                    String s = new String(String.valueOf(itr.next()));
                    result.add(s);
                }
            }


        }
       */

        if (lettersToWords.containsKey(sortTargetWord))
        {
            ArrayList<String> tempList = lettersToWords.get(sortTargetWord);
            result.addAll(tempList);

        }
        result.add(targetWord);
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> tempList=new ArrayList<String>();
        String keyWordSort = sortLetters(word);
        ArrayList<String> chars= new ArrayList<String>();

        if(lettersToWords.containsKey(keyWordSort)){
            tempList=lettersToWords.get(keyWordSort);
            result.addAll(tempList);
            for(char ch='a';ch<'z';ch++)
            {
                String newKeyWord=keyWordSort + ch;
                String newKeyWordSort=sortLetters(newKeyWord);
                if(lettersToWords.containsKey(newKeyWordSort)){
                    ArrayList<String> tempListNew=new ArrayList<String>();
                    tempListNew = lettersToWords.get(newKeyWordSort);
                    result.addAll(tempListNew);
                    String newWord=word+ch;
                    String newWord2=ch+word;
                    if (result.contains(newWord))
                        result.remove(newWord);
                    if (result.contains(newWord2))
                        result.remove(newWord2);
                }
            }

        }
        result.remove(word);

        return result;
    }

    public List<String> getAnagramsWithTwoMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> tempList=new ArrayList<String>();
        String keyWordSort = sortLetters(word);
        ArrayList<String> chars= new ArrayList<String>();

        if(lettersToWords.containsKey(keyWordSort)){
            tempList=lettersToWords.get(keyWordSort);
            result.addAll(tempList);
            for(char ch='a';ch<'z';ch++)
            {
                String newKeyWord=keyWordSort + ch;
                String newKeyWordSort=sortLetters(newKeyWord);
                if(lettersToWords.containsKey(newKeyWordSort)){
                    ArrayList<String> tempListNew=new ArrayList<String>();
                    tempListNew = lettersToWords.get(newKeyWordSort);
                    result.addAll(tempListNew);
                }
            }

        }


        return result;
    }

    public String pickGoodStarterWord() {
        int flag=1;
        //static int wordLength=DEFAULT_WORD_LENGTH;
        String s=new String();

        while(flag==1) {
            ArrayList<String> words= sizeToWords.get(wordLength);
            int n = random.nextInt(words.size());
            s = words.get(n);
            if(s!=null) {

                if(wordLength<=MAX_WORD_LENGTH)
                wordLength++;
                flag = 0;
            }
        }
        return s;
    }
}