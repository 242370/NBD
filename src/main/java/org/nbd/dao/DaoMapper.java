package org.nbd.dao;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.datastax.oss.driver.api.mapper.MapperBuilder;

@Mapper
public interface DaoMapper {
    @DaoFactory
    ClientDao getClientDao(@DaoKeyspace CqlIdentifier keyspace);

    @DaoFactory
    AccommodationDao getAccommodationDao(@DaoKeyspace CqlIdentifier keyspace);
}
