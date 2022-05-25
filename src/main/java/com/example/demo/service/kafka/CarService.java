//MINHA CAMADA DE SERVIÇO

package com.example.demo.service.kafka;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Indica que esta classe será injetada pelo spring boot e classe de serviço
@Service
public class CarService {

    //Chamei o repositorio pra ca
    //@Autowired: Injeta uma instancia do repositorio na minha classe.
    @Autowired
    private CarRepository carRepository;

    //Salva um usuario. Se for atribuido um id ele atualiza o existente. se não, ele adiciona um novo.
    public Car save(Car car){
        return carRepository.save(car);
    }

    //Retorna uma lista de usuario
    public List<Car> findAll(){
        return carRepository.findAll();
    }

}
