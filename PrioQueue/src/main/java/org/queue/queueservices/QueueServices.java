package org.queue.queueservices;

import org.queue.taskmodels.PriorityTask;

import java.util.Collection;
import java.util.List;

public interface QueueServices<T extends PriorityTask> {


    boolean persistToDisk() ;


    boolean initFromDisk();


    //spin n threads to read
    List<T> poll(int numElements) ;

    boolean add(T e);

    boolean addAll(Collection<T> c);

}
