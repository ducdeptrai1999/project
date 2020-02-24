package com.buiminhduc.repository.impl;

import com.buiminhduc.common.annotation.Id;
import com.buiminhduc.paging.Pageable;
import com.buiminhduc.repository.JpaRepository;
import com.buiminhduc.util.AnnotationUtil;
import com.buiminhduc.util.MySqlConnectionUtil;
import com.buiminhduc.util.ObjectUtil;
import com.buiminhduc.util.pool.ConnectionPool;
import com.buiminhduc.util.pool.ConnectionPoolIMPL;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BasicQuery<T, ID> implements JpaRepository<T, ID> {
    protected Connection connection;
    protected ConnectionPool connectionPool;
    protected Class<T> tClass;

    public BasicQuery() {
        connectionPool = new ConnectionPoolIMPL();
        connection = connectionPool.getConnection();
        tClass = (Class<T>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    @Override
    public <S extends T> S insert(T entity) throws SQLException {
        StringBuilder sql = new StringBuilder(Query.INSERT);
        sql.append(AnnotationUtil.getTableName(tClass)).append("(");
        Field[] fields = entity.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                if (field.isAnnotationPresent(Id.class)) {
                    sql.append(AnnotationUtil.getPrimarykey(tClass, field.getName())).append(",");
                } else {
                    sql.append(AnnotationUtil.getFields(tClass, field.getName())).append(",");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });

        sql.deleteCharAt(sql.length() - 1);
        connection.setAutoCommit(false);
        sql.append(")").append(Query.VALUES).append("(");

        Arrays.stream(fields).forEach(field -> {
            sql.append("?").append(",");
        });

        sql.deleteCharAt(sql.length() - 1).append(")");

        PreparedStatement ps = connection.prepareStatement(sql.toString());

        try {
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, ObjectUtil.getMethod(entity, fields[i]));
            }
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            MySqlConnectionUtil.disConnection(connection);
        }

        return (S) entity;
    }

    @Override
    public void update(T entity) throws SQLException {
        StringBuilder sql = new StringBuilder(Query.UPDATE)
                .append(AnnotationUtil.getTableName(tClass))
                .append(Query.SET);
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                sql.append(AnnotationUtil.getFields(tClass, field.getName())).append(" = ?,");
            }
            sql.deleteCharAt(sql.length() - 1).append(" ");
            connection.setAutoCommit(false);
            sql.append(Query.WHERE).append(AnnotationUtil.getPrimarykey(tClass, fields[0].getName())).append(" =?");
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 1; i < fields.length; i++) {
                ps.setObject(i, ObjectUtil.getMethod(entity, fields[i]));
            }
            ps.setObject(fields.length, ObjectUtil.getMethod(entity, fields[0]));
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
    }

    @Override
    public <S extends T> S findById(ID id) {
        try {
            StringBuilder sql = new StringBuilder(Query.SELECT).append(AnnotationUtil.getTableName(tClass)).append(Query.WHERE).append(AnnotationUtil.getPrimarykey(tClass,"id")).append(" =?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Object object = null;
            while (resultSet.next()){
                object = ObjectUtil.map(tClass,resultSet);
            }
            return (S) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long count() throws SQLException {
        StringBuilder sql = new StringBuilder(Query.COUNT).append(AnnotationUtil.getTableName(tClass));

        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        long result = 0;
        while (rs.next()) {
            result = rs.getLong(1);
        }

        return result;
    }

    @Override
    public void delete(ID id) throws NoSuchFieldException {
        StringBuilder sql = new StringBuilder(Query.DELETE)
                .append(AnnotationUtil.getTableName(tClass))
                .append(Query.WHERE)
                .append(AnnotationUtil.getPrimarykey(tClass, "id"))
                .append(" = ?");

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends T> List<S> findByProperties(Map<String, Object> filter) {
        StringBuilder sql = new StringBuilder(Query.SELECT).append(AnnotationUtil.getTableName(tClass)).append(Query.WHERE).append(" 1 = 1");
        for (Map.Entry entry : filter.entrySet()) {
            if (entry.getValue() != null) {
                sql.append(Query.AND).append(" ").append(entry.getKey()).append(" = ?");
            }
        }
        sql.delete(sql.lastIndexOf("AND"), sql.length() - 1);
        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet resultSet = ps.executeQuery();
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                T t = (T) ObjectUtil.map(tClass, resultSet);
                results.add(t);
            }
            return (List<S>) results;
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            return null;
        }
    }

    @Override
    public <S extends T> List<S> findAll() {
        StringBuilder sql = new StringBuilder(Query.SELECT).append(AnnotationUtil.getTableName(tClass));
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql.toString());
            ResultSet resultSet = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while (resultSet.next()){
                T t = (T) ObjectUtil.map(tClass, resultSet);
                list.add(t);

            }
            return (List<S>) list;
        } catch (SQLException | IllegalAccessException | InstantiationException |NoSuchFieldException e) {
            return null;
        }
    }

    @Override
    public void update(T entity, int ID) throws SQLException {
        StringBuilder sql = new StringBuilder(Query.UPDATE)
                .append(AnnotationUtil.getTableName(tClass))
                .append(Query.SET);
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                sql.append(AnnotationUtil.getFields(tClass, field.getName())).append(" = ?,");
            }
            sql.deleteCharAt(sql.length() - 1).append(" ");
            connection.setAutoCommit(false);
            sql.append(Query.WHERE).append(AnnotationUtil.getPrimarykey(tClass, fields[0].getName())).append(" =?");
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 1; i < fields.length; i++) {
                ps.setObject(i, ObjectUtil.getMethod(entity, fields[i]));
            }
            ps.setObject(fields.length, ObjectUtil.getMethod(entity, fields[0]));
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }
    }


    @Override
    public <S extends T> List<S> findAll(Pageable pageable) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        StringBuilder sql = new StringBuilder(Query.SELECT)
                .append(AnnotationUtil.getTableName(tClass))
                .append(Query.LIMIT).append(pageable.getSize())
                .append(Query.OFFSET).append(pageable.getOffset());

        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            T t = (T) ObjectUtil.map(tClass, rs);

            list.add(t);
        }

        return (List<S>) list;
    }

