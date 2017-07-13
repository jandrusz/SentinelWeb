package com.sentinel

import spock.lang.Specification

class Test extends Specification {

    def "test"(){
        when:
        Integer sum = 2 + 2

        then:
        sum == 4
    }
}