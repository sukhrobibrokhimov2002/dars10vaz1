package uz.pdp.dars10vaz1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Integer roomNumber;
    private String floor;
    private String size;
    private Integer hotelId;
}
