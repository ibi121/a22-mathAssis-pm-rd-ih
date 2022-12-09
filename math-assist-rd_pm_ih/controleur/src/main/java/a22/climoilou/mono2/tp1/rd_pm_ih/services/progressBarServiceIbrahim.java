package a22.climoilou.mono2.tp1.rd_pm_ih.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class progressBarServiceIbrahim extends Service<Integer> {
    @Override
    protected Task<Integer> createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    updateProgress(i, 100);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
    }
}


