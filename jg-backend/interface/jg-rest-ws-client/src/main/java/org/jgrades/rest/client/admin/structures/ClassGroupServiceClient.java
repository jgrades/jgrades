/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.rest.client.admin.structures;

import org.jgrades.data.api.entities.ClassGroup;
import org.jgrades.data.api.entities.roles.StudentDetails;
import org.jgrades.rest.api.admin.structures.IClassGroupService;
import org.jgrades.rest.client.StatefullRestTemplate;
import org.jgrades.rest.client.common.RestCrudPagingServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class ClassGroupServiceClient extends RestCrudPagingServiceClient<ClassGroup, Long>
        implements IClassGroupService {
    @Autowired
    public ClassGroupServiceClient(@Value("${rest.backend.base.url}") String backendBaseUrl,
                                   StatefullRestTemplate restTemplate) {
        super(backendBaseUrl, "/classgroup", restTemplate);
    }

    @Override
    public ClassGroup getWithId(Long id) {
        String serviceUrl = crudUrl + "/" + id.toString();
        ResponseEntity<ClassGroup> response = restTemplate.exchange(backendBaseUrl + serviceUrl,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<ClassGroup>() {
                });
        return response.getBody();
    }

    @Override
    public List<ClassGroup> getWithIds(List<Long> ids) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.putIfAbsent("id", ids.stream().map(e -> e.toString()).collect(toList()));
        URI uri = UriComponentsBuilder.fromHttpUrl(backendBaseUrl + crudUrl)
                .queryParams(map)
                .build().encode().toUri();
        ResponseEntity<List<ClassGroup>> response = restTemplate.exchange(uri,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<ClassGroup>>() {
                });
        return response.getBody();
    }

    @Override
    public List<ClassGroup> getAll() {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendBaseUrl + crudUrl)
                .build().encode().toUri();
        ResponseEntity<List<ClassGroup>> response = restTemplate.exchange(uri,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<ClassGroup>>() {
                });
        return response.getBody();
    }

    @Override
    public Page<ClassGroup> getPage(Integer number, Integer size) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendBaseUrl + crudUrl)
                .queryParam("page", number)
                .queryParam("limit", size)
                .build().encode().toUri();
        ResponseEntity<Page<ClassGroup>> response = restTemplate.exchange(uri,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Page<ClassGroup>>() {
                });
        return response.getBody();
    }

    @Override
    public void setStudents(Long id, Set<StudentDetails> students) {
        String serviceUrl = backendBaseUrl + crudUrl + "/" + id + "/students";
        HttpEntity<Set<StudentDetails>> entity = new HttpEntity<>(students);
        restTemplate.exchange(serviceUrl, HttpMethod.POST, entity, Void.class);
    }

    @Override
    public Set<StudentDetails> getStudents(Long id) {
        String serviceUrl = backendBaseUrl + crudUrl + "/" + id + "/students";
        ResponseEntity<Set<StudentDetails>> response = restTemplate.exchange(serviceUrl,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Set<StudentDetails>>() {
                });
        return response.getBody();
    }

    @Override
    public List<ClassGroup> getFromActiveSemester() {
        String serviceUrl = backendBaseUrl + crudUrl + "/active";
        ResponseEntity<List<ClassGroup>> response = restTemplate.exchange(serviceUrl,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<ClassGroup>>() {
                });
        return response.getBody();
    }
}
