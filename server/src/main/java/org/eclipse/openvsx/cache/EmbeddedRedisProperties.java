package org.eclipse.openvsx.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ovsx.redis")
public class EmbeddedRedisProperties {

    private boolean embedded;
    private String path;

    public boolean isEmbedded() {
        return embedded;
    }
    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}