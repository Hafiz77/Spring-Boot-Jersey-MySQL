package com.basePackage.services;

import com.basePackage.dao.ConnectionFactory;
import com.basePackage.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hafiz on 4/20/2016.
 */


@Service
public class UserService {
    static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public int createUser(User user) {

        int createResult = 0;
        try {
            Connection conn= ConnectionFactory.createConnection();
            Statement statement = conn.createStatement();
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("insert  into userinfo.user ");
            sqlQuery.append("( firstname,lastname,email,phone,cell,age )");
            sqlQuery.append("VALUES( ");
            sqlQuery.append("'"+user.getFirstname()+"',");
            sqlQuery.append("'"+user.getLastname()+"',");
            sqlQuery.append("'"+user.getEmail()+"',");
            sqlQuery.append("'"+user.getPhone()+"',");
            sqlQuery.append("'"+user.getCell()+"',");
            sqlQuery.append("'"+user.getAge()+"'");
            sqlQuery.append(")");
            logger.info(">> getPerson({})", sqlQuery);

            createResult = statement.executeUpdate(sqlQuery.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createResult;
    }
    public int updateUser(User user){
        int createResult = 0;
        try {
            Connection conn=ConnectionFactory.createConnection();
            Statement statement = conn.createStatement();
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append("UPDATE userinfo.user ");
            sqlQuery.append("SET ");
            sqlQuery.append("firstname='"+user.getFirstname()+"',");
            sqlQuery.append("lastname='"+user.getLastname()+"',");
            sqlQuery.append("email='"+user.getEmail()+"',");
            sqlQuery.append("phone='"+user.getPhone()+"',");
            sqlQuery.append("cell='"+user.getCell()+"',");
            sqlQuery.append("age='"+user.getAge()+"'");
            sqlQuery.append(" WHERE ");
            sqlQuery.append("id="+user.getId()+"");
            logger.info(">> getPerson({})", sqlQuery);
            createResult = statement.executeUpdate(sqlQuery.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createResult;
    }

    public List<User> listUsers(){
        List<User> listusers=new ArrayList<>();

        Connection conn=ConnectionFactory.createConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet result;
            String sql = "SELECT * FROM userinfo.user";
            result = statement.executeQuery(sql);
            while (result.next()){

                User user=new User();
                user.setId(result.getInt(1));
                user.setFirstname(result.getString(2));
                user.setLastname(result.getString(3));
                user.setEmail(result.getString(4));
                user.setPhone(result.getString(5));
                user.setCell(result.getString(6));
                user.setAge(result.getInt(7));

                listusers.add(user);
            }} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listusers;
    }

    public User getUser(int userId){
        User user=null;
        Connection conn=ConnectionFactory.createConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet result;
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("SELECT * FROM user");
            sqlQuery.append(" WHERE ");
            sqlQuery.append("id=");
            sqlQuery.append(userId);
            logger.info(">> getUser({})", sqlQuery.toString());
            result = statement.executeQuery(sqlQuery.toString());


            while (result.next()){
                user=new User();
                user.setId(result.getInt(1));
                user.setFirstname(result.getString(2));
                user.setLastname(result.getString(3));
                user.setEmail(result.getString(4));
                user.setPhone(result.getString(5));
                user.setCell(result.getString(6));
                user.setAge(result.getInt(7));
            }} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;

    }

    public int deleteUser(int userId) {

        int createResult = 0;
        try {
            Connection conn=ConnectionFactory.createConnection();
            Statement statement = conn.createStatement();
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append("DELETE FROM userinfo.user ");
            sqlQuery.append(" WHERE ");
            sqlQuery.append("id="+userId+"");
            logger.info(">> getPerson({})", sqlQuery);
            createResult = statement.executeUpdate(sqlQuery.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return createResult;
    }



}
