package com.social.prpo.samplejdbc;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao {

    Connection getConnection() throws NamingException, SQLException;

    Entity getEntity(int id);

    void insert(Entity entity);

    void remove(int id);

    void update(Entity entity);

    List<Entity> getAll();
}
