package a22.climoilou.mono2.tp1.rd_pm_ih.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;


public class TempsPasseDansAppService extends Service<Integer> {

    private int lastValue = 0;
    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {

                while (!Thread.interrupted()){
                    updateValue(lastValue + 1);
                    SlowHelper.slow(1000);
                }

                return 0;
            }
        };
    }

    public void setLastValue(int lastValue) {
        this.lastValue = lastValue;
    }
}
