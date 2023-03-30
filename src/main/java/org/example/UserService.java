package org.example;

import java.util.Optional;

public class UserService {
    private final Userdao userDao;

    public UserService(Userdao userDao){
        this.userDao = userDao;
    }

    public UserService() {
        this.userDao = new Userdao();
    }

    public void addUser(String login, String email, String password) {
        if (login != null && login != "" && email != null && email != "" && password != null && password != "") {
            User user = new User(login, email, password);
            if (userDao.save(user)) return;
        }
        throw new IllegalArgumentException();
    }

    public boolean validUser(String login, String password) {
        User user = userDao.get(login);
        return user !=null && user.getPassword().equals(password);
    }
}
