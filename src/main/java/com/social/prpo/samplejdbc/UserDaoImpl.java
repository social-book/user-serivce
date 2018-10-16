package com.social.prpo.samplejdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDaoImpl implements BaseDao {

    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null)
            instance = new UserDaoImpl();
        return instance;
    }

    @Override
    public Connection getConnection() throws NamingException, SQLException{
        InitialContext initCtx;
        initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
        return ds.getConnection();
    }

    @Override
    public Entity getEntity(int id) {

        PreparedStatement preparedStatement = null;
        try {
            Connection connection = getConnection();
            String sql = "SELECT * FROM social_user WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            } else {
                System.out.println("User does not exists");
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Exception occurred");
                }
            }
        }
        return null;
    }

    @Override
    public void insert(Entity entity) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public List<Entity> getAll() {
        return null;
    }

    private UserEntity getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        return new UserEntity(id, username);
    }
}
