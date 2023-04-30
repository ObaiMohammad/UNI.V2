package com.unipd.universityautomationsystemv2.Services;

import com.unipd.universityautomationsystemv2.Exceptions.EntityNotFoundException;
import com.unipd.universityautomationsystemv2.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.unipd.universityautomationsystemv2.model.User;
import com.unipd.universityautomationsystemv2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServices {
    private final UserRepository repository;

    @Autowired
    public UserServices(UserRepository repository) {
        this.repository = repository;
    }


    public User create(UserModel user) {
        var newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        return repository.save(newUser);
    }

    public User findById(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not exist with id :" + id));
    }

    public List<User> findAll() {
        return repository.findAll().stream().sorted(Comparator.comparing(User::getId)).toList();
    }

    public User patchOne(long id, JsonPatch patch) {
        var product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not exist with id :" + id));
        var productPatched = applyPatchToUser(patch, product);
        return repository.save(productPatched);
    }

    private User applyPatchToUser(JsonPatch patch, User product) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
            return objectMapper.treeToValue(patched, User.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(long id) {
        repository.deleteById(id);
        return true ;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }
    public User updateOne(long id, UserModel user) {

        User oldUSer = findById(id);

        oldUSer.setFirstName(user.getFirstName());
        oldUSer.setLastName(user.getLastName());
        oldUSer.setRole(user.getRole());
        oldUSer.setEmail(user.getEmail());

        User updatedUser = repository.save(oldUSer);
        return updatedUser;
    }

}
