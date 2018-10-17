package com.socialbook.users.services;

//import com.kumuluz.ee.rest.beans.QueryParameters;
//import com.kumuluz.ee.rest.utils.JPAUtils;
import com.socialbook.users.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext(unitName = "user-service-jpa")
    private EntityManager em;


    public List<User> getUsers() {
        List<User> users = em.createNamedQuery("User.getAll").getResultList();
        return users;
    }

    public User getUser(Integer userId) {
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

}
