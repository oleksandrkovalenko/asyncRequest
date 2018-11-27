package de.welt.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface RequestExecutor {

    <T> Future<T> submit(Callable<T> callable);

}
