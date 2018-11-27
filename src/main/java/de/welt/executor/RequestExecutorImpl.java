package de.welt.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@Slf4j
public class RequestExecutorImpl implements RequestExecutor {

    private static final int N_THREADS = 10;

    private ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

    public <T> Future<T> submit(Callable<T> callable) {
        log.debug("Submitting request to executor");
        return executor.submit(callable);
    }
}
