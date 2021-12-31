package com.example.volunteerservice;
import java.sql.*;

/**
 * @author dcr
 */

public class MysqlHelper {
    static final String name = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/";
    static String user;
    static String password;
    static String sql;
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    /******************************************************************************************************************/

    public MysqlHelper(){};

    public MysqlHelper(String input_database, String input_user, String input_pass) throws SQLException, ClassNotFoundException {
        url = url.concat(input_database);
        user = input_user;
        password = input_pass;

        Class.forName(name);
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connect to thr database");
        }catch (SQLException e){
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

    /**现在就是个实验，是知道每一项的信息的，同时不存在那些奇奇怪怪的特殊情况**/
    public void Insert(final String name, final int student_number, final String grade, final String sex,
                       final String collage, final String major) throws SQLException {
        sql = "insert into student_info (name, student_number, grade, sex, collage, major)" +
                "values (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, student_number);
        preparedStatement.setString(3, grade);
        preparedStatement.setString(4, sex);
        preparedStatement.setString(5, collage);
        preparedStatement.setString(6, major);

        int update = preparedStatement.executeUpdate();
        System.out.println(update + " lines have been changed");
        preparedStatement.close();
    }

    public void Select() throws SQLException{
        sql = "select * from student_info";
//        int update = statement.executeUpdate(sql);
//        System.out.println(update + " lines have been selected");
        statement = connection.createStatement();
        ResultSet re = statement.executeQuery(sql);
        while (re.next()){
            int id = re.getInt(re.findColumn("id"));
            String name = re.getString(re.findColumn("name"));
            int student_number = re.getInt(re.findColumn("student_number"));
            String sex = re.getString(re.findColumn("sex"));
            String grade = re.getString(re.findColumn("grade"));
            String collage = re.getString(re.findColumn("collage"));
            String major = re.getString(re.findColumn("major"));

            System.out.println(id + ' '+ name + ' '+ student_number + ' '+ sex + ' '+
                                grade + ' '+ collage + ' '+ major);
            statement.close();
        }
    }

    public void close() throws SQLException {
        connection.close();
    }
}
