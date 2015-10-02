/*
 * Copyright (C) 2015 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.lic.api.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "address", "phone"})
@Data
public final class Customer {
    @XmlAttribute(name = "id")
    private Long id;

    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "phone")
    private String phone;
}
