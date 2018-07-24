package com.bloodbank.MyBloodBank;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.database.ConnChecker;
import com.bloodbank.front.Admin;
import com.bloodbank.front.CP;
import com.bloodbank.front.HomeView;
import com.bloodbank.front.MyInfo;
import com.bloodbank.setup.Setup;
import com.bloodbank.sliderMenu.BloodBankMenu;
import com.bloodbank.statusHeader.BloodBankHeader;
import com.bloodbank.utils.AttNames;
import com.bloodbank.utils.SimpleExecutor;
import com.bloodbank.utils.Utils;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
//@PushStateNavigation
@Push(transport = Transport.WEBSOCKET_XHR)
@Theme("BloodBankDefault")
public class MyUI extends UI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// connection

	private BloodBankDatabase mysql;

	// layout
	private VerticalLayout page = null;
	public BloodBankHeader header = null; // getter/setter
	public BloodBankMenu menu = null;
	private CP cp = null;
	private Navigator navigator = null;
	private transient ResourceBundle bundle = null;// nu e serializabil, sau foloeste SerializableResourceBundle...
	private static boolean isCheckStarted = false;
	private MyInfo myinfo = null;
	private Admin admin = null;
	private VerticalLayout menuContainer = null;
	private Panel viewContainer = null;
	private HorizontalLayout mainLayout = null;
	private HomeView home = null;
	private int code = 0;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		if (!Utils.isSetupFilePresent()) {
			Page.getCurrent().setTitle("Setup");
			addWindow(new Setup());
		} else {

			Page.getCurrent().setTitle("My Blood Bank!");
			// mysql = new BloodBankDatabase();

			//int code = mysql.dbInit();/// returns no. of success/fail
			initStuff();
			System.out.println("Code: " + code);
			page = new VerticalLayout();

			menuContainer = new VerticalLayout();
			menu = new BloodBankMenu(this, bundle);

			viewContainer = new Panel();

			menuContainer.addComponent(menu);
			menuContainer.setMargin(false);
			menuContainer.setSizeFull();// new
			menuContainer.addStyleName(ValoTheme.MENU_ROOT);
			mainLayout = new HorizontalLayout(menuContainer, viewContainer);
			mainLayout.setExpandRatio(menuContainer, 1.60f);
			mainLayout.setExpandRatio(viewContainer, 9);
			mainLayout.setSpacing(false);// test
			mainLayout.setMargin(false);
			mainLayout.setSizeFull();
			viewContainer.setSizeFull();
			viewContainer.addStyleName("viewContainer");

			header = new BloodBankHeader(this, menuContainer, bundle, mysql);

			if ((!header.circleStatus.getStyleName().contains("red")
					|| !header.circleStatus.getStyleName().contains("green")) && code > 0) {
				header.circleStatus.addStyleName("green");
				header.circleStatus.setDescription("DB connection OK!");
			} else {
				header.circleStatus.addStyleName("red");
				header.circleStatus.setDescription("No db connection!");
			}

			header.setWidth(Page.getCurrent().getBrowserWindowWidth(), Unit.PIXELS);
			// set height full
			header.setHeight("100%");
			//
			page.addComponents(header, mainLayout);

			page.setExpandRatio(header, 0.9f);
			page.setExpandRatio(mainLayout, 9.1f);
			page.setSpacing(false);
			page.setMargin(false);
			page.setSizeFull();

			setContent(page);
			addStyleName("background1");

			home = new HomeView(mysql);

			navigator = new Navigator(this, viewContainer);
			navigator.addView("", home);
			navigator.addView("Home", home);
			navigator.setErrorView(home);

			navigator.addViewChangeListener(event -> {

				if (admin != null && event.getNewView().getClass().equals(admin.getClass())) {
					menuContainer.setVisible(false);
				}
				return true;
			});

			// test
			
			//
			/*
			 * if(connChecker==null) connChecker = new ConnChecker.get(this, mysql); else {
			 * connChecker.setUI(this); }
			 */
			if (!isCheckStarted) {
				// System.out.println("Init adsdsa");
				SimpleExecutor.getInstance().getSES().scheduleAtFixedRate(ConnChecker.getInstance(this, mysql), 10, 30,
						TimeUnit.SECONDS);// orig 10
				isCheckStarted = true;
			} else {
				ConnChecker.getInstance(this, mysql).setUI(this);
			}

			// log with cookie

			if (menu.getCookie(AttNames.REMEMBERME.getAtt()).isPresent()
					&& VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()) == null) {
				
					System.out.println("Log With Cookie!!!!!!!!");
					String hash = menu.getCookie(AttNames.REMEMBERME.getAtt()).get().getValue();
					String ip = Page.getCurrent().getWebBrowser().getAddress();
					String storedHash = mysql.getToken(hash);
					if(storedHash!=null) {
					String[] split = storedHash.split("\\.");
					String storedIP = Utils.simpleDec(split[1]);

					if (storedHash.equals(hash) && storedIP.equals(ip)) {/// testare necesara
						List<String> li = mysql.getRememberLoginInfo(hash);
						VaadinSession.getCurrent().setAttribute(AttNames.LOGGED.getAtt(), li.get(0));
						VaadinSession.getCurrent().setAttribute(AttNames.USERID.getAtt(), li.get(1));
						VaadinSession.getCurrent().setAttribute(AttNames.RANK.getAtt(), li.get(2));
						VaadinSession.getCurrent().setAttribute(AttNames.ISQUPDATED.getAtt(),
								mysql.isQUpdated(li.get(1)));// test

					} 
					}else {
						Cookie cookie = new Cookie(AttNames.REMEMBERME.getAtt(), "");
						cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
						cookie.setMaxAge(0);
						VaadinService.getCurrentResponse().addCookie(cookie);
					}

				

			}

			if (VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()) != null) {// no
				menu.setLogged();
				header.update();
				setLogged();
				if(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt())!=null)
				VaadinSession.getCurrent().setAttribute(AttNames.ISQUPDATED.getAtt(),
						mysql.isQUpdated(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString()));// test
			}

		}

	}

	public MyUI() {
		//Utils.setBasePath(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"//WEB-INF//classes");
		Utils.setBasePath("/home/spectral369/workspace_WEB/MyBloodBank/src/main/resources");
		if (Utils.isSetupFilePresent())
			mysql = BloodBankDatabase.getInstance();
		/*
		 * mysql = new BloodBankDatabase(); sc =
		 * Executors.newSingleThreadScheduledExecutor();
		 */
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet implements SessionDestroyListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void destroy() {

			/*
			 * VaadinService.getCurrentRequest().getWrappedSession().invalidate();
			 * VaadinService.getCurrent().fireSessionDestroy(VaadinSession.getCurrent());
			 */
			// super.destroy();
			System.out.println("destroy");
			BloodBankDatabase.getInstance().destroy();
			SimpleExecutor.getInstance().destroy();//
			if (ConnChecker.getInstance() != null)
				ConnChecker.getInstance().interrupt();//

			getService().destroy();// redundant super.destroy();
			try {
				AbandonedConnectionCleanupThread.checkedShutdown();
			} catch (Throwable t) {
			}
			// This manually deregisters JDBC driver, which prevents Tomcat 7 from
			// complaining about memory leaks
			Enumeration<java.sql.Driver> drivers = java.sql.DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				java.sql.Driver driver = drivers.nextElement();
				try {
					java.sql.DriverManager.deregisterDriver(driver);
				} catch (Throwable t) {
				}
			}
			try {
				Thread.sleep(2000L);
			} catch (Exception e) {
			}
		}

		@Override
		public void sessionDestroy(SessionDestroyEvent event) {
			System.out.println("destroy session");
			VaadinService.getCurrentRequest().getWrappedSession().invalidate();
			UI.getCurrent().getUI().getSession().close();

			/*
			 * VaadinService.getCurrentRequest().getWrappedSession().invalidate();
			 * VaadinService.getCurrent().fireSessionDestroy(VaadinSession.getCurrent());
			 * VaadinSession.getCurrent().close();
			 */

		}

	}

	public CP getCP() {
		return cp;
	}

	public MyInfo getMyInfo() {
		return myinfo;
	}

	public VerticalLayout getMenuContainer() {
		return menuContainer;
	}

	public void setLogged() {
		//

		cp = new CP(this, mysql);
		navigator.addView("CP", cp);
		//System.out.println(VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()).toString());
		if (Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()).toString()) > 0) {
			admin = new Admin(mysql);
			navigator.addView("Admin", admin);
		} else {
			myinfo = new MyInfo(mysql);
			navigator.addView("myInfo", myinfo);
		}

	}
	
	private void initStuff() {
		code  = mysql.isvalid();
		// end init1
		if (getSession().getLocale().equals(Locale.US) || getSession().getLocale().equals(Locale.ENGLISH)
				|| getSession().getLocale().equals(Locale.UK))
			bundle = ResourceBundle.getBundle("local_EN", getSession().getLocale());
		else {
			bundle = ResourceBundle.getBundle("local_RO", getSession().getLocale());
		}
		System.out.println(getSession().getLocale());
		
		

	}

}
