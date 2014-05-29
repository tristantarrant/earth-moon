#!/bin/sh
./bin/standalone.sh -c moon.xml -Djboss.bind.address=192.168.201.1 -Djboss.bind.address.management=192.168.201.1 -Djboss.socket.binding.port-offset=0 -Djboss.local.multicast.address=234.168.201.1 -Djboss.global.multicast.address=234.234.234.234 -Djboss.node.name=MOON1
