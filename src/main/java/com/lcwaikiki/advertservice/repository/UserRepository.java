package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();

        users.add(
                new User(1L, "Evren", "Ispiroglu", "Male", "evrenn.ispiroglu@gmail.com",
                        "5427261042", "İstanbul", 34, "Şişli", 1,
                        "This is about user evren", new ArrayList<>())
        );
        users.add(
                new User(2L, "Cihan", "Ispiroglu", "Male", "cihan.ispiroglu@gmail.com",
                        "5427261042", "İstanbul", 34, "Şişli", 1,
                        "This is about user evren", new ArrayList<>())
        );
        for (long i = 3; i < 5; i++)
            users.add(
                    new User(i, "Masal", "Ispiroglu", "Male", "cihan.ispiroglu@gmail.com",
                            "5427261042", "İstanbul", 34, "Şişli", 1,
                            "This is about user evren", new ArrayList<>()));

    }
    public List<User> findAll() {
        return users;
    }
    public User findById(int id) throws UserNotFoundException {
        return users.stream().filter(user -> user.getId()==id).findFirst().orElseThrow(UserNotFoundException::new);
    }
    public User create(User user) {
        users.add(user);
        return user;
    }
    public void update(User user, int id) throws UserNotFoundException {
        User existingUser = users.stream().filter(u -> u.getId()==id).findFirst().orElseThrow(UserNotFoundException::new);
        users.set(
                users.indexOf(existingUser), user
        );
    }
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
