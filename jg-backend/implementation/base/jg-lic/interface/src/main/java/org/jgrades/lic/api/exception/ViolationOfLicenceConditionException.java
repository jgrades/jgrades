/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.lic.api.exception;

public class ViolationOfLicenceConditionException extends LicenceException {
    public ViolationOfLicenceConditionException(Exception e) {
        super(e);
    }
}
