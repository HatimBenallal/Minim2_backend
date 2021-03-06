package edu.upc.dsa;

import edu.upc.dsa.models.FAQ;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {


    public User addUser(User user);
    public User login(String username, String password);
    public int size();
    public List<Item> catalogoTienda ();
    public List<User> getUsers();

    public List<FAQ> listafaqs ();

}
