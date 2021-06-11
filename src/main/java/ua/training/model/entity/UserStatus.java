package ua.training.model.entity;

import java.io.Serializable;

public class UserStatus implements Serializable {

    private static final long serialVersionUID = -6024656031856157181L;

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
