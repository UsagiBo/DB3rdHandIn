
import org.neo4j.driver.v1.*;
import org.apache.commons.lang.time.StopWatch;

import java.io.IOException;


/**
 * Created by l on 30-03-2017.
 */
public class TestConnection {
    public static void main(String[] args) throws IOException {

        double average;
        double median;
        long[] times = new long[20];
        int[] rndNodes = {4592,73950,11,472,7305,25459,99,23452,2234,234,543,2352,2352,462948,772893,34,77,9
        ,1456,2455};
        Calc Cal = new Calc();
        Driver driver = GraphDatabase.driver(
                "bolt://localhost:3030",
                AuthTokens.basic( "neo4j", "class" ) );
        Session session = driver.session();
        StopWatch timer = new StopWatch();



        System.out.println("Depth of one:");
        for (int i = 0; i < 20; i++) {
        timer.reset();
        timer.start();
        
        StatementResult result = session.run(
            "MATCH (p:Person)-[r:ENDORSES]->(e:Person)" +
                    "WHERE p.id = "+ rndNodes[i] +
                    " RETURN e.name as name");
        timer.stop();
        times[i] =  timer.getTime();

        System.out.println(+timer.getTime()+" ms");
        }

        average = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ average +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");

        System.in.read();


        System.out.println("Dept of two:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            // all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.
            StatementResult result2 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        average = Cal.CalAvg(times);
        System.out.println("Average time: "+ average +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");

        System.in.read();


        System.out.println("Dept of three:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result3 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        average = Cal.CalAvg(times);
        System.out.println("Average time: "+ average +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");

        System.in.read();


        System.out.println("Depth of four:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result4 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        average = Cal.CalAvg(times);
        System.out.println("Average time: "+ average +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");

        System.in.read();



        System.out.println("Depth of five:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result5 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();

            System.out.println(+timer.getTime()+" ms");
        }

        average = Cal.CalAvg(times);
        System.out.println("Average time: "+ average +" ms");
        median = Cal.CalMedian(times);
        System.out.println("Median time: "+ median +" ms");







        session.close();
        driver.close();
    }
}
