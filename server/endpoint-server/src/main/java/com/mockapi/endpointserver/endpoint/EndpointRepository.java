package com.mockapi.endpointserver.endpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint,Long>  {
    @Query("SELECT e FROM Endpoint e WHERE e.projectId = ?1 AND e.path = ?2 AND e.httpRequest = ?3")
    List<Endpoint> custWork(String projectId, String path, Endpoint.HttpRequest httpRequest);

}
