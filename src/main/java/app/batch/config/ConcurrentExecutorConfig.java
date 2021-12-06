package app.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ConcurrentExecutorConfig {

    @Bean(name = "taskConcurrentExecutor")
    public Executor taskConcurrentExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(1);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        executor.setRejectedExecutionHandler(new BlockCallerExecutionPolicy());

        return executor;
    }

    @Slf4j
    static class BlockCallerExecutionPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                log.info("placing a task to blocking queue in BlockCallerExecutionPolicy");
                // based on the BlockingQueue documentation below should block until able to place on the queue...
                executor.getQueue().put(r);
            }
            catch (InterruptedException e) {
                throw new RejectedExecutionException("Unexpected InterruptedException while waiting to add Runnable to ThreadPoolExecutor queue...", e);
            }
        }
    }

}
