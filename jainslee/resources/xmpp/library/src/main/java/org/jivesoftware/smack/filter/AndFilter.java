/**
 * $RCSfile$
 * $Revision: 2408 $
 * $Date: 2004-11-02 20:53:30 -0300 (Tue, 02 Nov 2004) $
 *
 * Copyright 2003-2004 Jive Software.
 *
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

/**
 * Implements the logical AND operation over two or more packet filters.
 * In other words, packets pass this filter if they pass <b>all</b> of the filters.
 *
 * @author Matt Tucker
 */
public class AndFilter implements PacketFilter {

    /**
     * The current number of elements in the filter.
     */
    private int size;

    /**
     * The list of filters.
     */
    private PacketFilter [] filters;

    /**
     * Creates an empty AND filter. Filters should be added using the
     * {@link #addFilter(PacketFilter)} method.
     */
    public AndFilter() {
        size = 0;
        filters = new PacketFilter[3];
    }

    /**
     * Creates an AND filter using the two specified filters.
     *
     * @param filter1 the first packet filter.
     * @param filter2 the second packet filter.
     */
    public AndFilter(PacketFilter filter1, PacketFilter filter2) {
        if (filter1 == null || filter2 == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        size = 2;
        filters = new PacketFilter[2];
        filters[0] = filter1;
        filters[1] = filter2;
    }

    /**
     * Adds a filter to the filter list for the AND operation. A packet
     * will pass the filter if all of the filters in the list accept it.
     *
     * @param filter a filter to add to the filter list.
     */
    public void addFilter(PacketFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }
        // If there is no more room left in the filters array, expand it.
        if (size == filters.length) {
            PacketFilter [] newFilters = new PacketFilter[filters.length+2];
            for (int i=0; i<filters.length; i++) {
                newFilters[i] = filters[i];
            }
            filters = newFilters;
        }
        // Add the new filter to the array.
        filters[size] = filter;
        size++;
    }

    public boolean accept(Packet packet) {
        for (int i=0; i<size; i++) {
            if (!filters[i].accept(packet)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return filters.toString();
    }
}