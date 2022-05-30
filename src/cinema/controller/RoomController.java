package cinema.controller;

import cinema.configuration.AppConfiguration;
import cinema.configuration.Initialization;
import cinema.model.purchase.Token;
import cinema.model.room.ReturnedTicket;
import cinema.model.room.Room;
import cinema.model.room.Seat;
import cinema.model.error.Error;
import cinema.model.error.ErrorBean;
import cinema.model.purchase.Purchase;
import cinema.service.stats.StatisticsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * REST controller for handling request connected to Cinema Room
 */
@RestController
public class RoomController {

    Room cinemaRoom = Initialization.initializeRoom();

    ConcurrentLinkedQueue<Purchase> purchases = new ConcurrentLinkedQueue<>();

    @GetMapping("/seats")
    public Room getSeatsInfo() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {

        for (Seat seatFromIterator : cinemaRoom.getAvailable_seats()) {
            if (seat.getRow() == seatFromIterator.getRow()
                    && seat.getColumn() == seatFromIterator.getColumn()) {
                if (seatFromIterator.isSold()) {

                    return new ResponseEntity<>(
                            new ErrorBean(Error.TICKET_ALREADY_PURCHASED.toString()),
                            HttpStatus.BAD_REQUEST);
                }
                seatFromIterator.setSold(true);
                Purchase currentPurchase = new Purchase(seatFromIterator);
                purchases.add(currentPurchase);

                return new ResponseEntity<>(currentPurchase, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(
                new ErrorBean(Error.OUT_OF_BOUND.toString()),
                HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {


        for (Purchase purchase : purchases) {
            if (purchase.getToken().equals(token.getToken())) {

                if (returnTicket(purchase.getTicket())) {
                    ReturnedTicket returnedTicket = new ReturnedTicket(purchase.getTicket());
                    purchases.remove(purchase);
                    return new ResponseEntity<>(returnedTicket, HttpStatus.OK);
                }

                return new ResponseEntity<>(
                        new ErrorBean(Error.TICKET_NOT_RETURN.toString()),
                        HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }

        return new ResponseEntity<>(
                new ErrorBean(Error.WRONG_TOKEN.toString()),
                HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStatistics(@RequestParam(required = false) String password) {

        if (password == null) {
            return new ResponseEntity<>(new ErrorBean(Error.WRONG_PASSWORD.toString()), HttpStatus.UNAUTHORIZED);
        }

        if (AppConfiguration.isPasswordCorrect(password)) {
            StatisticsUtil statisticsUtil = new StatisticsUtil();

            return new ResponseEntity<>(statisticsUtil.calculateStatistics(cinemaRoom), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ErrorBean(Error.WRONG_PASSWORD.toString()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Method responsible for changing status on seat for which ticket is returned
     *
     * @param seat - Seat for which ticket is returned.
     * @return - boolean information if status change was successful
     */
    private boolean returnTicket(Seat seat) {
        for (Seat seatFromIterator : cinemaRoom.getAvailable_seats()) {
            if (seat.getRow() == seatFromIterator.getRow()
                    && seat.getColumn() == seatFromIterator.getColumn()) {
                if (seatFromIterator.isSold()) {
                    seatFromIterator.setSold(false);

                    return true;
                }
            }
        }
        return false;
    }


}
