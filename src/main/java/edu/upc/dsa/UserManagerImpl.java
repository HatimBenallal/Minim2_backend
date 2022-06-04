package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class UserManagerImpl implements UserManager {
    private static UserManager instance;
    protected List<User> users;
    private HashMap<String, User> hmUsers;
    protected List<Item> items;
    protected List<FAQ> faqs;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl() {
        this.users = new LinkedList<>();
        this.items = new LinkedList<>();
        this.faqs = new LinkedList<>();
        hmUsers = new HashMap<String, User>();
    }

    public static UserManager getInstance() {
        if (instance == null) instance = new UserManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public User addUser(User user) {
        logger.info("Trying to add a new User ");
        User aux = hmUsers.get(user.getUsername());//Comprobamos que no este ya registrado
        if (aux == null) //Si no esta en el hashmap, lo añadimos
        {
            this.users.add(user);
            this.hmUsers.put(user.getUsername(), user);
            logger.info("new User added");
            return user;
        } else //si ya tenemos un usuario registrado, retornamos null
        {
            logger.info("this username is already used");
            return null;
        }
    }

    public User login(String username, String password) {
        User u=hmUsers.get(username);
        if (u!=null) { //try

            if (!password.equals(u.getPassword())) {
                logger.warn("Incorrect password");
                return null;

            }
            else {
                logger.warn("User logged in");
                return u;
            }
        } else //catch (NullPointerException e)
        {
            return null;
        }

    }

    public List<Item> catalogoTienda() {
        items.add(new Item("Vida extra", "Pocion para una vida extra", 100.0));
        items.add(new Item("Sierra", "Sierra que corta mucho", 75.0));
        items.add(new Item("Escudo", "Proteccion extra", 230.0));
        items.add(new Item("Espada", "Espada dorada", 150.0));
        items.add(new Item("Comida", "Equivale a media vida", 20.0));
        logger.info("Catalogo cargado");
        return items;


    }

    public List<FAQ> listafaqs() {
        if (faqs.isEmpty()) {
            faqs.add(new FAQ("What is the customer service email?", "nightmare@dsa1.com"));
            faqs.add(new FAQ("It can be played online?", "Not at the moment, in the future it may be"));
            faqs.add(new FAQ("Can I have more than one game in progress at the same time?",
                    "Yes you can, when you start the game you can select the game"));
            faqs.add(new FAQ("Can I see the scores, rankings, time, etc?",
                    "Yes, in the statistics part"));
            faqs.add(new FAQ("Is the game free?",
                    "Yes, you can download it completely free"));
            faqs.add(new FAQ("Can upgrades or items be purchased in-game?",
                    "You can buy items or upgrades with the coins you get by playing and beating levels. It is not a pay-to-win game."));
            faqs.add(new FAQ("What can be done on the website?",
                    "You can check your profile, buy items in the store with game coins, check statistics, register. Just like in the app."));
        }
        logger.info("faqs loaded");
        return faqs;
    }


    public List<User> getUsers(){
        return users;
    }
}