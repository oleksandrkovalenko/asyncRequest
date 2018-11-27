package de.welt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.welt.domain.MergedResponse;
import de.welt.domain.Post;
import de.welt.domain.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final String DOMAIN = "https://jsonplaceholder.typicode.com";
    private static final Long TIMEOUT = 1000L;

    private ObjectMapper mapper = new ObjectMapper();

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        String userId = "1";

        Main main = new Main();
        main.requestForUser(userId);

    }

    private void requestForUser(String userId) {
        try {
            requestForUserWithThrow(userId);
        } catch (IOException | InterruptedException | ExecutionException e) {
            //TODO: Add logging here
        } catch (TimeoutException e) {
            //TODO: Add logging here
        } catch (Exception e) {
            //TODO: Add logging here
        } finally {
            executor.shutdown();
        }
    }

    private void requestForUserWithThrow(String userId) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        Future<User> userFuture = executor.submit(createUserCallable(userId));
        Future<List<Post>> postFuture = executor.submit(createPostsCallable(userId));

        User user = userFuture.get(TIMEOUT, TimeUnit.MILLISECONDS);
        List<Post> posts = postFuture.get(TIMEOUT, TimeUnit.MILLISECONDS);

        MergedResponse mergedResponse = new MergedResponse(user, posts);
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedResponse);

        System.out.println(response);
    }

    private Callable<User> createUserCallable(String userId) {
        return () -> mapper.readValue(new URL(DOMAIN + "/users/" + userId), User.class);
    }

    private Callable<List<Post>> createPostsCallable(String userId) {
        return () -> mapper.readValue(new URL(DOMAIN + "/posts?userId=" + userId), new TypeReference<List<Post>>(){});
    }
}
