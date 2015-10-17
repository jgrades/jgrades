/*
 * Copyright (C) 2015 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.lic.api.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jgrades.lic.api.service.LicenceCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
class LicenceAspect {
    @Autowired
    private LicenceCheckingService licenceCheckingService;

    @Pointcut(value = "execution(* *(..))")
    private void anyMethod() { //NOSONAR
    }

    @Before("anyMethod() && @annotation(checkLicence)")
    public void checkLicenceForProduct(CheckLicence checkLicence) {
        String productName = checkLicence.value();
        licenceCheckingService.checkValidForProduct(productName);
    }

    @Before("anyMethod() && @within(checkLicence)")
    public void checkLicenceForProductFOrMethod(CheckLicence checkLicence) {
        String productName = checkLicence.value();
        licenceCheckingService.checkValidForProduct(productName);
    }
}
