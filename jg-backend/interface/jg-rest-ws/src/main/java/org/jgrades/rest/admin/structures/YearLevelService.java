/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.rest.admin.structures;


import org.jgrades.admin.api.structures.YearLevelMgntService;
import org.jgrades.data.api.entities.YearLevel;
import org.jgrades.lic.api.aop.CheckLicence;
import org.jgrades.logging.JgLogger;
import org.jgrades.logging.JgLoggerFactory;
import org.jgrades.monitor.api.aop.CheckSystemDependencies;
import org.jgrades.rest.api.admin.structures.IYearLevelService;
import org.jgrades.rest.common.AbstractRestCrudPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/yearlevel", produces = MediaType.APPLICATION_JSON_VALUE)
@CheckSystemDependencies
@CheckLicence
public class YearLevelService extends AbstractRestCrudPagingService<YearLevel, Long, YearLevelMgntService> implements IYearLevelService {
    private static final JgLogger LOGGER = JgLoggerFactory.getLogger(YearLevelService.class);

    @Autowired
    protected YearLevelService(YearLevelMgntService crudService) {
        super(crudService);
    }

    @Override
    protected JgLogger getLogger() {
        return LOGGER; //NOSONAR
    }
}
