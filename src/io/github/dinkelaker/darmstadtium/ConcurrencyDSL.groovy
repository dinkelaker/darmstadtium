package io.github.dinkelaker.darmstadtium;

import groovy.lang.Closure;

import de.tud.stg.tigerseye.DSL;

public class ConcurrencyDSL implements DSL {

    void parallelize(Closure logic) {
        Thread.start(logic);
    }

}
