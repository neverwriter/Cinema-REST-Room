package cinema.service.stats;

import cinema.model.purchase.Purchase;
import cinema.model.room.Room;
import cinema.model.room.Seat;
import cinema.model.stats.Stats;

import java.util.concurrent.ConcurrentLinkedQueue;

public class StatisticsUtil {

    private Stats stats;

    public StatisticsUtil() {
    }

    public Stats calculateStatistics(Room cinemaRoom) {

        int currentIncome = 0;
        int available_seats = cinemaRoom.getAvailable_seats().size();
        int purchased_tickets = 0;
        for (Seat seat : cinemaRoom.getAvailable_seats()) {
            if (seat.isSold()) {
                currentIncome += seat.getPrice();
                available_seats--;
                purchased_tickets++;
            }

            setStats(new Stats(currentIncome, available_seats, purchased_tickets));
        }

        return getStats();
    }

    // Getters and setters

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
