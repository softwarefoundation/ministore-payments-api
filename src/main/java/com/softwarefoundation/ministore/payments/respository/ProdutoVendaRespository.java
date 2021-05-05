package com.softwarefoundation.ministore.payments.respository;

import com.softwarefoundation.ministore.payments.entity.ProdutoVenda;
import com.softwarefoundation.ministore.payments.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoVendaRespository extends JpaRepository<ProdutoVenda,Long> {
}
