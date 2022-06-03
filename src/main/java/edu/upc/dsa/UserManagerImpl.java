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
        faqs.add(new FAQ("¿Cual és el correo e atención al clinete?", "juego2D@gmail.com"));
        faqs.add(new FAQ("¿Se puede jugar online?", "De momento no, en un futuro puede ser"));
        faqs.add(new FAQ("¿Puedo tener más de una partida a la vez en curso?", "Sí que se puede, al arrancar el juego se permite seleccionar la partida"));
        faqs.add(new FAQ("¿Puedo mirar las puntaciones, rankings, tiempo, etc?", "Sí, en la parte de estadístícas"));
        logger.info("faqs cargados");
        return faqs;


    }

    public List<User> getUsers(){
        return users;
    }
}