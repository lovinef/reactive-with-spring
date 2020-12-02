package com.practice.react.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
@SequenceGenerator(
        name = "ORDERS_SEQ_INDEX_GEN",
        sequenceName = "ORDERS_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Orders {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDERS_SEQ_INDEX_GEN"
    )
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AMOUNT")
    private int amount;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "ORDER_DATE", updatable = false)
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
