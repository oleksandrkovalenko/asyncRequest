package de.welt.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.welt.domain.MergedResponse;
import de.welt.domain.Post;
import de.welt.domain.User;
import de.welt.executor.RequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

@Service
public class UserDataServiceImpl implements UserDataService {

    private static final String DOMAIN = "https://jsonplaceholder.typicode.com";
    private static final Long TIMEOUT = 1000L;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RequestExecutor requestExecutor;

    public MergedResponse requestForUser(String userId) {
        try {
            return requestForUserWithThrow(userId);
        } catch (InterruptedException | ExecutionException e) {
            //TODO: Add logging here
        } catch (TimeoutException e) {
            //TODO: Add logging here
        } catch (Exception e) {
            //TODO: Add logging here
        }
        //TODO Make response
        return null;
    }

    private MergedResponse requestForUserWithThrow(String userId) throws InterruptedException, ExecutionException, TimeoutException {
        Future<User> userFuture = requestExecutor.submit(createUserCallable(userId));
        Future<List<Post>> postFuture = requestExecutor.submit(createPostsCallable(userId));

        User user = userFuture.get(TIMEOUT, TimeUnit.MILLISECONDS);
        List<Post> posts = postFuture.get(TIMEOUT, TimeUnit.MILLISECONDS);

        return new MergedResponse(user, posts);
    }

    private Callable<User> createUserCallable(String userId) {
        return () -> mapper.readValue(new URL(DOMAIN + "/users/" + userId), User.class);
    }

    private Callable<List<Post>> createPostsCallable(String userId) {
        return () -> mapper.readValue(new URL(DOMAIN + "/posts?userId=" + userId), new TypeReference<List<Post>>(){});
    }
}
