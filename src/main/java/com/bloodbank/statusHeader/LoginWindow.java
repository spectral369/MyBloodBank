package com.bloodbank.statusHeader;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.Cookie;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.AttNames;
import com.bloodbank.utils.Utils;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class LoginWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Binder<LoginWindow> binder = null;
	private VerticalLayout main = null;
	private HorizontalLayout titlelayout = null;
	private Button title = null;
	private HorizontalLayout userLabel = null;
	private Button usernameLabel = null;
	private HorizontalLayout cnpLayout = null;
	private TextField cnpField = null;
	private HorizontalLayout passLabel = null;
	private Button passwordLabel = null;
	private HorizontalLayout passField = null;
	private PasswordField passwordField = null;
	private HorizontalLayout rememberLayout = null;
	private CheckBox remember = null;
	private HorizontalLayout btnLayout = null;
	private Button login = null;
	private List<String> li = null;

	private Button cancel = null;

	public LoginWindow(MyUI ui, BloodBankDatabase db) {

		// test
		binder = new Binder<>(LoginWindow.class);

		main = new VerticalLayout();
		titlelayout = new HorizontalLayout();
		title = new Button("Login Form", VaadinIcons.SIGN_IN);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		titlelayout.addComponent(title);
		titlelayout.setComponentAlignment(title, Alignment.TOP_CENTER);
		titlelayout.addStyleName(ValoTheme.MENU_ROOT);
		titlelayout.setWidth("100%");
		main.addComponent(titlelayout);
		main.setComponentAlignment(titlelayout, Alignment.TOP_CENTER);

		userLabel = new HorizontalLayout();
		usernameLabel = new Button("CNP:", VaadinIcons.MAILBOX);
		usernameLabel.setEnabled(false);
		usernameLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		userLabel.addComponent(usernameLabel);
		userLabel.setComponentAlignment(usernameLabel, Alignment.MIDDLE_CENTER);
		main.addComponent(userLabel);
		main.setComponentAlignment(userLabel, Alignment.TOP_CENTER);

		cnpLayout = new HorizontalLayout();
		cnpField = new TextField();
		cnpField.setPlaceholder("CNP");
		cnpLayout.addComponent(cnpField);

		cnpLayout.setComponentAlignment(cnpField, Alignment.MIDDLE_CENTER);
		main.addComponent(cnpLayout);
		main.setComponentAlignment(cnpLayout, Alignment.MIDDLE_CENTER);

		passLabel = new HorizontalLayout();
		passwordLabel = new Button("Password", VaadinIcons.PASSWORD);
		passwordLabel.setEnabled(false);
		passwordLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		passLabel.addComponent(passwordLabel);
		passLabel.setComponentAlignment(passwordLabel, Alignment.MIDDLE_CENTER);
		main.addComponent(passLabel);
		main.setComponentAlignment(passLabel, Alignment.TOP_CENTER);

		passField = new HorizontalLayout();
		passwordField = new PasswordField();
		passwordField.setPlaceholder("******");
		passField.addComponent(passwordField);
		passField.setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);
		main.addComponent(passField);
		main.setComponentAlignment(passField, Alignment.MIDDLE_CENTER);

		rememberLayout = new HorizontalLayout();
		remember = new CheckBox("Remember me", false);
		rememberLayout.addComponent(remember);
		rememberLayout.setComponentAlignment(remember, Alignment.MIDDLE_CENTER);
		main.addComponent(rememberLayout);
		main.setComponentAlignment(rememberLayout, Alignment.MIDDLE_CENTER);

		btnLayout = new HorizontalLayout();
		login = new Button("Login", VaadinIcons.SIGN_IN);
		login.setEnabled(false);
		login.addStyleName(ValoTheme.BUTTON_PRIMARY);
		login.addClickListener(event -> {

			try {
				li = db.logSimpleUser(cnpField.getValue().trim(), passwordField.getValue());
			} catch (Exception e) {
				Notification notif = new Notification("Error db connection, please try again later !",
						Notification.Type.ERROR_MESSAGE);
				notif.setPosition(Position.BOTTOM_RIGHT);
				notif.setDelayMsec(4000);
				notif.show(Page.getCurrent());
			}

			Notification notif = new Notification("Login Success !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			if (li.size() > 2) {
				notif.show(Page.getCurrent());
				System.out.println(AttNames.LOGGED.getAtt() + " " + AttNames.LOGGED.getAtt());
				VaadinSession.getCurrent().setAttribute(AttNames.LOGGED.getAtt(), li.get(0));
				VaadinSession.getCurrent().setAttribute(AttNames.USERID.getAtt(), li.get(1));
				VaadinSession.getCurrent().setAttribute(AttNames.RANK.getAtt(), li.get(2));
				VaadinSession.getCurrent().setAttribute(AttNames.ISQUPDATED.getAtt(),
						db.isQUpdated( li.get(1)));// test
				// ui.header.markAsDirty();
				ui.menu.setLogged();
				ui.header.update();
				ui.setLogged();
				
				// ui.header.getUI().push();
				if (remember.getValue()) {

					String ip = Page.getCurrent().getWebBrowser().getAddress();
					String hashed = getRandomToken(64, ip);

					int re = db.updateToken(hashed, cnpField.getValue().trim());
					if (re > 0)
						setCookie(hashed);
					else {
						Notification no = new Notification("Cookies are disabled!", Notification.Type.HUMANIZED_MESSAGE);
						no.setPosition(Position.BOTTOM_RIGHT);
						no.setDelayMsec(4000);
						no.show(Page.getCurrent());
					}

				}

				// UI.getCurrent().getUI().push();

				this.close();
				if (li != null)
					li.clear();

			} else {
				notif.setCaption("Login failed !");
				this.close();

				notif.show(Page.getCurrent());
			}

		});
		cancel = new Button("Cancel", VaadinIcons.CLOSE);
		cancel.addClickListener(event -> {
			this.close();
		});
		btnLayout.addComponents(login, cancel);
		btnLayout.setComponentAlignment(login, Alignment.BOTTOM_RIGHT);
		btnLayout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);
		main.addComponent(btnLayout);
		main.setComponentAlignment(btnLayout, Alignment.BOTTOM_RIGHT);
		// test

		binder.forField(cnpField)
		.withConverter(new StringToBigIntegerConverter("CNP must be Integer"))
		.withValidator(str->Utils.getDigitCount(str)==13, "Invalid CNP Length!")

				
				.bind(new ValueProvider<LoginWindow, BigInteger>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(LoginWindow source) {
						return null;
					}
				}, new Setter<LoginWindow, BigInteger>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(LoginWindow bean, BigInteger fieldvalue) {

					}
				});
		binder.forField(passwordField)
				.withValidator(str -> str.length() > 5, "Password must be greather than 5 chartacters !")
				.bind(new ValueProvider<LoginWindow, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(LoginWindow source) {
						return null;
					}
				}, new Setter<LoginWindow, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(LoginWindow bean, String fieldvalue) {

					}
				});

		binder.addStatusChangeListener(event -> {
			if (binder.isValid())
				login.setEnabled(true);
			else
				login.setEnabled(false);
		});

		/// test
		setContent(main);
		setWindowMode(WindowMode.NORMAL);
		setResizable(false);

		// focus();
		setClosable(false);
		setWidth(Page.getCurrent().getBrowserWindowWidth() / 2, Unit.PIXELS);
		setHeight(Page.getCurrent().getBrowserWindowHeight() / 1.5f, Unit.PIXELS);

	}

	private int getRandom(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}

	private String getRandomToken(int length, String ip) {
		StringBuffer buffer = new StringBuffer();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		while (length-- != 0) {
			int ch = (int) (getRandom(0, chars.length() - 1));
			buffer.append(chars.charAt(ch));
		}
		buffer.append(".");
		buffer.append(Utils.simpleEnc(ip));

		return buffer.toString();
	}

	private void setCookie(String token) {
		Cookie cookie = new Cookie(AttNames.REMEMBERME.getAtt(), token);
		cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
		cookie.setMaxAge(60 * 60 * 24 * 30); // valid for 30 days

		VaadinService.getCurrentResponse().addCookie(cookie);
	}

}
