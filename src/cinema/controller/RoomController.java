package cinema.controller;

import cinema.configuration.Initialization;
import cinema.model.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    Room cinemaRoom = Initialization.initializeRoom();

    @GetMapping("/seats")
    public Room getSeatsInfo(){
        return cinemaRoom;
    }
}
