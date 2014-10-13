# clojure-common

This project contains function definitions that are (hopefully) useful across all Clojure projects.  Currently, this consists of functions to read Java properties files and for handling unix time.

## Installation

leiningen

    [com.madeye.clojure.common/common "0.2.3"]

gradle

    compile "com.madeye.clojure.common:common:0.2.3"

maven

    <dependency>
      <groupId>com.madeye.clojure.common</groupId>
      <artifactId>common</artifactId>
      <version>0.2.3</version>
    </dependency>

## Usage

As it is a library project, common can not be executed directly. See the function docs or the examples below for information on how to use the supplied functions.

## Examples

    com.madeye.clojure.common.common=> (load-props "test.properties")
    {:some.other.property "Another test value", :some.property "A test value"}

    com.madeye.clojure.common.common=> (current-unix-time)
    1371192804

    com.madeye.clojure.common.common=> (to-unix-time (tm/now))
    1371192824

    com.madeye.clojure.common.common=> (from-unix-time 1261738182)
    #<DateTime 2009-12-25T10:49:42.000Z>

### Bugs

None identified so far

## License

Copyright © 2013-2014 Madeye Software Ltd.

Distributed under the Eclipse Public License, the same as Clojure.
