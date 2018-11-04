package com.socialbook.users.services;

import com.socialbook.users.entities.User;
import com.socialbook.users.services.configuration.AppProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext(unitName = "user-service-jpa")
    private EntityManager em;


    @Inject
    AppProperties appProperties;

    public List<UserDto> getUsers() {
        log.info("getting all users");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from);
        List<User> users = em.createQuery(query).getResultList();
        log.info("returning all users");
        return Mapper.convertToUserDtos(users);
    }

    public List<UserDto> getFriends(Integer userId) {
        log.info("searching friends");
        User user = em.find(User.class, userId);
        if (user == null) throw new NotFoundException();
        UserDto userDtos = Mapper.convertToDto(user);
        log.info("returning friends");
        return userDtos.getFriends();
    }

    public UserDto validateLogin(UserDto userDto) {
        log.info("checking for user if exists");
        List<User> users = em.createNamedQuery("User.getUserByUsername").setParameter("username", userDto.getUsername()).getResultList();
        if (users == null) return null;
        User user = users.get(0);
        if (user.getUsername().equals(userDto.getUsername()) &&
                user.getPassword().equals(userDto.getPassword())) {
            log.info("user exists password and username are correct");
            return Mapper.convertToDto(user);
        }
        log.info("username or passwords does not match");
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public UserDto registerUser(UserDto userDto) {
        log.info("beginning of transaction");
        beginTx();
        User user = new User();
        if (userDto.getGender() == null) {
            log.info("gender is not set... rolling back");
            rollbackTx();
            return null;
        }
        user.setGender(userDto.getGender());
        if (userDto.getPassword() == null) {
            log.info("pass not set rolling back");
            rollbackTx();
            return null;
        }
        user.setPassword(userDto.getPassword());
        if (userDto.getUsername() == null) {
            log.info("username not set rolling back");
            rollbackTx();
            return null;
        }
        user.setUsername(userDto.getUsername());
        user.setFriends(null);
        log.info("persisting user");
        em.persist(user);
        commitTx();
        log.info("transaction committed");
        if (user.getId() != null)
            return Mapper.convertToDto(user);
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Boolean addFriend(Integer userId, Integer friendId) {
        if (appProperties.isStatisticServiceEnabled()) {
            log.info("statistic is available");
        } else log.info("statistic is disabled");
        log.info("finding user");
        User user = em.find(User.class, userId);
        log.info("finding friend");
        User friend = em.find(User.class, friendId);
        if (friend == null || user == null) {
            return false;
        }
        List<User> friends = user.getFriends();
        boolean exists = friends.stream().anyMatch(t -> t.getId().equals(friendId));
        if (exists) {
            return false;
        }
        friends.add(friend);
        user.setFriends(friends);
        beginTx();
        log.info("transaction started merging user");
        em.merge(user);
        commitTx();
        log.info("transaction committed");
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Boolean removeFriend(Integer userId, Integer friendId) {
        User user = em.find(User.class, userId);
        User friend = em.find(User.class, friendId);
        log.info("user and friend are found");
        if (user == null || friend == null) {
            return false;
        }
        List<User> friends = user.getFriends();
        friends.removeIf(t -> t.getId().equals(friendId));
        user.setFriends(friends);
        beginTx();
        log.info("transaction started merging user");
        em.merge(user);
        log.info("committing transaction");
        commitTx();
        return true;
    }


    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
