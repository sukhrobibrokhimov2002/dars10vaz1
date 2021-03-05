package uz.pdp.dars10vaz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars10vaz1.entity.Hotel;
import uz.pdp.dars10vaz1.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class hotelController {
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String add(@RequestBody Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Successfully added";
    }

    @GetMapping(value = "/{id}")
    public Hotel getOneById(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return new Hotel();
        return optionalHotel.get();
    }

    @GetMapping
    public List<Hotel> getAll() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {

        try {
            hotelRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }

    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel) {
        try {
            Optional<Hotel> optionalHotel = hotelRepository.findById(id);
            if (!optionalHotel.isPresent()) return "Hotel not found";
            Hotel hotel1 = optionalHotel.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Successfully edited";

        } catch (Exception e) {
            return "Error in editing";
        }
    }

}
