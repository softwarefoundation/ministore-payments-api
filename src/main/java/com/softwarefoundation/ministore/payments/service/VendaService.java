package com.softwarefoundation.ministore.payments.service;

import com.softwarefoundation.ministore.payments.dto.VendaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendaService {

    VendaDto create(VendaDto vendaDto);

    Page<VendaDto> findAll(Pageable pageable);

    public VendaDto findById(Long id);
}
