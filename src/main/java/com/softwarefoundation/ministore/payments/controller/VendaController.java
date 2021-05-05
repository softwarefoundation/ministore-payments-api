package com.softwarefoundation.ministore.payments.controller;

import com.softwarefoundation.ministore.payments.dto.ProdutoDto;
import com.softwarefoundation.ministore.payments.dto.VendaDto;
import com.softwarefoundation.ministore.payments.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/venda")
public class VendaController {

    VendaService vendaService;
    PagedResourcesAssembler<VendaDto> assemblerPaged;

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaDto> assemblerPaged) {
        this.vendaService = vendaService;
        this.assemblerPaged = assemblerPaged;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public VendaDto findById(@PathVariable("id") Long id){
        VendaDto vendaDtoDto  = vendaService.findById(id);
        vendaDtoDto.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return vendaDtoDto;
    }

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findByAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "12") int limit,
                                       @RequestParam(value = "direction", defaultValue = "asc") String direction){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataCadastro"));
        Page<VendaDto> vendas = vendaService.findAll(pageable);
        vendas.stream().forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
        PagedModel<EntityModel<VendaDto>> pagedModel = assemblerPaged.toModel(vendas);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public VendaDto create(@RequestBody VendaDto VendaDto){
        VendaDto dto = vendaService.create(VendaDto);
        dto.add(linkTo(methodOn(VendaController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }


}
