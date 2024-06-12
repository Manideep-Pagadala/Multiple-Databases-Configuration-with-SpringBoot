package com.boot.rest;

import org.springframework.web.bind.annotation.RestController;

import com.boot.mysql.Db1Repo;
import com.boot.oracle.Db2Repo;

@RestController
public class MyRestController {

	private Db1Repo db1Repo;
	private Db2Repo db2Repo;

	public MyRestController(Db1Repo db1Repo, Db2Repo db2Repo) {
		super();
		this.db1Repo = db1Repo;
		this.db2Repo = db2Repo;
	}

}
