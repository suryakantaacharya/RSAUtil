package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainThread {

    private Thread keyGenerationThread;


    public void startSession(){
        keyGenerationThread.start();
        keyGenerationThread.interrupt();

    }
}
