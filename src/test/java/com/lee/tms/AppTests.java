package com.lee.tms;

import com.lee.tms.modules.task.mapper.BatchMapper;
import com.lee.tms.modules.task.mapper.DrawingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


@SpringBootTest
@Slf4j
public class AppTests
{

    @Autowired
    private BatchMapper batchMapper;

    @Autowired
    private DrawingMapper drawingMapper;

    private ThreadPoolTaskExecutor executor;
    private CountDownLatch latch;

    // @Test
    void asyncProcess() throws IOException, InterruptedException
    {
    }
}
