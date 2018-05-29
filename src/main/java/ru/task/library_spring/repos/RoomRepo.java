package ru.task.library_spring.repos;

import org.springframework.data.repository.CrudRepository;
import ru.task.library_spring.entity.Room;

public interface RoomRepo extends CrudRepository<Room, Long> {

}
