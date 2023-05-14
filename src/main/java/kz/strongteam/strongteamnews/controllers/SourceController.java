package kz.strongteam.strongteamnews.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.strongteam.strongteamnews.dto.request.SourceDtoRequest;
import kz.strongteam.strongteamnews.dto.response.SourceDtoResponse;
import kz.strongteam.strongteamnews.services.interfaces.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/sources/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SourceController {

    private final SourceService sourceService;

    @Operation(summary = "Get all sources")
    @GetMapping("")
    public ResponseEntity<List<SourceDtoResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sourceService.getAll(pageable));
    }

    @Operation(summary = "Get source by id")
    @GetMapping("{id}")
    public ResponseEntity<SourceDtoResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(sourceService.getById(id));
    }

    @Operation(summary = "Create new source")
    @PostMapping("")
    public ResponseEntity<SourceDtoResponse> create(@RequestBody SourceDtoRequest sourceDtoRequest) {
        return ResponseEntity.ok(sourceService.save(sourceDtoRequest));
    }

    @Operation(summary = "Update source")
    @PutMapping("{id}")
    public ResponseEntity<SourceDtoResponse> update(@PathVariable UUID id, @RequestBody SourceDtoRequest sourceDtoRequest) {
        return ResponseEntity.ok(sourceService.update(id, sourceDtoRequest));
    }

    @Operation(summary = "Delete source")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        sourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
