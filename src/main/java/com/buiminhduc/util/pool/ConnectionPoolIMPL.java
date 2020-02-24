package com.buiminhduc.util.pool;

import com.buiminhduc.util.MySqlConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class  ConnectionPoolIMPL implements ConnectionPool {
    private static final LinkedList<Connection> connectionInUes = new LinkedList<>();
    private static final int MAX_CONNECTION = 2;

    public ConnectionPoolIMPL(){
        initializeConnectionPool();
    }

    private synchronized void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            Connection newConnection = MySqlConnectionUtil.getConnection();
            connectionInUes.add(newConnection);
        }
        notifyAll();
    }

    private boolean checkIfConnectionPoolIsFull() {
        return connectionInUes.size() >= MAX_CONNECTION;
    }

    @Override
    public synchronized Connection getConnection() {
        while (connectionInUes.size() == 0 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Connection) connectionInUes.poll();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        try {
            if (connection.isClosed()) {
                initializeConnectionPool();
            }else {
                boolean isReleased = connectionInUes.offer(connection);
                notifyAll();
                return isReleased;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
