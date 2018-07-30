package com.lucasambrosi.app.api;

import com.lucasambrosi.app.api.input.UserInput;
import com.lucasambrosi.app.entity.AddressEntity;
import com.lucasambrosi.app.entity.UserEntity;
import com.lucasambrosi.app.repository.AddressRepository;
import com.lucasambrosi.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserApi {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/users/add")
    @ResponseBody
    public String addUser(@RequestBody UserInput input) {
        try {
            AddressEntity address = parseAddress(input);
            addressRepository.save(address);

            UserEntity user = parseUser(input);
            user.setAddress(address);
            userRepository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Erro ao salvar novo contato.";
        }
        return "Contato salvo com sucesso!";
    }

    private AddressEntity parseAddress(UserInput input){
        AddressEntity address = new AddressEntity();
        address.setStreet(input.getStreet());
        address.setNumber(input.getNumber());
        address.setCountry(input.getCountry());

        return address;
    }

    private UserEntity parseUser(UserInput input){
        UserEntity user = new UserEntity();
        user.setPersonName(input.getName());
        user.setPhone(input.getPhone());

        return user;
    }
}
