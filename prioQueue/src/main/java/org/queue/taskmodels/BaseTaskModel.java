package org.queue.taskmodels;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BaseTaskModel implements Serializable {
    static final long serialVersionUID = 42L;
    private String id;
    private String userId;

    private LocalDateTime issuedAt;


    protected BaseTaskModel(String id, String userId) {
        this.id = id;
        this.userId = userId;
        issuedAt = LocalDateTime.now();
    }

    protected BaseTaskModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
}
