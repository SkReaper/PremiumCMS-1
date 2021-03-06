package Login;

import domain.User;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController {

    private static LoginController instance;
    private LoginProvider loginProvider;
    private User user;

    private LoginController() {
        loginProvider = new LoginProvider();
    }

    public static LoginController getInstance() {
        if (instance == null){
            instance = new LoginController();
        }
        return instance;
    }

    public void login (String username, String password) throws Exception {
        if (username.equals("") || password.equals("")) {
            throw new Exception("Please fill required fields!");
        }
        User user = loginProvider.getUser(username);
        if (user == null) {
            throw new Exception("This username doesn't exist!");
        }
        else if (!user.getPassword().equals(password)) {
            throw new Exception("Wrong password!");
        }
        else if (user.getStatus().equals("pending")) {
            throw new Exception("Your account needs approval!");
        }
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
