package cinema.controller;

import cinema.configuration.Initialization;
import cinema.model.Room;
import cinema.model.Seat;
import cinema.model.error.Error;
import cinema.model.error.ErrorBean;
import cinema.model.purchase.Purchase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                    ErrorBean errorBean = new ErrorBean(Error.TICKET_ALREADY_PURCHASED.toString());
                    return new ResponseEntity<>(errorBean, HttpStatus.BAD_REQUEST);
                }
                seatFromIterator.setSold(true);
                Purchase currentPurchase = new Purchase(seatFromIterator);
                purchases.add(currentPurchase);

                return new ResponseEntity<>(currentPurchase, HttpStatus.OK);

            }
        }
        ErrorBean errorBean = new ErrorBean(Error.OUT_OF_BOUND.toString());
        return new ResponseEntity<>(errorBean, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) {
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
