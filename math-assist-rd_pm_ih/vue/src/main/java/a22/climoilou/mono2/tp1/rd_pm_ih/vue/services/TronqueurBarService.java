package a22.climoilou.mono2.tp1.rd_pm_ih.vue.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TronqueurBarService extends Service<Void> {
    private boolean onGoing;

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (onGoing && !isCancelled()) {
                    updateProgress(1, 1);
                    if (isCancelled()) {
                        break;
                    }
                }
                return null;
            }
        };
    }
}
