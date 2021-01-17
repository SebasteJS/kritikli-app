package com.example.demo.service;

import com.example.demo.dto.MusicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Music;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.type.RoleType;
import com.example.demo.repository.MusicRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public Long add(UserDto userDto) {
        List<Role> roles = userDto.getRoles();
        if (roles == null) {
            Role role = new Role(RoleType.CLIENT);
            roleRepository.save(role);
            roles = Collections.singletonList(role);
        }

        User addedUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .login(userDto.getLogin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(roles)
                .build();
        return userRepository.save(addedUser).getId();
    }

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        for (User userFind : users) {
            userDtoList.add(
                    UserDto.builder()
                            .firstName(userFind.getFirstName())
                            .lastName(userFind.getLastName())
                            .roles(userFind.getRoles())
                            .build());
        }
        return userDtoList;
    }

    public void update(UserDto userDto) {
        Optional<User> editedUser = userRepository.findById(userDto.getId());
        if (editedUser.isPresent()) {
            User user = editedUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());
            user.setRoles(userDto.getRoles());
            userRepository.save(user);
        }
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
