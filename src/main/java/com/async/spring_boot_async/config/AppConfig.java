package com.async.spring_boot_async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAsync
public class AppConfig implements AsyncConfigurer {

    private ThreadPoolExecutor poolExecutor;

    @Override
    public synchronized Executor getAsyncExecutor() {

        int minPoolSize = 2;
        int maxPoolSize = 4;
        int queueSize = 3;

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(minPoolSize, maxPoolSize, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(queueSize), new CustomThreadFactory());

        return poolExecutor;
    }

    private class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNo = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("myThread-" + threadNo.getAndIncrement());
            return thread;
        }
    }
}
