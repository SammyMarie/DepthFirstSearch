package uk.co.sammy.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.System.*;

/**
 * Created by smlif on 16/12/2015.
 */

public class DepthFirst {
    private final int MAX = 100;//maximum connections
    private FlightInfo[] flights = new FlightInfo[MAX];//This holds flight Information
    private int numFlights = 0; //number entries to the flight array
    private Stack<FlightInfo> btStack = new Stack<>();//backtrack Stack

    public Stack<FlightInfo> getBtStack() {
        return btStack;
    }

    //Initializes flight database
    public void setup() {
        addFlight("New York", "Chicago", 900);
        addFlight("Chicago", "Denver", 1000);
        addFlight("New York", "Toronto", 500);
        addFlight("New York", "Denver", 1800);
        addFlight("Toronto", "Calgary", 1700);
        addFlight("Toronto", "Los Angeles", 2500);
        addFlight("Toronto", "Chicago", 500);
        addFlight("Denver", "Urbana", 1000);
        addFlight("Denver", "Huston", 1000);
        addFlight("Huston", "Los Angeles", 1500);
        addFlight("Denver", "Los Angeles", 1000);
    }

    //Add flights to database
    private void addFlight(String from, String to, int distance) {
        if (numFlights < MAX) {
            flights[numFlights] = new FlightInfo(from, to, distance);

            numFlights++;
        } else {
            out.println("Flight database full.\n");
        }
    }

    //Shows route and total distance
    public void route(String to) {
        Stack<FlightInfo> rev = new Stack<>();
        int distance = 0;
        FlightInfo flights;
        int index = btStack.size();

        //Reverses the stack to display route.
        for (int number = 0; number < index; number++) {
            rev.push(btStack.pop());
        }

        for (int number = 0; number < index; number++) {
            flights = rev.pop();
            out.println(flights.getFrom() + " to ");
            distance += flights.getDistance();
        }
        out.println(to);
        out.println("Distance is " + distance);
    }

    //Returns distance if transit flight exist, returns 0 if none.
    public int match(String from, String to) {
        for (int number = numFlights - 1; number > -1; number--) {
            if (flights[number].getFrom().equals(from) && flights[number].getTo().equals(to) && !flights[number].isSkip()) {
                flights[number].setSkip(true);//prevents reuse
                return flights[number].getDistance();
            }
        }
        return 0;//if none found
    }

    //Given from, finds any connection.
    public FlightInfo find(String from) {
        for (int number = 0; number < numFlights; number++) {
            if (flights[number].getFrom().equals(from) && !flights[number].isSkip()) {
                FlightInfo flight = new FlightInfo(flights[number].getFrom(),
                        flights[number].getTo(), flights[number].getDistance());

                flights[number].setSkip(true);//prevents reuse
                return flight;
            }
        }
        return null;
    }

    //Determines if there's a route between from and to.
    public void isFlight(String from, String to) {
        int distance;
        FlightInfo flights;

        //Sees if at destination.
        distance = match(from, to);
        if (distance != 0) {
            btStack.push(new FlightInfo(from, to, distance));
            return;
        }

        //Tries another connection.
        flights = find(from);
        if (flights != null) {
            btStack.push(new FlightInfo(from, to, flights.getDistance()));
            isFlight(flights.getTo(), to);
        } else if (btStack.size() > 0) {
            //Backtracks and tries another connection.
            flights = btStack.pop();
            isFlight(flights.getFrom(), flights.getTo());
        }
    }
}
