package com.extraTask.wordCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class WordCounter {
    private File file;
    private Scanner scanner;
    private Map<String, Counter> wordCounts = new HashMap<>();

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

    public Map<String, Counter> getWordCounts() {
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
            wordCounts.get(string).increment();
        } else {
            wordCounts.put(string, new Counter());
        }
    }

    private String convertString(String string){
        return string.toLowerCase().replaceAll("[^a-zA-Z]+", "");
    }
}
