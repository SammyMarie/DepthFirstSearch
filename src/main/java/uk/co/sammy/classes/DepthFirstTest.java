package uk.co.sammy.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;

/**
 * Created by smlif on 16/12/2015.
 */
public class DepthFirstTest {
    public static void main(String... args){
        String to, from;
        DepthFirst depths = new DepthFirst();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        depths.setup();

        try{
            out.println("From: ");
            from = reader.readLine();
            out.println("To: ");
            to = reader.readLine();

            depths.isFlight(from, to);
            if(depths.getBtStack().size() != 0)
                depths.route(to);
        }catch (IOException ioe){
            out.println("Error on input.");
        }
    }
}

