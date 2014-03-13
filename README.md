# Objectify Performance Test, entityloader fork

This project is for comparing the performance of Objectify against
using Google's lower-level datastore APIs directly.

This branch uses my experimental [entityloader branch of
Objectify](https://github.com/AgentME/objectify/tree/entityloader). The
entityloader branch allows an EntityLoader object to be registered
with Objectify to take over translating a specific type of entity to a
class, while allowing the user to still use the Objectify API
seamlessly.

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

To build and run, first make sure the Objectify entityloader branch is installed (git clone it, and then run `mvn clean install` from its directory), and then run

    mvn appengine:devserver
