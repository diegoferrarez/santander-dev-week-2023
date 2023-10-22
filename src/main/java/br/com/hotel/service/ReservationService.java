package br.com.hotel.service;

import br.com.hotel.controller.dto.ReservationRequest;
import br.com.hotel.controller.dto.ReservationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

   ReservationResponse createReservation(String userLogin, String password, ReservationRequest request);
   ResponseEntity<Optional<ReservationRequest>> changeReservation(String id, ReservationRequest request);
   List<ReservationResponse> findAll();
   Optional<ReservationResponse> findById(String id);
   String delete(String id);

}
