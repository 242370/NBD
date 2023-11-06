package org.nbd;

import org.nbd.model.TestDBItem;
import org.nbd.model.TravelAgency;

import java.util.ArrayList;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        try (MongołConfig mongoRepository = new MongołConfig()) {
            TestDBItem testDBItem = new TestDBItem("TestName", 420);
            mongoRepository.testAdd(testDBItem);
            ArrayList<TestDBItem> testDBItemArrayList = mongoRepository.testGet();
            System.out.println(testDBItemArrayList.get(0));
        } catch (Exception e) {
            System.out.println("oops:\n" + e);
        }
        // TODO: testing env
    }
}