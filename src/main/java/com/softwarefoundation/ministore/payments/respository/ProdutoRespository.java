package com.softwarefoundation.ministore.payments.respository;

import com.softwarefoundation.ministore.payments.entity.Produto;
import com.softwarefoundation.ministore.payments.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRespository extends JpaRepository<Produto,Long> {
}
