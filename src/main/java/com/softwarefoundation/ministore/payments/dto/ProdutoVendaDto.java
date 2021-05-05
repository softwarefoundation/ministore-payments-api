package com.softwarefoundation.ministore.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.softwarefoundation.ministore.payments.entity.Produto;
import com.softwarefoundation.ministore.payments.entity.ProdutoVenda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@JsonPropertyOrder({"id","idProduto","quantidade","idVenda"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaDto extends RepresentationModel<ProdutoVendaDto> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("idProduto")
    private Long idProduto;
    @JsonProperty("quantidade")
    private Integer quantidade;
    @JsonProperty("idVenda")
    private Long idVenda;

    public ProdutoVenda toProdutoVenda(){
        return new ModelMapper().map(this , ProdutoVenda.class);
    }

}
