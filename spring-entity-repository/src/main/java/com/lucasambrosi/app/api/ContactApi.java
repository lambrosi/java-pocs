package com.lucasambrosi.app.api;

import com.lucasambrosi.app.entity.ContactEntity;
import com.lucasambrosi.app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ContactApi {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts/{id}")
    @ResponseBody
    public String searchById(@PathVariable(value = "id") Integer id){
        try {
            Optional<ContactEntity> contact = contactRepository.findById(id);
            if(contact.isPresent()) return contact.get().toString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return "Contato não encontrado!";
    }

    @GetMapping("/contacts/name/{name}")
    @ResponseBody
    public String searchByName(@PathVariable(value = "name") String name){
        try{
            List<ContactEntity> contactList = contactRepository.findByName(name);
            if(contactList.size() > 1)
                return treatMultipleResults(contactList);
            return contactList.get(0).toString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return "Contato não encontrado!";
    }

    @PostMapping("/contacts/delete/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Integer id){
        try {
            contactRepository.deleteById(id);
            return "Contact " + id + " removed!";
        }catch (EmptyResultDataAccessException emptyResult){
            return "Usuario de id " + id + " não encontrado!";
        }catch (Exception ex){
            ex.printStackTrace();
            return "Erro: " + ex.getMessage();
        }
    }

    private String treatMultipleResults(List<ContactEntity> contactList){
        StringBuilder stringBuilder = new StringBuilder()
                .append(contactList.size()).append(" resultados encontrados!\n");
        contactList.forEach(contact -> stringBuilder.append(contact.toString()).append("\n\n"));
        return stringBuilder.toString();
    }
}
