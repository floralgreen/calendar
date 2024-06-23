package floralgreen.proj.calendar.controllers;

import floralgreen.proj.calendar.entities.Event;
import floralgreen.proj.calendar.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @Operation(summary = "Create a new event", description = "Creates a new event with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find event by ID", description = "Returns the event with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<Optional<Event>> findEventById(
            @Parameter(description = "ID of the event to be obtained. Cannot be empty.", example = "123")
            @PathVariable("id") Long id) {
        Optional<Event> eventOptional = eventService.findEventById(id);
        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eventOptional);
    }

    @GetMapping("/all")
    @Operation(summary = "Find all events", description = "Returns a list of all events.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of events retrieved"),
    })
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok(eventService.getAllActiveEvents());
    }

    @GetMapping("/allCalendarEvents/{calendarId}")
    @Operation(summary = "Find all events for a calendar by ID", description = "Returns a list of all events associated with the specified calendar ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of calendar events retrieved"),
            @ApiResponse(responseCode = "400", description = "Input not valid")
    })
    public ResponseEntity<List<Event>> findAllCalendarEvents(
            @Parameter(description = "ID of the calendar whose events are to be obtained.", example = "123")
            @PathVariable Long calendarId){
        return ResponseEntity.ok(eventService.findAllCalendarEventsByCalendarId(calendarId));
    }

    @GetMapping("/allDayEvents/{calendardId}")
    @Operation(summary = "Find all events for a calendar within a date range", description = "Returns a list of all events associated with the specified calendar ID that fall within the given date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of calendar events retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<Event>> findAllCalendarEventsWithinRange(
            @Parameter(description = "ID of the calendar whose events are to be obtained.", example = "123")
            @PathVariable Long calendardId,
            @Parameter(description = "The start date and time of the range.", example = "2023-06-01T00:00:00Z")
            @RequestParam OffsetDateTime fromDate,
            @Parameter(description = "The end date and time of the range.", example = "2023-06-30T23:59:59Z")
            @RequestParam OffsetDateTime toDate){
        List<Event> eventsFound = eventService.findAllEventsByCalendarIdWithinRange(calendardId, fromDate, toDate);
        return ResponseEntity.ok(eventsFound);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update event by ID", description = "Updates the event with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<Optional<Event>> updateEvent(
            @Parameter(description = "ID of the event to be updated.", example = "123")
            @PathVariable Long id,
            @RequestBody Event event) {
        Optional<Event> eventOptional = eventService.updateEvent(id, event);
        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eventOptional);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete event by ID", description = "Deletes the event with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<Optional<Event>> deleteEvent(
            @Parameter(description = "ID of the event to be deleted.", example = "123")
            @PathVariable Long id) {
        Optional<Event> eventOptional = eventService.deleteEventById(id);
        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eventOptional);
    }

}
