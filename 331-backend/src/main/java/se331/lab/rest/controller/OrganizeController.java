package se331.lab.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.service.OrganizerService;

import java.util.List;

@Controller
public class OrganizeController {

    final OrganizerService organizerService;

    public OrganizeController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping("organizer")
    public ResponseEntity<?> getOrganizeLists(@RequestParam(value = "_limit",
            required = false) Integer perPage
            , @RequestParam(value = "_page", required = false)Integer page) {
        Page<Organizer> pageOutput = organizerService.getOrganizers(perPage, page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("organizer/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);

        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/organizer")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(output);
    }
}
