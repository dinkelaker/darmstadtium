package io.github.dinkelaker.darmstadtium

import io.github.dinkelaker.darmstadtium.exceptions.UnknownElementIdentifier

class UnresolvedAutoWiringElement {

    String identifier

    String toString() {
        return this.identifier
    }

    UnresolvedAutoWiringElement(String identifier) {
        this.identifier = identifier
    }

    Object methodMissing(String name, Object args) {
        if (args.every { it instanceof UnresolvedAutoWiringElement }) {
            def completeName = [ name, *args ].join(' ')
            throw new UnknownElementIdentifier("Unknown element identifier '$completeName'.")
        } else {
            throw new MissingMethodException(name, args);
        }
    }

    Object propertyMissing(String name) {
        throw new UnknownElementIdentifier("Unknown element identifier '$name'.")
    }

}
