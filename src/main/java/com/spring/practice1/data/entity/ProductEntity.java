package com.spring.practice1.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product") //Entity를 기반으로 DB에 테이블을 생성할때 table 명을 지정해준다.
public class ProductEntity {

    @Id // JAVA에서는 Id라는 어노테이션을 통해서 productId를 PK로 만들어 준다.
    String productID;
    String productName;
    int ProductPrice;
    int productStock;

    /*
    @Column
    String sellerId;

    @Colimn
    String sellerPhoneNumber;
    */
}
