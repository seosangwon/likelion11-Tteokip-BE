package com.example.koun.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order {


    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name="delivery_status")
    private DeliveryStatus deliveryStatus;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;

    @Builder
    public Order(LocalDateTime orderDate, DeliveryStatus deliveryStatus, String address, User user,Raffle raffle
        ) {
        this.orderDate = orderDate;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        setUser(user);
        setRaffle(raffle);


    }


    //==연관관계메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
        raffle.setOrder(this);

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryStatus=" + deliveryStatus +
                ", address='" + address + '\'' +
                ", user=" + user.getUserName() +  // assuming User class has a getUserName() method
                ", raffle=" + raffle.getId() +    // assuming Raffle class has a getId() method
                '}';
    }




}
