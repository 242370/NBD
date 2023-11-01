package org.nbd.repos;

import org.nbd.model.Client;

public class ClientRepo implements IRepo<Client> {
    public void add(Client client) {
        // TODO: implementation
    }

    public Client getByID(int id) throws Exception{
        if(id < 1)
        {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
        return null;
    }

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