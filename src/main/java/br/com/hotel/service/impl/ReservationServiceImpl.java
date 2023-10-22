package br.com.hotel.service.impl;

import br.com.hotel.controller.dto.ReservationRequest;
import br.com.hotel.controller.dto.ReservationResponse;
import br.com.hotel.model.Reservation;
import br.com.hotel.service.ReservationService;
import br.com.hotel.repository.ReservationRepository;
import br.com.hotel.service.mapper.ReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper mapper;
    @Autowired
    private ReservationRepository repository;

    @Override
    @Transactional
    public ReservationResponse createReservation(String userLogin, String password, ReservationRequest dto) {

        var registerProduct = this.repository.save(savedProduct(dto));
        return this.mapper.toResponse(registerProduct);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ReservationRequest>> changeReservation(String id, ReservationRequest dto) {
        Optional<Reservation> product = repository.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        product.ifPresent(s -> {
            s.setId(dto.getId());
            s.setNameGuest(dto.getNameGuest());
            s.setNumberRoom(dto.getNumberRoom());
            s.setType(dto.getType());
            s.setStatus(dto.getStatus());
            s.setValueReservation(dto.getValueReservation());
            repository.save(s);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public List<ReservationResponse> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Reservation> reservations = repository.findAll();
        return Arrays.asList(modelMapper.map(reservations, ReservationResponse[].class));
    }

    @Override
    @Transactional
    public Optional<ReservationResponse> findById(String id){
        return repository.findById(id).map(ReservationResponse::converter);
    }

    @Override
    @Transactional
    public String delete(final String id){
        repository.deleteById(id);
        return "successfully deleted";
    }

    private Reservation savedProduct (ReservationRequest p){
        return Reservation.builder()
                .id(p.getId())
                .nameGuest(p.getNameGuest())
                .numberRoom(p.getNumberRoom())
                .type(p.getType())
                .status(p.getStatus())
                .valueReservation(p.getValueReservation())
                .build();
    }

    public ReservationServiceImpl(ReservationMapper mapper) {
        this.mapper = mapper;
    }
}
