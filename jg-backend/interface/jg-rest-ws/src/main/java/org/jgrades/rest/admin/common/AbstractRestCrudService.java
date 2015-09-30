package org.jgrades.rest.admin.common;

import org.jgrades.admin.api.common.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractRestCrudService<T, ID, S extends CrudService<T, ID>> {
    protected final S crudService;

    protected AbstractRestCrudService(S crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insertOrUpdate(@RequestBody T entity) {
        crudService.saveOrUpdate(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> remove(@RequestParam("id") List<ID> ids) {
        crudService.removeIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    T getWithId(@PathVariable ID id) {
        return crudService.getWithId(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<T> getWithIds(@RequestParam(value = "id", required = false) List<ID> ids) {
        return ids == null ? crudService.getAll() : crudService.getWithIds(ids);
    }
}
