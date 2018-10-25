package com.extraTask.wordCount;

import org.junit.Assert;
import org.junit.Test;

public class WordCounterTestSuite {
    @Test
    public void testInit() {
        try {
            WordCounter wordCounter = new WordCounter("wrongName.txt");
        } catch (NullPointerException e) {
            Assert.assertEquals("java.lang.NullPointerException", e.toString());
        }
    }

    @Test
    public void testSimpleWords() {
        WordCounter wordCounter = new WordCounter("testFile.txt");

        Assert.assertEquals(5, wordCounter.getWordCounts().entrySet().size());
        Assert.assertEquals(2, wordCounter.getWordCounts().get("ma").get());
    }

    @Test
    public void testLoremIpsum1() {
        WordCounter wordCounter = new WordCounter("loremIpsum1.txt");

        long wordsTotal = wordCounter.getWordCounts().entrySet().stream().mapToLong(n -> n.getValue().get()).sum();
        Assert.assertEquals(69, wordsTotal);
    }

    @Test
    public void testWhatever() {
        WordCounter wordCounter = new WordCounter("whatever.txt");

        long wordsTotal = wordCounter.getWordCounts().entrySet().stream().mapToLong(n -> n.getValue().get()).sum();
        long countOfXYZ = wordCounter.getWordCounts().get("xyz").get();
        Assert.assertEquals(8, wordsTotal);
        Assert.assertEquals(4, countOfXYZ);
    }

    @Test
    public void testBook1(){
        WordCounter wordCounter = new WordCounter("book1.txt");

    }
}

