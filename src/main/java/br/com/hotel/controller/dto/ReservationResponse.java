package br.com.hotel.controller.dto;

import br.com.hotel.model.Reservation;
import br.com.hotel.model.enums.StatusReservation;
import br.com.hotel.model.enums.TypeRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

    @Id
    private String id;
    private String nameGuest;
    private int numberRoom;
    private TypeRoom type;
    private BigDecimal valueReservation;
    private StatusReservation status;

    public static ReservationResponse converter(Reservation c){
        return ReservationResponse.builder()
                .id(c.getId())
                .nameGuest(c.getNameGuest())
                .numberRoom(c.getNumberRoom())
                .type(c.getType())
                .valueReservation(c.getValueReservation())
                .status(c.getStatus())
                .build();
    }
}