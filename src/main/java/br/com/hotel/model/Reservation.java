package br.com.hotel.model;

import br.com.hotel.model.enums.StatusReservation;
import br.com.hotel.model.enums.TypeRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reservations")
public class Reservation {

    @Id
    private String id;
    private String nameGuest;
    private int numberRoom;
    private TypeRoom type;
    private BigDecimal valueReservation;
    private StatusReservation status;
}