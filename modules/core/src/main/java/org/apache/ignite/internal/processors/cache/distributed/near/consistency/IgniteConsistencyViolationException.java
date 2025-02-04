/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.cache.distributed.near.consistency;

import java.util.Set;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.processors.cache.KeyCacheObject;

/**
 * Consistency violation exception.
 */
public class IgniteConsistencyViolationException extends IgniteCheckedException {
    /** */
    private static final long serialVersionUID = 0L;

    /** Inconsistent entries keys. */
    private final Set<KeyCacheObject> keys;

    /**
     * @param keys Keys.
     */
    public IgniteConsistencyViolationException(Set<KeyCacheObject> keys) {
        super("Distributed cache consistency violation detected.");

        assert keys != null && !keys.isEmpty();

        this.keys = keys;
    }

    /**
     * Inconsistent entries keys.
     */
    public Set<KeyCacheObject> keys() {
        return keys;
    }
}
