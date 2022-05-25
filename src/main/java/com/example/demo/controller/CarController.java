package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.kafka.CarService;
import com.example.demo.service.kafka.TopicProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//selforJ: Injeta uma intancia de log. Vai pro arquivo de log para controle da aplicação
@Slf4j
@RestController
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    private TopicProducer topicProducer;

    @Autowired
    private CarService carService;

    @PostMapping
    public void addQueueCars(@RequestBody Car car) {
        topicProducer.send(car);
        log.info("Car stored in queue kafka");
        carService.save(car);
        log.info("Car stored in mysql");
    }
}
