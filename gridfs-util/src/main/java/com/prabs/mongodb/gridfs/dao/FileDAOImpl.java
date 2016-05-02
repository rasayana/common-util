package com.prabs.mongodb.gridfs.dao;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

@Component
public class FileDAOImpl implements FileDAO {
    private static Query getFilenameQuery(String name) {
        return Query.query(GridFsCriteria.whereFilename().is(name));
    }
    @Autowired private GridFsTemplate gridFsTemplate;
    @Override
    public void store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
        gridFsTemplate.store(inputStream, fileName, contentType, metaData).save();
    }
    @Override
    public Optional<GridFSDBFile> getByFilename(String fileName) {
        return Optional.ofNullable(gridFsTemplate.findOne(getFilenameQuery(fileName)));
    }
    @Override
    public List<GridFSDBFile> findAll() {
        return gridFsTemplate.find(null);
    }
    @Override
    public void delete(String fileName) {
        gridFsTemplate.delete(getFilenameQuery(fileName));
    }
    @Override
    public List<String> list() {
        return findAll().stream().map(GridFSDBFile::getFilename).collect(Collectors.toList());
    }
}