package com.prabs.mongodb.gridfs.dao;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileDAO {
	void store(InputStream inputStream, String fileName, String contentType, DBObject metaData);
	Optional<GridFSDBFile> getByFilename(String filename);
	void delete(String fileName);
	List<GridFSDBFile> findAll();
        List<String> list();
}