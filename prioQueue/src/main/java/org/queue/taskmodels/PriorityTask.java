package org.queue.taskmodels;

public abstract class PriorityTask extends BaseTaskModel implements Comparable<PriorityTask> {
    static final long serialVersionUID = 42L;

    private int priority;


    protected PriorityTask(String id, String userId) {
        super(id, userId);
    }

    public PriorityTask() {
        super();
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
