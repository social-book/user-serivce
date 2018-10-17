package com.socialbook.users.services;

//import com.kumuluz.ee.rest.beans.QueryParameters;
//import com.kumuluz.ee.rest.utils.JPAUtils;
import com.socialbook.users.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext(unitName = "user-service-jpa")
    private EntityManager em;


    public List<User> getUsers() {

//        Criteria API
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from);
        return em.createQuery(query).getResultList();
//        return (List<User>) em.createNamedQuery("User.getAll").getResultList();
    }

    public User getUser(Integer userId) {
        em.createNamedQuery("User.getOne").setParameter("id", userId);
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }
}
