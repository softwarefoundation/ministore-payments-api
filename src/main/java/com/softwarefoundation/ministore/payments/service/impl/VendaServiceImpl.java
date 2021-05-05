package com.softwarefoundation.ministore.payments.service.impl;

import com.softwarefoundation.ministore.payments.dto.VendaDto;
import com.softwarefoundation.ministore.payments.entity.ProdutoVenda;
import com.softwarefoundation.ministore.payments.entity.Venda;
import com.softwarefoundation.ministore.payments.exceptions.ResourceNotFoundException;
import com.softwarefoundation.ministore.payments.respository.ProdutoRespository;
import com.softwarefoundation.ministore.payments.respository.ProdutoVendaRespository;
import com.softwarefoundation.ministore.payments.respository.VendaRespository;
import com.softwarefoundation.ministore.payments.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {

    private VendaRespository vendaRespository;
    private ProdutoVendaRespository produtoVendaRespository;

    @Autowired
    public VendaServiceImpl(VendaRespository vendaRespository, ProdutoVendaRespository produtoVendaRespository) {
        this.vendaRespository = vendaRespository;
        this.produtoVendaRespository = produtoVendaRespository;
    }

    @Override
    public VendaDto create(VendaDto vendaDto) {
        Venda venda = vendaRespository.save(vendaDto.toVenda());

        List<ProdutoVenda> produtos = new ArrayList<>();
        vendaDto.getProdutos().forEach( p -> {
            ProdutoVenda produtoVenda = p.toProdutoVenda();
            produtoVenda.setVenda(venda);
            ProdutoVenda produtoVendaRetorno = produtoVendaRespository.save(produtoVenda);
            produtos.add(produtoVendaRetorno);
        });
        venda.setProdutos(produtos);
        return venda.toVendaDto();
    }

    @Override
    public Page<VendaDto> findAll(Pageable pageable) {
        var page = vendaRespository.findAll(pageable);
        return page.map( p -> p.toVendaDto());
    }

    public VendaDto findById(Long id) {
        var venda = vendaRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return venda.toVendaDto();
    }

}
