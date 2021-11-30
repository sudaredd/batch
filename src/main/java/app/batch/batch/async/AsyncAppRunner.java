package app.batch.batch.async;

import app.batch.batch.model.User;
import app.batch.batch.service.GitHubLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AsyncAppRunner implements CommandLineRunner {


    private final GitHubLookupService gitHubLookupService;

    public AsyncAppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {

        long start = System.currentTimeMillis();

        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        CompletableFuture<User> page4 = gitHubLookupService.findUser("sudaredd");

        // Wait until they are all done
        CompletableFuture.allOf(page1, page2, page3, page4).join();

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
        log.info("--> " + page4.get());


    }
}
