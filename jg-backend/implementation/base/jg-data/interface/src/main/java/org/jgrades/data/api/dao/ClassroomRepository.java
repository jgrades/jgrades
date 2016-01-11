/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.data.api.dao;

import org.jgrades.data.api.entities.Classroom;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends PagingAndSortingRepository<Classroom, Long> {
}

