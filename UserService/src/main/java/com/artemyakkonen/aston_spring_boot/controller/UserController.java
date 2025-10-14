package com.artemyakkonen.aston_spring_boot.controller;

import com.artemyakkonen.aston_spring_boot.dto.UserCreateDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserParamsDTO;
import com.artemyakkonen.aston_spring_boot.dto.UserUpdateDTO;
import com.artemyakkonen.aston_spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User management", description = "API for managing users")
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Get user by ID",
            description = "Returns a single user by their unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getUser(
            @Parameter(description = "User ID", example = "1", required = true)
            @PathVariable Long id){
        log.info("GET /api/users/{}", id);
        return userService.findUser(id);
    }

    @Operation(
            summary = "Get all users",
            description = """
        Returns paginated and filtered list of users.
        
        **Filtering:**
        - `name`: Filter by name (contains, case-insensitive)
        - `ageGt`: Age greater than
        - `ageLt`: Age less than
        - `createdAtGt`: Created after date
        - `createdAtLt`: Created before date
        
        **Pagination:**
        - `page`: Page number (0-based)
        - `size`: Page size
        
        **Sorting:**
        - `sortBy`: Field to sort by (id, createdAt, name, age, email)
        - `sortDirection`: Sort direction (asc, desc)
        """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO[].class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid query parameters")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserDTO> getAllUses(@Parameter(description = "Search and pagination parameters")
                             @Valid UserParamsDTO params){
        log.info("GET /api/users");
        return userService.findAllUsers(params);
    }

    @Operation(summary = "Create new user", description = "Creates a new user in the db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO createUser(@Parameter(description = "User data for creation", required = true)
                       @RequestBody @Valid UserCreateDTO dto){
        log.info("POST /api/users");
        return userService.createUser(dto);
    }

    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@Parameter(description = "User ID to delete", example = "1")
                    @PathVariable Long id){
        log.info("DELETE /api/users/{}", id);
        userService.deleteUser(id);
    }

    @Operation(summary = "Update user", description = "Partially updates an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid update data"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO updateUser(@Parameter(description = "User data for update", required = true)
                       @RequestBody @Valid UserUpdateDTO dto,
                       @Parameter(description = "User ID to update", example = "1")
                       @PathVariable Long id){
        log.info("PUT /api/users/{}", id);
        return userService.updateUser(id, dto);
    }

}
