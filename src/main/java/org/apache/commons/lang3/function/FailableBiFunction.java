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

package org.apache.commons.lang3.function;

import java.util.function.BiFunction;

/**
 * A functional interface like {@link BiFunction} that declares a {@code Throwable}.
 *
 * @param <O1> Input type 1.
 * @param <O2> Input type 2.
 * @param <R> Return type.
 * @param <T> Thrown exception.
 * @since 3.11
 */
@FunctionalInterface
public interface FailableBiFunction<O1, O2, R, T extends Throwable> {

    /**
     * Applies this function.
     *
     * @param input1 the first input for the function
     * @param input2 the second input for the function
     * @return the result of the function
     * @throws T Thrown when the function fails.
     */
    R apply(O1 input1, O2 input2) throws T;
}
