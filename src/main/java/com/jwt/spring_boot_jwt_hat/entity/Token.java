package com.jwt.spring_boot_jwt_hat.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_token")
@Getter
@Setter
public class Token  extends BaseEntity{
    @Column(length = 1000)
    private String toekn;

    private Date tokenExDate;
}
