//classe producer: que envia os dados para o kafka

package com.example.demo.service.kafka;

import com.example.demo.model.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;


@Slf4j
@RequiredArgsConstructor
@Service
public class TopicProducer {
    //value abaixo pega o nome do topico, dentro do nosso arquivo de propriedades
    @Value("${topic.name.producer}")
    private String topicName;

    //Kafka template: objeto usado para salvar os dados no kafka
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void send(Car message) {
        //Mapear o objeto de string para obj
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //salvando valor do objeto como uma string
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(message));
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("Sended payload: {}", message);
        //No topico name envia a mensagem (atributo carro).
    }
}
