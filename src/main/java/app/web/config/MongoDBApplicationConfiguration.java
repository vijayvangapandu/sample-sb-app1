package app.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories
public class MongoDBApplicationConfiguration extends AbstractMongoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(MongoDBApplicationConfiguration.class);
	
	@Value("${mongodb.database}")
	private String databaseName;

	@Value("${mongodb.connection.uri}")
	private String mongosUri;

	@Value("${mongodb.max.connection.idle.time:60000}")
	private Integer maxConnectionIdleTime;

	@Value("${mongodb.max.connection.timeout:10000}")
	private Integer maxConnectionTimeout;

	@Value("${mongodb.min.connections.per.host:50}")
	private Integer minConnectionsPerHost;

	@Value("${mongodb.max.connections.per.host:100}")
	private Integer maxConnectionsPerHost;

	@Value("${mongodb.socket.keep.alive:true}")
	private Boolean socketKeepAlive;

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	public Mongo mongo() throws Exception {
		logger.info("Creating mongo client");
		MongoClientOptions.Builder builder = MongoClientOptions.builder().maxConnectionIdleTime(maxConnectionIdleTime)
				.connectTimeout(maxConnectionTimeout).minConnectionsPerHost(minConnectionsPerHost)
				.connectionsPerHost(maxConnectionsPerHost).socketKeepAlive(socketKeepAlive);

		MongoClientURI mongoUri = new MongoClientURI(mongosUri, builder);
		MongoClient mongo = new MongoClient(mongoUri);
		return mongo;
	}
}
