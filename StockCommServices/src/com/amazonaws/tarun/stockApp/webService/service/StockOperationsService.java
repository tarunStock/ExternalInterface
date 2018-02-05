package com.amazonaws.tarun.stockApp.webService.service;

import java.sql.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.amazonaws.tarun.stockApp.TechnicalIndicator.Data.PurchasedStockData;
import com.amazonaws.tarun.stockApp.externalOperations.StockOperations;
import com.sun.jersey.api.client.ClientResponse;

@Path("/StockOps")
public class StockOperationsService {

	/*static {
		try {
			PropertyFileReader.setProperties("autoConfig.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	@GET
	@Path("/addStock")
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
	
	@GET
	@Path("/sellStock")
	@Produces("application/json")
	public Response SellPurchasedStock(@QueryParam("nseCode") String stockCode,  
			@QueryParam("soldQuantity") String soldQuantity) {
		//String response = "Failure";
		
		System.out.println("Stock Code -> "+stockCode);
		System.out.println("soldQuantity -> "+soldQuantity);
				
		PurchasedStockData objPurchasedStockData = new PurchasedStockData();
		objPurchasedStockData.stockCode = stockCode;
		objPurchasedStockData.purchasedQuantity = Integer.parseInt(soldQuantity.trim());
		
		StockOperations objAddPurchasedStocks = new StockOperations();
		//objAddPurchasedStocks.addPurchasedStock(objPurchasedStockData);
		//return "Pass";
		return Response.status(200).entity("Success").build();
	}
	
	/*@GET
	@Path("/sellStock")
	@Produces("text/plain")
	public Response GetPurchasedStockDetails() {
		//String response = "Failure";
		
		System.out.println("Stock Code -> "+stockCode);
		System.out.println("soldQuantity -> "+soldQuantity);
				
		PurchasedStockData objPurchasedStockData = new PurchasedStockData();
		objPurchasedStockData.stockCode = stockCode;
		objPurchasedStockData.purchasedQuantity = Integer.parseInt(soldQuantity.trim());
		
		PurchasedStocksOperations objAddPurchasedStocks = new PurchasedStocksOperations();
		//objAddPurchasedStocks.addPurchasedStock(objPurchasedStockData);
		//return "Pass";
		return Response.status(200).entity("Success").build();
	}*/
	
	@GET
	@Path("/StockDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetStockDetails(@QueryParam("stockCode") String stockCode, @QueryParam("targetDate") String targetDate) {
		//String response = "Failure";
		
		System.out.println("Stock Code -> "+stockCode);
		System.out.println("Date -> "+new Date(Date.parse(targetDate)));
		StockOperations objStockOperations = new StockOperations();
		
		JSONObject stockDetails = objStockOperations.getStockDetails(stockCode, new Date(Date.parse(targetDate)));
		//objAddPurchasedStocks.addPurchasedStock(objPurchasedStockData);
		//return "Pass";
		return stockDetails.toString();
		//return Response.status(200).entity("Success").build();
	}
	
	@GET
	@Path("/CollectFinancial")
	@Produces("text/plain")
	public Response ColletFinacials() {
		
		StockOperations objStockOperations = new StockOperations();
		objStockOperations.CollectFinancialData();
		return Response.status(200).entity("Success").build();
		//return Response.status(200).entity("Success").build();
	}
}