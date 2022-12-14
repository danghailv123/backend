package com.huce.doantotnghiep.utility.mb;

import lombok.Data;

/**
 * TODO: Class description here.
 *
 * @author <a href="https://github.com/tjeubaoit">tjeubaoit</a>
 */
@Data
public final class Record {

    private final byte[] data;
    private final long timestamp;

    public Record(byte[] data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public final byte[] data() {
        return this.data;
    }

    public final long timestamp() {
        return this.timestamp;
    }
}
