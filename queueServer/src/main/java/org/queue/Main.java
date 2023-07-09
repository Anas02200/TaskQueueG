package org.queue;

import org.queue.grpc.generated.QueueOpsGrpc;
import org.queue.queueservices.PromptQueueServImpl;
import org.queue.queueservices.QueueServices;
import org.queue.taskmodels.PrioPromptTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");




        PromptQueueServImpl promptQueueServ = new PromptQueueServImpl();

    }
}