package kr.code6150.database;

import java.sql.SQLException;

@FunctionalInterface
public interface ThrowExceptionConsumer<T> {

    void accept(T t) throws SQLException;

}
