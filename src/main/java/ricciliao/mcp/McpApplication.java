package ricciliao.mcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(
        exclude = {
                MongoDataAutoConfiguration.class,
                MongoReactiveDataAutoConfiguration.class,
                MongoReactiveRepositoriesAutoConfiguration.class,
                MongoRepositoriesAutoConfiguration.class,
                RedisAutoConfiguration.class,
                RedisReactiveAutoConfiguration.class,
                RedisRepositoriesAutoConfiguration.class,
                MongoAutoConfiguration.class,
                MongoReactiveAutoConfiguration.class,
        }
)
public class McpApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(McpApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
