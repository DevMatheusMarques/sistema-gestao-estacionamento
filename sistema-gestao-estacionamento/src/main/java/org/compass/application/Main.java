package org.compass.application;

import org.compass.db.DbException;
import org.compass.model.dao.VeiculoDao;
import org.compass.model.dao.implement.TicketDaoJDBC;
import org.compass.model.dao.implement.VeiculoDaoJDBC;
import org.compass.model.entities.*;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.inicio();
    }
}

