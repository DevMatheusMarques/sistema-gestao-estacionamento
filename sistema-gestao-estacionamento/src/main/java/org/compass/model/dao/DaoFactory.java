package org.compass.model.dao;

import org.compass.db.DB;
import org.compass.model.dao.implement.TicketDaoJDBC;
import org.compass.model.dao.implement.VeiculoDaoJDBC;

public class DaoFactory {
    private static VeiculoDao crateVeiculoDao() {
        return new VeiculoDaoJDBC(DB.getConnection());
    }

    private static TicketDao crateTicketDao() {
        return new TicketDaoJDBC(DB.getConnection());
    }
}
