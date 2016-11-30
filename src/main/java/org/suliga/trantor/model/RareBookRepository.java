package org.suliga.trantor.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "rest_rarebook", path = "rest_rarebook")
public interface RareBookRepository extends CrudRepository<RareBook, Long> {

}