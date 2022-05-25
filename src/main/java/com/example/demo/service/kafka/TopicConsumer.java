//classe consumer: que recebe, lê


package com.example.demo.service.kafka;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ObjectInputStream;


@Slf4j
@RequiredArgsConstructor
public class TopicConsumer {
    @Value("${topic.name.consumer}")
    private String topicName;

    //kafkalistener: escuta. detecta mudança, add algo. recebe 2 parametros: topics e groupId.
    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")

    //Classe abaixo do proprio kafka: recebe chave e valor. tudo string.
    public ConsumerRecord<String, String> consume(ConsumerRecord<String, String> payload) {
        //Payload: evento, o que foi salvo
        //topic: nome
        log.info("Topic: {}", topicName);
        //chave: pesquisar
        log.info("key: {}", payload.key());
        //headers: config do conteudo
        log.info("Headers: {}", payload.headers());
        //Partição: onde o obj está alocado
        log.info("Partion: {}", payload.partition());
        //value: Proprio obj
        log.info("Order {}", payload.value());

        //Mapear o objeto de string para obj
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = null;
        try{
            //lendo o valor da classe car
            car = objectMapper.readValue(payload.value(), Car.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        //imprimindo a marca
        System.out.println(car.getBrand());

        return payload;
    }
}
