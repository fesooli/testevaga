package br.com.prova.campanha.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Fellipe Oliveira
 * @since 2017
 *
 */
@Component
@Profile(value = { "local" })
public class CacheManagerCheck implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CacheManagerCheck.class);

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info(
                "\n\n=========================================================\n"
                        + "USING CACHE MANAGER: {} \n"
                        + "=========================================================\n\n",
                this.cacheManager.getClass().getName());
    }

}