package kz.strongteam.strongteamnews.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.strongteam.strongteamnews.dto.request.NewsDtoRequest;
import kz.strongteam.strongteamnews.dto.response.NewsDtoResponse;
import kz.strongteam.strongteamnews.services.interfaces.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/news/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Get all news")
    @GetMapping("")
    public ResponseEntity<List<NewsDtoResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(newsService.getAll(pageable));
    }

    @Operation(summary = "Get news by id")
    @GetMapping("{id}")
    public ResponseEntity<NewsDtoResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @Operation(summary = "Get all news by source id")
    @GetMapping("source/{sourceId}")
    public ResponseEntity<List<NewsDtoResponse>> getBySourceId(
            @PathVariable UUID sourceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(newsService.getNewsBySourceId(sourceId, pageable));
    }

    @Operation(summary = "Get all news by topic id")
    @GetMapping("topic/{topicId}")
    public ResponseEntity<List<NewsDtoResponse>> getByTopicId(
            @PathVariable UUID topicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(newsService.getNewsByTopicId(topicId, pageable));
    }

    @Operation(summary = "Create new news")
    @PostMapping("")
    public ResponseEntity<NewsDtoResponse> create(@RequestBody NewsDtoRequest newsDtoRequest) {
        return ResponseEntity.ok(newsService.save(newsDtoRequest));
    }

    @Operation(summary = "Update news")
    @PutMapping("{id}")
    public ResponseEntity<NewsDtoResponse> update(@PathVariable UUID id, @RequestBody NewsDtoRequest newsDtoRequest) {
        return ResponseEntity.ok(newsService.update(id, newsDtoRequest));
    }

    @Operation(summary = "Delete news")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        newsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
