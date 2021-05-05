package com.softwarefoundation.ministore.payments.entity;

import com.softwarefoundation.ministore.payments.dto.VendaDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB01_VENDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = {CascadeType.REFRESH})
    private List<ProdutoVenda> produtos;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    public VendaDto toVendaDto(){
        return new ModelMapper().map(this, VendaDto.class);
    }

}
