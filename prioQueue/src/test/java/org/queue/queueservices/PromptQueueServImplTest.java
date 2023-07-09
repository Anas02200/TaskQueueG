package org.queue.queueservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.queue.taskmodels.PrioPromptTask;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromptQueueServImplTest {

    private QueueServices<PrioPromptTask> queueServices;

    @BeforeEach
    void setUp() {

        List<PrioPromptTask> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            PrioPromptTask prioPromptTask = new PrioPromptTask();

            prioPromptTask.setPriority(i);

            prioPromptTask.setUserId("ANAS " + i);

            tasks.add(prioPromptTask);
        }


        queueServices = new PromptQueueServImpl();

        queueServices.addAll(tasks);


    }

//    @Test
//    void persistToDisk() {
//
//        queueServices.persistToDisk();
//    }

    @Test
    void initFromDisk() {
        queueServices.persistToDisk();

        queueServices.initFromDisk();


        List<PrioPromptTask> poll = queueServices.poll(10);



        poll.forEach(x-> System.out.println(x.getPriority()));

    }

//    @Test
//    void poll() {
//    }
}