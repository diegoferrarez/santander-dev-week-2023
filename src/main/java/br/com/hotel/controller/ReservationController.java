package br.com.hotel.controller;

import br.com.hotel.controller.dto.ReservationRequest;
import br.com.hotel.controller.dto.ReservationResponse;
import br.com.hotel.service.ReservationService;
import br.com.hotel.utils.UserConstants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @ApiOperation(value = "Busca todos as reservas de hóspedes")
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponse> getall(){
        return reservationService.findAll();
    }

    @ApiOperation(value = "Busca as reservas por id")
    @GetMapping("/findby/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<ReservationResponse> getById(@PathVariable String id){
        return reservationService.findById(id);
    }

    @ApiOperation(value="Registra uma reserva dentro do sistema")
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse create(@RequestHeader(name = UserConstants.USER_SIGN_HEADER)String userLogin,
                                      @RequestHeader(name = UserConstants.USER_PASS_HEADER)String password,
                                      @RequestBody ReservationRequest request){
        return reservationService.createReservation(userLogin, password, request);
    }

    @ApiOperation(value = "Altera a informação de uma reserva")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<ReservationRequest>> update(@PathVariable String id, @RequestBody ReservationRequest request){
        return reservationService.changeReservation(id, request);
    }

    @ApiOperation(value="Deletar uma reserva pelo id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteAccount(@PathVariable final String id){
        return reservationService.delete(id);
    }
}
