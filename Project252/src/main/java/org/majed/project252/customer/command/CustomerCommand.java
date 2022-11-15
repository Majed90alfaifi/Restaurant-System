package org.majed.project252.customer.command;


import java.sql.SQLException;

public interface CustomerCommand {

    void execute(int id) throws SQLException;

}

