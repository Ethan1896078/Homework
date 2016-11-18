package team.t9001.saad;

import team.t9001.saad.http.JettyServer;


public class WebMain extends JettyServer {

	public static void main(String[] args) {
		WebMain logicMain = new WebMain();
		logicMain.setPort(8081);
		logicMain.addWebappPath("tropical-disease-research/src/main/webapp");
		logicMain.setContextPath("/");
		logicMain.start();
	}

}
