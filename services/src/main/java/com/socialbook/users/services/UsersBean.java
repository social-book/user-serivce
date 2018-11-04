package com.socialbook.users.services;

//import com.kumuluz.ee.rest.beans.QueryParameters;
//import com.kumuluz.ee.rest.utils.JPAUtils;

import com.socialbook.users.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


@ApplicationScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext(unitName = "user-service-jpa")
    private EntityManager em;


    public List<UserDto> getUsers() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from);
        List<User> users = em.createQuery(query).getResultList();
        return Mapper.convertToUserDtos(users);
    }

    public UserDto getUser(Integer userId) {
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return Mapper.convertToDto(user);
    }

    public List<UserDto> getFriends(Integer userId) {
        User user = em.find(User.class, userId);
        if (user == null) throw new NotFoundException();
        UserDto userDtos = Mapper.convertToDto(user);
        return userDtos.getFriends();
    }

    public UserDto validateLogin(UserDto userDto) { //FIXME you don't get ID!!!!!
        List<User> users = em.createNamedQuery("User.getUserByUsername").setParameter("username", userDto.getUsername()).getResultList();
        if (users == null) return null;
        User user = users.get(0);
        if (user.getUsername().equals(userDto.getUsername()) &&
                user.getPassword().equals(userDto.getPassword())) {
            return Mapper.convertToDto(user);
        }
        return null;
    }

    //    @Transactional(Transactional.TxType.REQUIRED)
    public UserDto registerUser(UserDto userDto) {
        em.getTransaction().begin();
        User user = new User();
        if (userDto.getGender() == null) return null;
        user.setGender(userDto.getGender());

        if (userDto.getPassword() == null) return null;
        user.setPassword(userDto.getPassword());

        if (userDto.getUsername() == null) return null;
        user.setUsername(userDto.getUsername());

        user.setFriends(null);
        em.persist(user);
        em.flush();
        em.getTransaction().commit();
        if (user.getId() != null)
            return Mapper.convertToDto(user); //TODO check if userID is properly set!
        return null;
    }

    @Transactional
    public Boolean addFriend(Integer userId, Integer friendId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        User friend = em.find(User.class, friendId);
        if (friend == null || user == null) {
            em.getTransaction().rollback();
            return false;
        }
        List<User> friends = user.getFriends();
        boolean exists = friends.stream().anyMatch(t -> t.getId().equals(friendId));
        if (exists) {
            em.getTransaction().rollback();
            return false;
        }
        friends.add(friend);
        user.setFriends(friends);
        em.merge(user);
        em.getTransaction().commit();

        return true;
    }

    @Transactional
    public Boolean removeFriend(Integer userId, Integer friendId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        User friend = em.find(User.class, friendId);
        if (user == null || friend == null) {
            em.getTransaction().rollback();
            return false;
        }
        List<User> friends = user.getFriends();
        friends.removeIf(t -> t.getId().equals(friendId));
        user.setFriends(friends);
        em.merge(user);
        em.getTransaction().commit();
        return true;
    }
}
