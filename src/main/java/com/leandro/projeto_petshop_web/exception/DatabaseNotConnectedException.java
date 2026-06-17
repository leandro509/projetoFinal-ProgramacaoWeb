package com.leandro.projeto_petshop_web.exception;

import java.sql.SQLException;

public class DatabaseNotConnectedException extends SQLException {
    public DatabaseNotConnectedException(String message) {
        super(message);
    }
}
