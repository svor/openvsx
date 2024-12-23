/** ******************************************************************************
 * Copyright (c) 2024 Precies. Software OU and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * ****************************************************************************** */
package org.eclipse.openvsx.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExpiredFileListener implements CacheEventListener<String, Path> {
    protected final Logger logger = LoggerFactory.getLogger(ExpiredFileListener.class);
    @Override
    public void onEvent(CacheEvent<? extends String, ? extends Path> cacheEvent) {
        logger.info("Expired file cache event: {} | key: {}", cacheEvent.getType(), cacheEvent.getKey());
        var path = cacheEvent.getOldValue();
        try {
            var deleted = Files.deleteIfExists(path);
            if(deleted) {
                logger.info("Deleted expired file {} successfully", path);
            } else {
                logger.warn("Did NOT delete expired file {}", path);
            }
        } catch (IOException e) {
            logger.error("Failed to delete expired file", e);
        }
    }
}
