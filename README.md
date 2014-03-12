# Objectify Performance Test

This project is for comparing the performance of Objectify against
using Google's lower-level datastore APIs directly.

This project first fills the datastore with 10,000 objects with a few
integer, string, and list properties. The property values are
deterministically pseudo-random, so the datastore should have the same
contents each time it's generated. Next, the project loads everything
from the datastore using Google's datastore APIs. It does a few
warm-up rounds first, then times one round, and prints how long the
round took into the console. Then this is repeated with Objectify.

The results are printed into the console while the dev appengine
webserver loads. The webserver will stay running once loaded, and the
tests can be re-run by accessing http://localhost:8080/.

## Running

Requires [Apache Maven](http://maven.apache.org) 3.0 or greater, and
JDK 7+ in order to run.

To build and run, type

    mvn appengine:devserver
