package cinema.configuration;

import cinema.model.room.Room;
import cinema.model.room.Seat;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class for initialization of base object with default configuration
 * @author Patryk Lewczuk
 */
public class Initialization {

    public static Room initializeRoom(){
        Room room = new Room(9,9,new ConcurrentLinkedQueue<>());

        Queue<Seat> available_seats = room.getAvailable_seats();

        for (int i = 1; i <= room.getTotal_rows(); i++){

            for (int j = 1; j <= room.getTotal_columns(); j++){

                available_seats.add(new Seat(i, j, getPrice(i), false));
            }

        }

        room.setAvailable_seats(available_seats);

        return room;
    }

    private static int getPrice(int row) {
        if(row <= 4){
            return 10;
        }

        return 8;
    }
}
