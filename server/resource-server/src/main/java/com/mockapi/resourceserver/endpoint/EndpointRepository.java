package com.mockapi.resourceserver.endpoint;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepository extends JpaRepository<Endpoint,Long> {
}
