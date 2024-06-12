package com.boot.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.mysql.DB1Table;
import com.boot.mysql.Db1Repo;
import com.boot.oracle.DB2Table;
import com.boot.oracle.Db2Repo;

@RestController
@CrossOrigin
public class MyRestController {

	private Db1Repo db1Repo;
	private Db2Repo db2Repo;

	public MyRestController(Db1Repo db1Repo, Db2Repo db2Repo) {
		super();
		this.db1Repo = db1Repo;
		this.db2Repo = db2Repo;
	}

	@PostMapping("savetodb1")
	public ResponseEntity<String> saveToDB1(@RequestBody DB1Table obj) {

		DB1Table save = db1Repo.save(obj);
		boolean status = save.getId() != null;
		HttpStatus code = status ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		String msg = status ? "Success" : "failed";
		return new ResponseEntity<>(msg, code);
	}

	@PostMapping("savetodb2")
	public ResponseEntity<String> saveToDB2(@RequestBody DB2Table obj) {

		DB2Table save = db2Repo.save(obj);
		boolean status = save.getId() != null;
		HttpStatus code = status ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		String msg = status ? "Success" : "failed";
		return new ResponseEntity<>(msg, code);
	}

	@GetMapping("fetchFromDB1")
	public ResponseEntity<List<DB1Table>> dataFromDb1() {
		List<DB1Table> data = db1Repo.findAll();
		HttpStatus code = !data.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(data, code);

	}

	@GetMapping("fetchFromDB2")
	public ResponseEntity<List<DB2Table>> dataFromDb2() {
		List<DB2Table> data = db2Repo.findAll();
		System.out.println(data);
		HttpStatus code = !data.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(data, code);

	}

}
