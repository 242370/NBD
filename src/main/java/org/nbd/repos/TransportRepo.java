package org.nbd.repos;

import org.nbd.model.TransportMean;

public class TransportRepo implements IRepo<TransportMean> {

    public void add(TransportMean transportMean) {
        // TODO: implementation
    }

    @Override
    public TransportMean getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
        return null;
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
    }


    public long getSize() {
        // TODO: implementation
        return 0;
    }
}
