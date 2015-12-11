/*
 * Copyright 2013 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.sbe.codec.java;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MethodSelector
{
    private final Set<String> ignoredMethods;
    private final Map<Class<?>, Set<String>> sortedMethods = new HashMap<>();

    public static Set<String> objectAndIteratorMethods()
    {
        return new HashSet<>(
            Arrays.asList("hashCode", "clone", "toString", "getClass", "next", "hasNext", "remove", "iterator"));
    }

    public MethodSelector(final Set<String> ignoredMethods)
    {
        this.ignoredMethods = ignoredMethods;
    }

    public List<Method> select(final Class<?> clazz)
    {
        final Method[] methods = clazz.getMethods();
        final Set<String> sortedMethodNames = getSortedMethods(clazz, methods);
        final Map<String, Method> sortedMethods = new HashMap<>();
        final List<Method> unsortedMethods = new ArrayList<>();

        for (final Method method : methods)
        {
            selectMethod(sortedMethodNames, sortedMethods, unsortedMethods, method);
        }

        for (final String name : sortedMethodNames)
        {
            unsortedMethods.add(sortedMethods.get(name));
        }

        return unsortedMethods;
    }

    private Set<String> getSortedMethods(final Class<?> clazz, final Method[] methods)
    {
        final Set<String> sortedMethodNames = sortedMethods.get(clazz);
        if (sortedMethodNames == null)
        {
            final GroupOrder order = clazz.getAnnotation(GroupOrder.class);
            if (order == null)
            {
                sortedMethods.put(clazz, Collections.<String>emptySet());

                return Collections.emptySet();
            }
            else
            {
                final Set<String> result = new LinkedHashSet<>();
                for (final Class<?> groupClazz : order.value())
                {
                    for (final Method method : methods)
                    {
                        if (method.getReturnType() == groupClazz && method.getParameterTypes().length == 0)
                        {
                            result.add(method.getName());
                        }
                    }
                }
                sortedMethods.put(clazz, result);

                return result;
            }
        }

        return sortedMethodNames;
    }

    private void selectMethod(
        final Set<String> sortedMethodNames,
        final Map<String, Method> sortedMethods,
        final List<Method> unsortedMethods,
        final Method method)
    {
        final int mods = method.getModifiers();
        if (!Modifier.isPublic(mods))
        {
            return;
        }
        if (Modifier.isStatic(mods))
        {
            return;
        }
        if (method.getParameterTypes().length != 0)
        {
            return;
        }
        if (method.getReturnType().equals(Void.TYPE))
        {
            return;
        }

        final String name = method.getName();
        if (ignoredMethods.contains(name))
        {
            return;
        }

        if (sortedMethodNames == null)
        {
            unsortedMethods.add(method);
        }
        else
        {
            if (sortedMethodNames.contains(name))
            {
                sortedMethods.put(name, method);
            }
            else
            {
                unsortedMethods.add(method);
            }
        }
    }
}
