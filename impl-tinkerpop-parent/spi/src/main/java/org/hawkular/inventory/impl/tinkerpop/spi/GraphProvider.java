/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.inventory.impl.tinkerpop.spi;

import com.tinkerpop.blueprints.TransactionalGraph;
import org.hawkular.inventory.api.Configuration;

/**
 * This is a service interface that the Tinkerpop implementation will use to get a configured and initialized instance
 * of a blueprints graph.
 *
 * <p>This level of indirection is needed because many graph databases provide configuration and management features
 * that are not accessible through plain Blueprints API.
 *
 * @author Lukas Krejci
 * @since 0.0.1
 */
public interface GraphProvider<G extends TransactionalGraph> {

    /**
     * Given provided configuration, tries to instantiate a graph to be used by the inventory.
     *
     * @param configuration the configuration of the graph
     * @return a configured instance of the graph or null if not possible
     */
    G instantiateGraph(Configuration configuration);

    /**
     * Makes sure all the indexes needed for good performance.
     *
     * <p>The provided set of indexes is what the implementation thinks the indices should be. The graph provider
     * is free to make more indexes if they choose so to support the "core" set of indices.
     *
     * @param graph the graph instance (coming from the
     * {@link #instantiateGraph(org.hawkular.inventory.api.Configuration)} call) to index
     *
     * @param indexSpecs the core set of indices to define
     */
    void ensureIndices(G graph, IndexSpec... indexSpecs);
}
