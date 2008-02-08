package esb.chapter4.messageflow;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringStarter {
	
	public static void main(String[] args) {
		FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext("file:messageflow/spring/messageflow-spring.xml");
		appContext.start();
		System.out.println("Spring beans started successfully");
	}

}
