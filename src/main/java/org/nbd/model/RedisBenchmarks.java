package org.nbd.model;

import org.nbd.repos.AccommodationRepoRedis;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class RedisBenchmarks {
    int testID1 = 0;
    int testID2 = 1;
    double testCapacity = 5.0;
    double testPricePerPerson = 10.0;
    int testRating = 4;
    String testDestination = "Zgierz";
    Accommodation testAccommodation1 = new Accommodation(testID1, testCapacity, testPricePerPerson, testRating, testDestination);
    Accommodation testAccommodation2 = new Accommodation(testID2, testCapacity, testPricePerPerson, testRating, testDestination);
    AccommodationRepoRedis repo = new AccommodationRepoRedis(900);

    @Setup(Level.Trial)
    public void setup() {
        repo.add(testAccommodation1);
        repo.add(testAccommodation2);
        repo.deleteFromCache(testID2);
    }

    @TearDown(Level.Trial)
    public void clear() {
        repo.remove(testID1);
        repo.remove(testID2);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 3, time = 1)
    public void getDataFromCache(Blackhole blackhole) {
        blackhole.consume(repo.getByID(testID1));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 3, time = 1)
    public void getDataFromDatabase(Blackhole blackhole) {
        blackhole.consume(repo.getByID(testID2));
    }
}
