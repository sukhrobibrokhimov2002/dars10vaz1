package uz.pdp.dars10vaz1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars10vaz1.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {


    Page<Room> findByHotel_Id(Integer hotel_id, Pageable pageable);
}
