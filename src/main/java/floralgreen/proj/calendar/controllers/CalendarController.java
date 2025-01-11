package floralgreen.proj.calendar.controllers;

import floralgreen.proj.calendar.entities.Calendar;
import floralgreen.proj.calendar.services.CalendarService;
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
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/create")
    @Operation(summary = "Create a new calendar", description = "Creates a new calendar with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<Calendar> createCalendar(@RequestBody Calendar calendar) {
        Calendar createdCalendar = calendarService.createCalendar(calendar);
        return ResponseEntity.ok(createdCalendar);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find calendar by ID", description = "Returns the calendar with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar found"),
            @ApiResponse(responseCode = "404", description = "Calendar not found")
    })
    public ResponseEntity<Calendar> findCalendarById(
            @Parameter(description = "ID of the calendar to be obtained. Cannot be empty.", example = "123")
            @PathVariable("id") Long id) {
        Optional<Calendar> calendarOptional = calendarService.findActiveCalendarById(id);
        if (calendarOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendarOptional.get());
    }

    @GetMapping("/all")
    @Operation(summary = "Find all calendars", description = "Returns a list of all calendars.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of calendars retrieved"),
    })
    public ResponseEntity<List<Calendar>> findAllCalendars() {
        return ResponseEntity.ok(calendarService.findAllActiveCalendar());
    }

    @GetMapping("/allUserCalendars/{id}")
    @Operation(summary = "Find all user calendars by user ID", description = "Returns a list of all calendars associated with the specified user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user calendars retrieved"),
            @ApiResponse(responseCode = "400", description = "Input no valid")
    })
    public ResponseEntity<List<Calendar>> findAllUserCalendars(
            @Parameter(description = "ID of the user whose calendars are to be obtained.", example = "123")
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(calendarService.findAllUserCalendarsByUserId(id));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update calendar by ID", description = "Updates the calendar with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar updated successfully"),
            @ApiResponse(responseCode = "404", description = "Calendar not found")
    })
    public ResponseEntity<Calendar> updateCalendar(
            @Parameter(description = "ID of the calendar to be updated.", example = "123")
            @PathVariable Long id,
            @RequestBody Calendar calendar) {
        Optional<Calendar> calendarOptional = calendarService.updateCalendarById(id, calendar);
        if (calendarOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendarOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete calendar by ID", description = "Deletes the calendar with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Calendar not found")
    })
    public ResponseEntity<Calendar> deleteCalendar(
            @Parameter(description = "ID of the calendar to be deleted.", example = "123")
            @PathVariable Long id) {
        Optional<Calendar> calendarOptional = calendarService.deleteCalendarById(id);
        if (calendarOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendarOptional.get());
    }



}
