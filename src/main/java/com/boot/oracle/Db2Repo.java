package com.boot.oracle;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Db2Repo extends JpaRepository<DB2Table, Serializable> {

}
