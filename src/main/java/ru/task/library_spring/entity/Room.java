package ru.task.library_spring.entity;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user_id;

    private String name;
    private Integer number;

    public Room() {}

    public Room(String name, Integer number) {
        this.name = name;
        this.number = number;
        this.user_id = null;
    }

    public User getWho_take() {
        return user_id;
    }

    public void setWho_take(User who_take) {
        this.user_id = who_take;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
