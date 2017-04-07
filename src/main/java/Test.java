import org.neo4j.driver.v1.*;
import org.apache.commons.lang.time.StopWatch;

import java.io.IOException;


/**
 *
 * @author Rumyana
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Driver driver = GraphDatabase.driver(
                "bolt://localhost:3030",
                AuthTokens.basic( "neo4j", "class" ) );
        Session session = driver.session();
        
          StatementResult result = session.run(
            "MATCH (p:Person)-[r:ENDORSES]->(e:Person)" +
                    "WHERE p.id = "+ "100" +
                    " RETURN e.name as name");
          
          System.out.println(result.next());
    }
}
