package com.extraTask.wordCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class WordCounter {
    private File file;
    private Scanner scanner;
    private Map<String, AtomicLong> wordCounts = new ConcurrentHashMap<>();

    public WordCounter(String fileName){
        this.file = new File(ClassLoader.getSystemResource(fileName).getFile());
        setScanner();
    }

    private void setScanner(){
        try {
            this.scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, AtomicLong> getWordCounts() {
        return wordCounts;
    }

    public void countWords(){
        String currentString;
        while(this.scanner.hasNext()){
            currentString = convertString(this.scanner.next());
            this.addToCount(currentString);
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

    private String convertString(String string){
        return string.toLowerCase().replaceAll("[^a-zA-Z]+", "");
    }
}
