/*
 * This file is generated by jOOQ.
 */
package com.mycompany.jooq.model.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users implements Serializable {

    private static final long serialVersionUID = -535570894;

    private final Integer id;
    private final String  name;
    private final String  email;

    public Users(Users value) {
        this.id = value.id;
        this.name = value.name;
        this.email = value.email;
    }

    public Users(
        Integer id,
        String  name,
        String  email
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(email);

        sb.append(")");
        return sb.toString();
    }
}