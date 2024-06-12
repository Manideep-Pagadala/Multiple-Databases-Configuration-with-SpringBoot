package com.boot.mysql;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Db1Repo extends JpaRepository<DB1Table, Serializable>{

}
