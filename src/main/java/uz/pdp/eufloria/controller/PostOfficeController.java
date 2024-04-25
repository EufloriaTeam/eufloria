package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.PostOfficeDto;
import uz.pdp.eufloria.entity.PostOffice;
import uz.pdp.eufloria.service.PostOfficeService;

@RestController
@RequestMapping(AppConstants.BASE_PATH + PostOfficeController.BASE_URL)
@RequiredArgsConstructor
public class PostOfficeController {
    public static final String BASE_URL = "/post";
    private final PostOfficeService service;

    @PostMapping
    public ApiResponse<PostOffice> create(@RequestBody PostOfficeDto postOfficeDto) {
        return ApiResponse.body(service.create(postOfficeDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<PostOffice> get(@PathVariable String id) {
        return ApiResponse.body(service.get(id));
    }

    @GetMapping
    public Page<PostOffice> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        return service.getAll(predicate, pageable);
    }

}
