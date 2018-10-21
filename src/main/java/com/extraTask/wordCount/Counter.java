package com.extraTask.wordCount;

public final class Counter{
    private int count;

    protected Counter(){
        this.count = 1;
    }

    protected void increment(){
        count++;
    }

    public int getCount(){
        return this.count;
    }

    @Override
    public String toString(){
        return String.valueOf(count);
    }
}
