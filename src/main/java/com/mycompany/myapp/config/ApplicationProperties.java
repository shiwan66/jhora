package com.mycompany.myapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Jhora.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    public final UploadFileParam uploadFileParam = new UploadFileParam();

    public UploadFileParam getUploadFileParam() {
        return uploadFileParam;
    }

    public static class UploadFileParam {

        private String host = "127.0.0.1";

        private int port = 0;

        private String uploadDir = "./uploads";

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUploadDir() {
            return uploadDir;
        }

        public void setUploadDir(String uploadDir) {
            this.uploadDir = uploadDir;
        }

        private Pool pool = new Pool();

        public void setPool(Pool pool) {
            this.pool = pool;
        }

        public Pool getPool() {
            return this.pool;
        }

        public static class Pool {
            private int maxActive = 8;
            private int maxWait = -1;

            public int getMaxIdle() {
                return maxIdle;
            }

            public void setMaxIdle(int maxIdle) {
                this.maxIdle = maxIdle;
            }

            private int maxIdle = 8;
            private int minIdle = 0;


            public void setMaxActive(int maxActive) {
                this.maxActive = maxActive;
            }

            public int getMaxActive() {
                return maxActive;
            }

            public int getMinIdle() {
                return minIdle;
            }

            public void setMinIdle(int minIdle) {
                this.minIdle = minIdle;
            }

            public int getMaxWait() {
                return maxWait;
            }

            public void setMaxWait(int maxWait) {
                this.maxWait = maxWait;
            }
        }

    }
}
