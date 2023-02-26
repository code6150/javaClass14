package kr.code6150.database;

import java.sql.*;
import java.util.function.Consumer;

public class DbManager {

    //객체 맴버 ? -> 객체가 생성 된 이 후에 사용 가능
    private static DbManager instance;

    // 접근제어자 리턴타입 이름(매개변수) { 코드; }
    // 객체 맴버 함수

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private Connection con;
    private boolean connected;

    private DbManager() {
        // http, ftp, https, ...
        // ex) https://www.naver.com
        try {
            con = DriverManager.getConnection("jdbc:mariadb:127.0.0.1/test","root","1234");

            connected = true;
        } catch (SQLException e) {
            connected = false;
        }
    }

    public boolean isConnected() {
        try {
            if(!connected) return false;
            return (con != null && !con.isClosed());
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean execute(String sql) {

        // try - with - resources ( try 끝나고 자동으로 해당 리소스를 close 해주기 위함. )
        try (Statement s = con.createStatement()) {

            s.execute(sql);
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public boolean execute(String sql, ThrowExceptionConsumer<PreparedStatement> consumer) {

        // ?를 나중에 채움
        try(PreparedStatement s = con.prepareStatement(sql)) {
            //Database 내부
            //  - Connection 을 들고있음
            consumer.accept(s);
            //설정은 외부에서 끝마친 뒤.

            s.execute();

            return true;
        } catch (SQLException e) {
            return false;
        }

    }
    //innovation encourage


}
