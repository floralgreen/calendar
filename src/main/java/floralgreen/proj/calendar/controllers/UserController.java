package floralgreen.proj.calendar.controllers;

import floralgreen.proj.calendar.entities.User;
import floralgreen.proj.calendar.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find user by ID", description = "Returns the active user with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> findActiveUserById(
            @Parameter(description = "ID of the user to be obtained. Cannot be empty.", example = "123")
            @PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findActiveUserById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @GetMapping("/all")
    @Operation(summary = "Find all active users", description = "Returns a list of all active users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of active users retrieved"),
    })
    public ResponseEntity<List<User>> findAllActiveUsers() {
        return ResponseEntity.ok(userService.findAllActiveUsers());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update user by ID", description = "Updates the user with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to be updated.", example = "123")
            @PathVariable Long id,
            @RequestBody User user) {
        Optional<User> userOptional = userService.updateUserById(id, user);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes the user with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> deleteUser(
            @Parameter(description = "ID of the user to be deleted.", example = "123")
            @PathVariable Long id) {
        Optional<User> userOptional = userService.deleteUserById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userOptional.get());
    }
}


