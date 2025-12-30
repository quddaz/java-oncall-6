package oncall.domain.work;

import java.util.List;
import oncall.dto.WorkDTO;

public class Worker {
    private final List<String> workers;
    private int index = 0;

    public Worker(List<String> workers) {
        this.workers = workers;
    }

    public void checkSwap(String name) {
        if (workers.get(index % workers.size()).equals(name)) {
            swap();
        }
    }

    public String process() {
        String worker = workers.get(index % workers.size());
        index++;

        return worker;
    }

    private void swap() {
        int cur = index % workers.size();
        int next = (index + 1) % workers.size();
        String temp = workers.get(cur);
        workers.set(cur, workers.get(next));
        workers.set(next, temp);
    }

    public static Worker selectWorker(boolean isHoliday, Worker weekWorker, Worker holidayWorker) {
        if (isHoliday) {
            return holidayWorker;
        }
        return weekWorker;
    }
}
