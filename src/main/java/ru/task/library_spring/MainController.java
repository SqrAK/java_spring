package ru.task.library_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.task.library_spring.entity.Room;
import ru.task.library_spring.entity.User;
import ru.task.library_spring.repos.RoomRepo;
import ru.task.library_spring.repos.UserRepo;

import java.util.Optional;

@Controller
public class MainController {

    private final UserRepo userRepo;
    private final RoomRepo roomRepo;

    @Autowired
    public MainController(UserRepo userRepo, RoomRepo roomRepo) {
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
        Iterable<Room> rooms = roomRepo.findAll();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }


    @GetMapping("/users")
    public String users(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserGet() {
        return "users/add";
    }

    @PostMapping("/users/add")
    public RedirectView addUserPost(@RequestParam String username, @RequestParam String password)
    {
        User user = new User(username, password);
        userRepo.save(user);

        return new RedirectView("/users");
    }

    @GetMapping("/rooms/add")
    public String addRoomGet() {
        return "rooms/add";
    }

    @PostMapping("/rooms/add")
    public RedirectView addRoomPost(@RequestParam String name, @RequestParam Integer number)
    {
        Room room = new Room(name, number);
        roomRepo.save(room);

        return new RedirectView("/rooms");
    }

    @PostMapping("/users")
    public RedirectView deleteUser(@RequestParam Long id)
    {
        User user = userRepo.findById(id).get();
        userRepo.delete(user);

        return new RedirectView("/users");
    }

    @PostMapping("/rooms")
    public RedirectView roomAction(@RequestParam Long id, @RequestParam String type, @RequestParam String login)
    {
        User user = userRepo.findByLogin(login);
        Optional<Room> rooms = roomRepo.findById(id);
        Room room = rooms.get();
//        Room room = roomRepo.findById(id);
        if (type.equals("take"))
        {
            room.setWho_take(user);
            roomRepo.save(room);
        }
        else if (type.equals("return"))
        {
            room.setWho_take(null);
            roomRepo.save(room);
        }
        else if (type.equals("delete"))
        {
            roomRepo.delete(room);
        }

        //User user = userRepo.findById(id).get();
        //userRepo.delete(user);

        return new RedirectView("/rooms");
    }

//    //redirect to users page
//    @GetMapping()
//    public RedirectView redirectWithUsingRedirectView() {
//        return new RedirectView("/users");
//    }

}