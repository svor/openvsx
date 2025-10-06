/** ******************************************************************************
 * Copyright (c) 2025 Precies. Software OU and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * ****************************************************************************** */
package org.eclipse.openvsx.cache;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;

@Component
@ConditionalOnProperty(value = "ovsx.redis.embedded", havingValue = "true")
public class EmbeddedRedisServer {

    private final int port;
    private final EmbeddedRedisProperties embeddedRedisProperties;
    private RedisServer server;

    public EmbeddedRedisServer(RedisProperties properties, EmbeddedRedisProperties embeddedRedisProperties) {
        this.port = properties.getPort();
        this.embeddedRedisProperties = embeddedRedisProperties;
    }

    @PostConstruct
    public void start() throws IOException {
        if (embeddedRedisProperties.getPath() != null && !embeddedRedisProperties.getPath().isEmpty()) {
            server = new RedisServer(port, new File(embeddedRedisProperties.getPath()));
        } else {
            server = new RedisServer(port);
        }
        server.start();
    }

    @PreDestroy
    public void stop() throws IOException {
        server.stop();
    }
}
