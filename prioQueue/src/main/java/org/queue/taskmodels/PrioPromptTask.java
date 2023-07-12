package org.queue.taskmodels;

public class PrioPromptTask extends PriorityTask{
    static final long serialVersionUID = 42L;
    private String prompt;


    public PrioPromptTask() {
        super();
    }

    public PrioPromptTask(String id, String userId) {
        super(id, userId);
    }


    public PrioPromptTask(String id, String userId, String prompt) {
        super(id, userId);
        this.prompt = prompt;
    }


    @Override
    public int compareTo(PriorityTask o) {

        return -Integer.compare(this.getPriority(), o.getPriority());


    }
}
