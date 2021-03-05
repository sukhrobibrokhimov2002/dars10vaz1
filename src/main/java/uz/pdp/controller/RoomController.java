package uz.pdp.dars10vaz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars10vaz1.entity.Hotel;
import uz.pdp.dars10vaz1.entity.Room;
import uz.pdp.dars10vaz1.payload.RoomDto;
import uz.pdp.dars10vaz1.repository.HotelRepository;
import uz.pdp.dars10vaz1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public String add(@RequestBody RoomDto roomDto) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotelId());
        if (!hotelOptional.isPresent()) return "Hotel not found";
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setFloor(roomDto.getFloor());
        room.setHotel(hotelOptional.get());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "Successfully saved";
    }

    @GetMapping
    public List<Room> getAll() {
        List<Room> all = roomRepository.findAll();

        return all;
    }

    @GetMapping("/{id}")
    public Room getOneById(@PathVariable Integer id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (!roomOptional.isPresent()) return new Room();
        return roomOptional.get();
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            roomRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e) {
            return "Failed in editing";
        }
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        try {
            Optional<Room> roomOptional = roomRepository.findById(id);

            if (!roomOptional.isPresent()) return "Room not found";

            Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotelId());
            if (!hotelOptional.isPresent()) return "Hotel not found";
            Room room = roomOptional.get();
            room.setSize(roomDto.getSize());
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setFloor(roomDto.getFloor());
            room.setHotel(hotelOptional.get());
            roomRepository.save(room);

            return "Successfully edited";
        } catch (Exception e) {
            return "Failed in editing";
        }
    }

    @GetMapping("byHotelId/{id}")
    public Page<Room> getByHotelId(@PathVariable Integer id, @RequestParam Integer page) {

        Pageable pageable=PageRequest.of(page,10);
        Page<Room> byHotel_id = roomRepository.findByHotel_Id(id,pageable);
        return byHotel_id;

    }
}
