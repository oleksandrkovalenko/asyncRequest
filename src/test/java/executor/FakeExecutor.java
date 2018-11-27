package executor;

import de.welt.executor.RequestExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FakeExecutor implements RequestExecutor {

    @Override
    public <T> Future<T> submit(Callable<T> callable) {
        return null;
    }

}
