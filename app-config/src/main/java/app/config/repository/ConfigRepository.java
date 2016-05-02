package app.config.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.config.domain.Config;
public interface ConfigRepository extends MongoRepository<Config, Long> {
	public Config findByName(String name);
}
