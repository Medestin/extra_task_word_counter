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
        wordCounter.countWords();

        Assert.assertEquals(5, wordCounter.getWordCounts().entrySet().size());
        Assert.assertEquals(2, wordCounter.getWordCounts().get("ma").getCount());
    }

    @Test
    public void testLoremIpsum1() {
        WordCounter wordCounter = new WordCounter("loremIpsum1.txt");
        wordCounter.countWords();

        int wordsTotal = wordCounter.getWordCounts().entrySet().stream().mapToInt(n -> n.getValue().getCount()).sum();
        Assert.assertEquals(69, wordsTotal);
    }

    @Test
    public void testWhatever() {
        WordCounter wordCounter = new WordCounter("whatever.txt");
        wordCounter.countWords();

        int wordsTotal = wordCounter.getWordCounts().entrySet().stream().mapToInt(n -> n.getValue().getCount()).sum();
        int countOfXYZ = wordCounter.getWordCounts().get("xyz").getCount();
        Assert.assertEquals(8, wordsTotal);
        Assert.assertEquals(4, countOfXYZ);
    }
}

