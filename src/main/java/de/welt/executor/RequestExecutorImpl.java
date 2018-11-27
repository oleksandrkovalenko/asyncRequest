package de.welt.executor;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class RequestExecutorImpl implements RequestExecutor {

    private static final int N_THREADS = 10;

    private ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

    public <T> Future<T> submit(Callable<T> userCallable) {
        return executor.submit(userCallable);
    }
}
