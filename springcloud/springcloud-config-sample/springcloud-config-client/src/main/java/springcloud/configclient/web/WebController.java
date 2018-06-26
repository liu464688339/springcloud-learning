package springcloud.configclient.web;

public class WebController {
	
	public static void main(String[] args) throws Exception {
		//refresh();
		refreshBus();
	}

   static void refresh() {
	   HttpClientUtils.doPost("http://localhost:7002/refresh");
	   System.out.println("============refresh success");
   }
   
   static void refreshBus() throws Exception{
	   HttpClientUtils.doPost("http://localhost:7001/bus/refresh");
	   System.out.println("============refreshBus success");
   }
}
