package com.extraTask.wordCount;

import java.util.concurrent.atomic.AtomicInteger;

public final class Counter{
    private AtomicInteger count;

    protected Counter(){
        this.count = new AtomicInteger(1);
    }

    protected void increment(){
        while(true){
            int existingValue = getCount();
            int newValue = existingValue + 1;
            if(count.compareAndSet(existingValue, newValue)){
                return;
            }
        }
    }

    public int getCount(){
        return count.get();
    }

    @Override
    public String toString(){
        return String.valueOf(count);
    }
}
