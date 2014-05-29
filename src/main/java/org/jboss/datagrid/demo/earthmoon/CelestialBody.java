package org.jboss.datagrid.demo.earthmoon;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

@Path("/celestialbody/{bodyName}/{boxName}")
public class CelestialBody {

   @Inject
   @CelestialBodyCacheManagers
   Map<String, RemoteCacheManager> rcMap;

   @PUT
   @POST
   @Path("/widget/{widgetName}")
   public void addWidget(@PathParam("bodyName") String bodyName, @PathParam("boxName") String boxName, @PathParam("widgetName") String widgetName, @FormParam("color") String color) {
      getCache(bodyName, boxName).put(widgetName, color);
   }

   @GET
   @Path("/widget/{widgetName}")
   public String getWidget(@PathParam("bodyName") String bodyName, @PathParam("boxName") String boxName, @PathParam("widgetName") String widgetName) {
      return getCache(bodyName, boxName).get(widgetName);
   }

   @GET
   @Path("/widgets")
   @Produces("application/json")
   public Map<String, String> getWidgets(@PathParam("bodyName") String bodyName, @PathParam("boxName") String boxName) {
      return getCache(bodyName, boxName).getBulk();
   }

   private RemoteCache<String, String> getCache(String bodyName, String boxName) {
      return rcMap.get(bodyName).getCache(boxName + "WidgetCache");
   }
}
