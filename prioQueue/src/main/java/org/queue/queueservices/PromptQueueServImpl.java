package org.queue.queueservices;

import org.queue.taskmodels.PrioPromptTask;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PromptQueueServImpl implements QueueServices<PrioPromptTask> {


    private PriorityBlockingQueue<PrioPromptTask> queue;

    private static final String QUEUE_FILE_NAME = "QUEUE.tmp";


    public PromptQueueServImpl() {
        if (initFromDisk()) {

            System.out.println("INITIALISED QUEUE FROM DISK");

        } else {

            queue = new PriorityBlockingQueue<>();
            persistToDisk();
            System.out.println("PERSISTED INITIAL QUEUE TO DISK");
        }
    }


    @Override
    public boolean persistToDisk() {

        try (FileOutputStream fos = new FileOutputStream(QUEUE_FILE_NAME); ObjectOutputStream oos =
                new ObjectOutputStream(fos)) {
            oos.writeObject(queue);
            System.out.println("persisted");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean initFromDisk() {

        try (FileInputStream fos = new FileInputStream(QUEUE_FILE_NAME); ObjectInputStream oos =
                new ObjectInputStream(fos)) {
            Object o = oos.readObject();

            if (o instanceof PriorityBlockingQueue<?>) {

                queue = ((PriorityBlockingQueue<PrioPromptTask>) o);
                return true;
            } else {

                return false;
            }


        } catch (FileNotFoundException e) {
            System.out.println("TMP FILE NOT FOUND");
            return false;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    //spin n threads to read
    @Override
    public List<PrioPromptTask> poll(int numElements) {
// n threads will poll the queue , wait for all results then returns
        List<CompletableFuture<Object>> futures = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            CompletableFuture<Object> prioPromptTaskCompletableFuture = CompletableFuture.supplyAsync(queue::poll);

            futures.add(prioPromptTaskCompletableFuture);
        }


        return futures.stream().map(CompletableFuture::join).map(x -> (PrioPromptTask) x).collect(Collectors.toList());


    }

    private PrioPromptTask peek() {

        System.out.println(Thread.currentThread().getName());

        return queue.peek();
    }

    private PrioPromptTask pollq() {

        System.out.println(Thread.currentThread().getName());

        return queue.poll();
    }

    @Override
    public boolean add(PrioPromptTask e) {

        return queue.add(e);

    }

    @Override
    public boolean addAll(Collection<PrioPromptTask> c) {
        return queue.addAll(c);

    }
}
