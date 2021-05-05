package com.softwarefoundation.ministore.payments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.softwarefoundation.ministore.payments.entity.Produto;
import com.softwarefoundation.ministore.payments.entity.ProdutoVenda;
import com.softwarefoundation.ministore.payments.entity.Venda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonPropertyOrder({"id","dataCadastro","produtos","valorTotal"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaDto extends RepresentationModel<VendaDto> implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonProperty("dataCadastro")
    private Date dataCadastro;

    @JsonProperty("produtos")
    private List<ProdutoVendaDto> produtos = new ArrayList<>();

    @JsonProperty("valorTotal")
    private Double valorTotal;

    public Venda toVenda(){
        return new ModelMapper().map(this, Venda.class);
    }

}
