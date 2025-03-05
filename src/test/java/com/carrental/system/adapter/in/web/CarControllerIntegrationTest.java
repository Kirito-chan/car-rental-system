package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.out.persistence.car.CarJpaEntity;
import com.carrental.system.adapter.out.persistence.car.CarRepository;
import com.carrental.system.adapter.out.persistence.customer.CustomerJpaEntity;
import com.carrental.system.adapter.out.persistence.customer.CustomerRepository;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.in.StartRentalUseCase;
import com.carrental.system.application.port.in.StopRentalUseCase;
import com.carrental.system.application.port.in.command.StartRentalCommand;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CarControllerIntegrationTest {


    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StartRentalUseCase startRentalUseCase;
    @Autowired
    private StopRentalUseCase stopRentalUseCase;

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
    }

    @Test
    void createCar_ShouldPersistInDatabaseAndReturnCreatedCar() throws Exception {
        String carJson = """
                    {
                        "make": "Toyota",
                        "model": "Corolla",
                        "totalSeats": 5,
                        "automaticTransmission": true,
                        "rented": false,
                        "totalKilometersDriven": 10000
                    }
                """;

        MvcResult result = mockMvc.perform(post("/api/car")
                                                   .contentType(MediaType.APPLICATION_JSON)
                                                   .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"))
                .andExpect(jsonPath("$.totalSeats").value(5))
                .andExpect(jsonPath("$.automaticTransmission").value(true))
                .andExpect(jsonPath("$.rented").value(false))
                .andExpect(jsonPath("$.totalKilometersDriven").value(10000))
                .andReturn();

        var content = result.getResponse().getContentAsString();
        var car = objectMapper.readValue(content, new TypeReference<Car>() {
        });

        CarJpaEntity savedCar = carRepository.findById(car.getId()).orElse(null);
        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getMake()).isEqualTo("Toyota");
        assertThat(savedCar.getModel()).isEqualTo("Corolla");
        assertThat(savedCar.getTotalSeats()).isEqualTo(5);
        assertThat(savedCar.isAutomaticTransmission()).isEqualTo(true);
        assertThat(savedCar.isRented()).isEqualTo(false);
        assertThat(savedCar.getTotalKilometersDriven()).isEqualTo(10000);
    }

    @Test
    void getCar_ShouldReturnCarFromDatabase() throws Exception {
        CarJpaEntity car = new CarJpaEntity(null, "Honda", "Civic", 5, 50000, true, true);
        car = carRepository.save(car);

        mockMvc.perform(get("/api/car/" + car.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("Honda"))
                .andExpect(jsonPath("$.model").value("Civic"))
                .andExpect(jsonPath("$.totalSeats").value(5))
                .andExpect(jsonPath("$.automaticTransmission").value(true))
                .andExpect(jsonPath("$.rented").value(true))
                .andExpect(jsonPath("$.totalKilometersDriven").value(50000));
    }

    @Test
    void getAllCars_ShouldReturnListOfCars() throws Exception {
        carRepository.saveAll(List.of(
                new CarJpaEntity(null, "BMW", "X5", 5, 70000, false, false),
                new CarJpaEntity(null, "Audi", "A4", 5, 60000, false, false)
        ));

        mockMvc.perform(get("/api/car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].car.make").value("BMW"))
                .andExpect(jsonPath("$[1].car.make").value("Audi"));
    }

    @Test
    void updateCar_ShouldModifyExistingCar() throws Exception {
        CarJpaEntity car = new CarJpaEntity(null, "Ford", "Focus", 5, 40000, false, true);
        car = carRepository.save(car);

        String updatedCarJson = """
                {
                    "id": """ + car.getId() + """
                        ,
                        "make": "Fordd",
                        "model": "Fiesta",
                        "totalSeats": 4,
                        "automaticTransmission": true,
                        "totalKilometersDriven": 45000
                    }
                """;

        mockMvc.perform(put("/api/car")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedCarJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("Fordd"))
                .andExpect(jsonPath("$.model").value("Fiesta"))
                .andExpect(jsonPath("$.totalSeats").value(4))
                .andExpect(jsonPath("$.automaticTransmission").value(true))
                .andExpect(jsonPath("$.totalKilometersDriven").value(45000));

        CarJpaEntity savedCar = carRepository.findById(car.getId()).orElse(null);
        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getMake()).isEqualTo("Fordd");
        assertThat(savedCar.getModel()).isEqualTo("Fiesta");
        assertThat(savedCar.getTotalSeats()).isEqualTo(4);
        assertThat(savedCar.isAutomaticTransmission()).isEqualTo(true);
        assertThat(savedCar.isRented()).isEqualTo(true);
        assertThat(savedCar.getTotalKilometersDriven()).isEqualTo(45000);
    }

    @Test
    void deleteCar_ShouldRemoveCarFromDatabase() throws Exception {
        CarJpaEntity car = new CarJpaEntity(null, "Mazda", "CX-5", 5, 30000, false, false);
        car = carRepository.save(car);

        mockMvc.perform(delete("/api/car/" + car.getId()))
                .andExpect(status().isOk());

        boolean exists = carRepository.existsById(car.getId());
        assertThat(exists).isFalse();
    }

    @Test
    void getTotalKilometersDriven_ShouldReturnKilometersFromDatabase() {
        var initialKilometersDriven = 120000;
        CarJpaEntity car = new CarJpaEntity(null, "Tesla", "Model S", 5, initialKilometersDriven, false, false);
        car = carRepository.save(car);

        CustomerJpaEntity customer = new CustomerJpaEntity(null, "Frank", "frank@email.com");
        customer = customerRepository.save(customer);

        var startRentalCommand = new StartRentalCommand(car.getId(), customer.getId());
        startRentalUseCase.startRental(startRentalCommand);

        var newlyDistanceDriven = 100;
        stopRentalUseCase.stopRental(car.getId(), newlyDistanceDriven);

        var updatedCar = carRepository.findById(car.getId()).orElse(null);
        assertThat(updatedCar).isNotNull();
        assertThat(updatedCar.getTotalKilometersDriven()).isEqualTo(initialKilometersDriven + newlyDistanceDriven);
    }
}