//    @Override
//    public long count() throws SQLException {
//        StringBuilder sql = new StringBuilder(Query.COUNT).append(AnnotationUtil.getTableName(tClass));
//
//        PreparedStatement ps = connection.prepareStatement(sql.toString());
//        ResultSet rs = ps.executeQuery();
//        long result = 0;
//        while (rs.next()) {
//            result = rs.getLong(1);
//        }
//
//        return result;
//    }


//    @Override
//    public <S extends T> List<S> findByProperties(Map<String, Object> filter, Pageable pageable) {
//        StringBuilder sql = new StringBuilder(Query.SELECT).append(AnnotationUtil
//                .getTableName(tClass))
//                .append(Query.WHERE).append(" 1 = 1 ");
//
//        for (Map.Entry entry : filter.entrySet()) {
//            if (entry.getValue() != null) {
//                sql.append(Query.AND).append(entry.getKey()).append(" =? ");
//            }
//        }
//
//        sql.append(Query.LIMIT).append(pageable.getSize()).append(Query.OFFSET).append(pageable.getOffset());
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql.toString());
//            int i = 1;
//            for (Map.Entry entry : filter.entrySet()) {
//                if (entry.getValue() != null) {
//                    ps.setObject(i++, entry.getValue());
//                }
//            }
//            ResultSet rs = ps.executeQuery();
//            List<T> results = new ArrayList<>();
//            while (rs.next()) {
//                T t = (T) ObjectUtil.map(tClass, rs);
//
//                results.add(t);
//            }
//
//            return (List<S>) results;
//        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
//            return null;
//        }
//    }

//    @Override
//    public long countFind(Map<String, Object> filter) {
////        StringBuilder sql = new StringBuilder(Query.COUNT).append(AnotationUtil.getTableName(tClass));
////
////        for (Map.Entry entry : filter.entrySet()) {
////            if (entry.getValue() != null) {
////                sql.append(Query.AND).append(entry.getKey()).append(" =? ");
////            }
////        }
////
////        try {
////            PreparedStatement ps = connection.prepareStatement(sql.toString());
////            ResultSet rs = ps.executeQuery();
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//        return 0;
//    }

    public interface Query {
        String SELECT = "SELECT * FROM ";
        String COUNT = "SELECT COUNT(*) FROM ";
        String WHERE = " WHERE ";
        String AND = " AND ";
        String OR = " OR ";
        String LIKE = " LIKE ";
        String INSERT = "INSERT INTO ";
        String UPDATE = "UPDATE ";
        String DELETE = "DELETE FROM ";
        String SET = " SET ";
        String ORDER_BY = " ORDER BY ";
        String VALUES = " VALUES ";
        String LIMIT = " LIMIT ";
        String OFFSET = " OFFSET ";
    }
}
