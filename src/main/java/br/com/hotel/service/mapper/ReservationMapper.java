package br.com.hotel.service.mapper;

import br.com.hotel.controller.dto.ReservationResponse;
import br.com.hotel.model.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public ReservationResponse toResponse(Reservation reservation) {
        var json = this.mapper.writeValueAsString(reservation);
        return this.mapper.readValue(json, ReservationResponse.class);
    }
}
