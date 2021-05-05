package com.softwarefoundation.ministore.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.softwarefoundation.ministore.payments.entity.Produto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoDto extends RepresentationModel<ProdutoDto> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoDto create(Produto produto){
        return new ModelMapper().map(produto, ProdutoDto.class);
    }

}
