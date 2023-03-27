package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final static Map<String, User> users = new HashMap<String, User>();

    public boolean save(User user) {
        String login = user.getLogin();
        if (users.containsKey(login)) {
            return false;
        } else {
            users.put(login, user);
            return true;
        }
    }

    public User getUser(String login) {
        return users.get(login);
    }
}
