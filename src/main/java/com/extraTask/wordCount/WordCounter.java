package com.extraTask.wordCount;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class WordCounter {
    private File file;
    private Scanner scanner;
    private BufferedReader reader;
    private Map<String, AtomicLong> wordCounts = new ConcurrentHashMap<>();

    public WordCounter(String fileName){
        this.file = new File(ClassLoader.getSystemResource(fileName).getFile());
        try {
            countWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, AtomicLong> getWordCounts() {
        return wordCounts;
    }

    private void countWords() throws IOException{
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));

        for(String line; (line = this.reader.readLine()) != null;){
            for(final String word : line.split("[\\W]+")){
                if(word.length() > 0){
                    addToCount(word.toLowerCase());
                }
            }
        }
    }

    private void addToCount(String string){
        if(wordCounts.containsKey(string)){
            long currentValue = wordCounts.get(string).get();
            long newValue = currentValue + 1;
            while(true){
                if(wordCounts.get(string).compareAndSet(currentValue, newValue)){
                    return;
                }
            }
        } else {
            wordCounts.put(string, new AtomicLong(1));
        }
    }
}
