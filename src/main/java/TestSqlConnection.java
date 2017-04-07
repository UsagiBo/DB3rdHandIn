
import org.apache.commons.lang.time.StopWatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by l on 30-03-2017.
 */
public class TestSqlConnection {

    public static void main(String[] args) throws IOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        double avg;
        double median;
        long[] times = new long[20];
        int[] rndNodes = {4647, 28999, 23, 500, 3556, 99873, 5, 97687, 45678, 333, 299, 1111, 84376, 300090, 444444, 34, 77, 9,
            7778, 1000};
        Calc Cal = new Calc();
        StopWatch timer = new StopWatch();
        String url = "jdbc:mysql://localhost:3030/socialnetwork?useSSL=false";
        String user = "root";
        String password = "pwd";
        System.out.println("Depth of one:");

        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            String query = "SELECT name FROM person "
                    + "WHERE id = ANY (SELECT target_node_id FROM endorses WHERE source_node_id = " + rndNodes[i] + ");";

            try {

                con = DriverManager.getConnection(url, user, password);

                st = con.createStatement();
                rs = st.executeQuery(query);

                /* if (rs.next()) {
                    System.out.println(rs.getString(1));
                }*/
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    System.err.println(ex);
                }
            }
            times[i] = timer.getTime();

            System.out.println(+timer.getTime() + " ms");
            timer.stop();
        }
        avg = Cal.CalAvg(times);
        System.out.println("Average time: " + avg + " ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: " + median + " ms");

        System.in.read();

        System.out.println("Depth of two:");

        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
           String query = "SELECT f1.source_node_id, f1.target_node_id FROM "
                    + "endorses f1 WHERE f1.source_node_id = " + rndNodes[i] + " OR "
                    + "f1.source_node_id IN ( select f2.target_node_id from "
                    + "endorses f2 where f2.source_node_id = " + rndNodes[i] +"); " ;
            try {

                con = DriverManager.getConnection(url, user, password);

                st = con.createStatement();
                rs = st.executeQuery(query);

                /* if (rs.next()) {
                    System.out.println(rs.getString(1));
                }*/
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    System.err.println(ex);
                }
            }
            times[i] = timer.getTime();

            System.out.println(+timer.getTime() + " ms");
            timer.stop();
        }
        avg = Cal.CalAvg(times);
        System.out.println("Average time: " + avg + " ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: " + median + " ms");

        System.in.read();

        System.out.println("Depth of three:");

        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
             String query = "SELECT f1.source_node_id, f1.target_node_id FROM "
                    + "endorses f1 WHERE f1.source_node_id = " + rndNodes[i] + " OR "
                    + "f1.source_node_id IN ( select f2.target_node_id "
                    + " from endorses f2 where f2.source_node_id in ( "
                    + "select f2.target_node_id from endorses f2 "
                    + "where f2.source_node_id = " + rndNodes[i] + " ) "
                    + "); ";

            try {

                con = DriverManager.getConnection(url, user, password);

                st = con.createStatement();
                rs = st.executeQuery(query);

                /* if (rs.next()) {
                    System.out.println(rs.getString(1));
                }*/
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    System.err.println(ex);
                }
            }
            times[i] = timer.getTime();

            System.out.println(+timer.getTime() + " ms");
            timer.stop();
        }
        avg = Cal.CalAvg(times);
        System.out.println("Average time: " + avg + " ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: " + median + " ms");

        System.in.read();

        System.out.println("Depth of four:");

        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
           String query = "SELECT f1.source_node_id, "
                    + "f1.target_node_id FROM "
                    + "endorses f1 "
                    + "WHERE f1.source_node_id = " + rndNodes[i] + " OR "
                    + "f1.source_node_id IN "
                    + "( "
                    + " select f2.target_node_id "
                    + " from endorses f2 "
                    + " where f2.source_node_id in "
                    + " ( "
                    + "    select f2.target_node_id "
                    + "    from endorses f2 "
                    + "    where f2.source_node_id in "
                    + "    ( "
                    + "      select f2.target_node_id "
                    + "      from endorses f2 "
                    + "      where f2.source_node_id = " + rndNodes[i] + " "
                    + "    ) "
                    + " ) "
                    + "); ";

            try {

                con = DriverManager.getConnection(url, user, password);

                st = con.createStatement();
                rs = st.executeQuery(query);

                /* if (rs.next()) {
                    System.out.println(rs.getString(1));
                }*/
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    System.err.println(ex);
                }
            }
            times[i] = timer.getTime();

            System.out.println(+timer.getTime() + " ms");
            timer.stop();
        }
        avg = Cal.CalAvg(times);
        System.out.println("Average time: " + avg + " ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: " + median + " ms");

        System.in.read();

        System.out.println("Depth of five:");

        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            String query = "SELECT f1.source_node_id, "
                    + "f1.target_node_id FROM "
                    + "endorses f1 "
                    + "WHERE f1.source_node_id = " + rndNodes[i] + " OR "
                    + "f1.source_node_id IN "
                    + "( "
                    + " select f2.target_node_id "
                    + " from endorses f2 "
                    + " where f2.source_node_id in "
                    + " ( "
                    + "    select f2.target_node_id "
                    + "    from endorses f2 "
                    + "    where f2.source_node_id in "
                    + "    ( "
                    + "      select f2.target_node_id "
                    + "      from endorses f2 "
                    + "      where f2.source_node_id in "
                    + "      ( "
                    + "        select f2.target_node_id "
                    + "        from endorses f2 "
                    + "        where f2.source_node_id = " + rndNodes[i] + " "
                    + "      ) "
                    + "    ) "
                    + " ) "
                    + "); ";

            try {

                con = DriverManager.getConnection(url, user, password);

                st = con.createStatement();
                rs = st.executeQuery(query);

          if (rs.next()) {
                    System.out.println(rs.getString(1));
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    System.err.println(ex);
                }
            }
            times[i] =  timer.getTime();

            System.out.println(+timer.getTime()+" ms");
            timer.stop();
        }
        avg = Cal.CalAvg(times);
        System.out.println("Average time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");

        System.in.read();
    }
}
