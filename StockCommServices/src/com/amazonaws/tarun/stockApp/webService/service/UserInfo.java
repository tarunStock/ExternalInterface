package com.amazonaws.tarun.stockApp.webService.service;

import java.sql.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.tarun.stockApp.TechnicalIndicator.Data.PurchasedStockData;
import com.amazonaws.tarun.stockApp.externalOperations.StockOperations;



// @Path here defines class level path. Identifies the URI path that 
// a resource class will serve requests for.
@Path("/UserInfoService")
public class UserInfo {


	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/name/{i}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String userName(@PathParam("i") String i) {
		String name = i;
		return "<User>" + "<Name>" + name + "</Name>" + "</User>";
	}

	@GET 
	@Path("/age/{j}") 
	@Produces(MediaType.TEXT_XML)
	public String userAge(@PathParam("j") int j) {
		int age = j;
		return "<User>" + "<Age>" + age + "</Age>" + "</User>";
	}
	
	@GET
	@Path("/execute")
	@Produces("text/plain")
	public Response AddPurchasedStock(@QueryParam("nseCode") String stockCode, @QueryParam("purchaseDate") String purDate, 
			@QueryParam("purchasePrice") String purPrice,  @QueryParam("purchaseQuantity") String purQuantity,
			@QueryParam("TotalBrokerage") String brokerage) {
		//String response = "Failure";
		
		System.out.println("Stock Code -> "+stockCode);
		System.out.println("purchaseDate -> "+purDate);
		System.out.println("purchasePrice -> "+purPrice);
		System.out.println("purchaseQuantity -> "+purQuantity);
		System.out.println("TotalBrokerage -> "+brokerage);
		
		PurchasedStockData objPurchasedStockData = new PurchasedStockData();
		objPurchasedStockData.stockCode = stockCode;
		objPurchasedStockData.purchasedDate = new Date(Date.parse(purDate.trim()));
		objPurchasedStockData.purchasedPrice = Float.parseFloat(purPrice.trim());
		objPurchasedStockData.purchasedQuantity = Integer.parseInt(purQuantity.trim());
		objPurchasedStockData.brokeragePaid = Float.parseFloat(brokerage.trim());
		
		StockOperations objAddPurchasedStocks = new StockOperations();
		objAddPurchasedStocks.addPurchasedStock(objPurchasedStockData);
		//return "Pass";
		return Response.status(200).entity("Success").build();
	}	
}
