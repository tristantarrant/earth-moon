package org.jboss.datagrid.demo.earthmoon;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class Configuration {
   @Produces
   @ApplicationScoped
   @CelestialBodyCacheManagers
   public Map<String, RemoteCacheManager> celestialBodyCacheManager() {
      Map<String, RemoteCacheManager> rcmMap = new HashMap<String, RemoteCacheManager>();

      ConfigurationBuilder builder = new ConfigurationBuilder();
      builder.addServer().host("192.168.200.1").port(11222);
      rcmMap.put("earth", new RemoteCacheManager(builder.build()));
      builder = new ConfigurationBuilder();
      builder.addServer().host("192.168.201.1").port(11222);
      rcmMap.put("moon", new RemoteCacheManager(builder.build()));
      return rcmMap;
   }
}
