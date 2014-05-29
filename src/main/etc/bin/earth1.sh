#!/bin/sh
./bin/standalone.sh -c earth.xml -Djboss.bind.address=192.168.200.1 -Djboss.bind.address.management=192.168.200.1 -Djboss.socket.binding.port-offset=0 -Djboss.local.multicast.address=234.168.200.1 -Djboss.global.multicast.address=234.234.234.234 -Djboss.node.name=EARTH1
