package kz.strongteam.strongteamnews.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.strongteam.strongteamnews.dto.request.TopicDtoRequest;
import kz.strongteam.strongteamnews.dto.response.TopicDtoResponse;
import kz.strongteam.strongteamnews.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/topics/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TopicController {

    private final TopicService topicService;

    @Operation(summary = "Get all topics")
    @GetMapping("")
    public ResponseEntity<List<TopicDtoResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(topicService.getAll(pageable));
    }

    @Operation(summary = "Get topic by id")
    @GetMapping("{id}")
    public ResponseEntity<TopicDtoResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(topicService.getById(id));
    }

    @Operation(summary = "Create new topic")
    @PostMapping("")
    public ResponseEntity<TopicDtoResponse> create(@RequestBody TopicDtoRequest topicDtoRequest) {
        return ResponseEntity.ok(topicService.save(topicDtoRequest));
    }

    @Operation(summary = "Update topic")
    @PutMapping("{id}")
    public ResponseEntity<TopicDtoResponse> update(@PathVariable UUID id, @RequestBody TopicDtoRequest topicDtoRequest) {
        return ResponseEntity.ok(topicService.update(id, topicDtoRequest));
    }

    @Operation(summary = "Delete topic")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
