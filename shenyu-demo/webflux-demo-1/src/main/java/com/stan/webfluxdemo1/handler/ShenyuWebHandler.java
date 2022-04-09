
package com.stan.webfluxdemo1.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is web handler request starter.
 */

public final class ShenyuWebHandler implements WebHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ShenyuWebHandler.class);

    private final List<ShenyuPlugin> plugins;

    private final boolean scheduled = false;

    private Scheduler scheduler;

    /**
     * Instantiates a new shenyu web handler.
     *
     * @param plugins the plugins
     */
    public ShenyuWebHandler(final List<ShenyuPlugin> plugins) {
        this.plugins = plugins;
//        ShenyuConfig.Scheduler config = shenyuConfig.getScheduler();
//        this.scheduled = config.getEnabled();
//        if (scheduled) {
//            if (Objects.equals(config.getType(), "fixed")) {
//                this.scheduler = Schedulers.newParallel("shenyu-work-threads", config.getThreads());
//            } else {
        this.scheduler = Schedulers.elastic();
//            }
//        }
    }

    /**
     * Handle the web server exchange.
     *
     * @param exchange the current server exchange
     * @return {@code Mono<Void>} to indicate when request handling is complete
     */
    @Override
    public Mono<Void> handle(final ServerWebExchange exchange) {
        Mono<Void> execute = new DefaultShenyuPluginChain(plugins).execute(exchange);
        if (scheduled) {
            return execute.subscribeOn(scheduler);
        }
        return execute;
    }

    /**
     * Put ext plugins.
     *
     * @param extPlugins the ext plugins
     */
    public void putExtPlugins(final List<ShenyuPlugin> extPlugins) {
        if (CollectionUtils.isEmpty(extPlugins)) {
            return;
        }
        List<ShenyuPlugin> shenyuPlugins = extPlugins.stream()
                .filter(e -> plugins.stream().noneMatch(plugin -> plugin.named().equals(e.named())))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(shenyuPlugins)) {
            shenyuPlugins.forEach(plugin -> {
                plugins.add(plugin);
                LOG.info("shenyu auto add extends plugins:{}", plugin.named());
            });
        }
    }

    private static class DefaultShenyuPluginChain implements ShenyuPluginChain {

        private int index;

        private final List<ShenyuPlugin> plugins;

        /**
         * Instantiates a new Default shenyu plugin chain.
         *
         * @param plugins the plugins
         */
        DefaultShenyuPluginChain(final List<ShenyuPlugin> plugins) {
            this.plugins = plugins;
        }

        /**
         * Delegate to the next {@code WebFilter} in the chain.
         *
         * @param exchange the current server exchange
         * @return {@code Mono<Void>} to indicate when request handling is complete
         */
        @Override
        public Mono<Void> execute(final ServerWebExchange exchange) {
            return Mono.defer(() -> {
                if (this.index < plugins.size()) {
                    ShenyuPlugin plugin = plugins.get(this.index++);
                    boolean skip = plugin.skip(exchange);
                    if (skip) {
                        return this.execute(exchange);
                    }
                    return plugin.execute(exchange, this);
                }
                return Mono.empty();
            });
        }
    }
}
