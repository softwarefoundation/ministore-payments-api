package com.softwarefoundation.ministore.payments.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB02_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ESTOQUE")
    private Integer estoque;

}
