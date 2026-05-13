package web.mvc.controller;

/**
 * 현재 요청이 어떤 Controller를 실행해야하는지를 찾아주는 클래스 
 * */
public class HandlerMapping {
	private static HandlerMapping factory = new HandlerMapping();
	
	private HandlerMapping() {}
	
	public static HandlerMapping getFactory() {
		return factory;
	}

	public Controller getController(String key){
		Controller controller =null;
		
		if(key.equals("insert")) {
			controller = new InsertController();
		}else if(key.equals("select")){
			controller=new SelectController();
		}else if(key.equals("update")) {
			controller= new UpdateController();
		}else if(key.equals("delete")) {
			controller = new DeleteController();
		}
		
		
		return controller;
	}
}
