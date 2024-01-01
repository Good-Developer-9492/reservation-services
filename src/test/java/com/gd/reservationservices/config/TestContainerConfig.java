package com.gd.reservationservices.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class TestContainerConfig implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        String redisImage = "redis:7.0.8-alpine";
        int redisPort = 6379;
        RedisContainer redisContainer =
                new RedisContainer(
                        redisImage,
                        redisPort,
                        new GenericContainer(DockerImageName.parse(redisImage))
                                .withExposedPorts(redisPort)
                );
        redisContainer.startContainer();
        setSystemProperties(redisContainer.getHost(), redisContainer.getMappedPort());
    }

    private void setSystemProperties(String host, int port) {
        System.setProperty("spring.data.redis.host", host);
        System.setProperty("spring.data.redis.port", String.valueOf(port));
    }

    private record RedisContainer(
            String image,
            int port,
            GenericContainer container
    ) {

        public void startContainer() {
            if (container.isRunning()) {
                return;
            }

            container.start();
        }

        public String getHost() {
            return container.getHost();
        }

        public int getMappedPort() {
            return container.getMappedPort(port);
        }
    }


}
