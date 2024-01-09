package com.group6.ads.controllers.users;


import com.group6.ads.controllers.users.models.UserOTPRequest;
import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.services.users.UserService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    ResponseEntity<Page<User>> getAlUsers(
            @RequestParam(required = false, value = "roleId")
            Integer roleId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize
    ) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(roleId,search, pageRequestCustom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserRequest user, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/checkOTP")
    public ResponseEntity<Integer> checkOTP(@RequestBody UserOTPRequest userOtpRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkOTP(userOtpRequest));
    }
    @GetMapping("FindByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }

}
