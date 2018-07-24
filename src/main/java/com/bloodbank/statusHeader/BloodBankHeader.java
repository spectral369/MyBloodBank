package com.bloodbank.statusHeader;

import java.util.ResourceBundle;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.AttNames;
import com.bloodbank.utils.GLobalCache;
import com.bloodbank.utils.Utils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class BloodBankHeader extends CustomComponent implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Button circleStatus = null;
	private HorizontalLayout root = null;
	private Button loginH = null;
	private Button signupH = null;
	private HorizontalLayout header = null;
	private HorizontalLayout user = null;
	public Image userH = null;
	private Button menu = null;
	private HorizontalLayout server = null;
	private Button conn = null;
	private HorizontalLayout ti = null;
	private Label title = null;
	private Button username = null;

	public BloodBankHeader(MyUI ui, VerticalLayout menuContainer, ResourceBundle bundle, BloodBankDatabase db) {

		root = new HorizontalLayout();
		header = new HorizontalLayout();
		user = new HorizontalLayout();
		menu = new Button(VaadinIcons.MENU);
		menu.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
		menu.setSizeUndefined();

		menu.addClickListener(event -> {

			menuContainer.setVisible(!menuContainer.isVisible());

		});
		header.addComponent(menu);
		header.setComponentAlignment(menu, Alignment.MIDDLE_LEFT);
		String uid = Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()));
		System.out.println("thing "+ uid);
		FileResource defaultIcon = GLobalCache.getUserIcon(uid);
		userH = new Image(null,defaultIcon);
	//	userH = new Image(null, Utils.getUserImage(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt())));
		userH.markAsDirty();
		userH.setHeight(Page.getCurrent().getBrowserWindowHeight() / 12, Unit.PIXELS);
		userH.setEnabled(false);
		userH.addStyleNames("bloodSum", ValoTheme.BUTTON_BORDERLESS, "clearDisabled");

		loginH = new Button("Login", VaadinIcons.SIGN_IN);
		loginH.addClickListener(event -> {
			if (event.getSource().equals(loginH)) {
				Window win = new LoginWindow(ui, db);
				win.setModal(true);
				win.center();
				ui.addWindow(win);

			}

		});

		loginH.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED);
		// Button signupH = new Button("Sign Up",VaadinIcons.SIGN_IN_ALT);
		signupH = new Button(bundle.getString("signup"), VaadinIcons.SIGN_IN_ALT);
		signupH.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED);
		signupH.addClickListener(event -> {
			if (event.getSource().equals(signupH)) {
				Window win2 = new SignUp(ui, db);
				win2.setModal(true);
				win2.center();
				ui.addWindow(win2);
			}
		});

		user.addComponents(userH, loginH, signupH);
		user.setComponentAlignment(userH, Alignment.MIDDLE_CENTER);
		user.setComponentAlignment(loginH, Alignment.MIDDLE_CENTER);
		user.setComponentAlignment(signupH, Alignment.MIDDLE_CENTER);
		user.setSizeUndefined();
		header.addComponents(user);
		header.setComponentAlignment(user, Alignment.MIDDLE_LEFT);

		server = new HorizontalLayout();

		conn = new Button("Connection Status: ");
		conn.setEnabled(false);
		conn.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		circleStatus = new Button(VaadinIcons.CIRCLE);
		circleStatus.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");

		circleStatus.setEnabled(false);

		/*
		 * Button timeLabel = new Button("Connection Time: ");
		 * timeLabel.setEnabled(false);
		 * timeLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		 * 
		 * Label time = new Label("N/A"); time.setSizeUndefined();
		 */

		server.addComponents(conn, circleStatus/* ,timeLabel,time */);
		// server.setComponentAlignment(time, Alignment.MIDDLE_LEFT);
		server.setSpacing(false);
		server.setSizeUndefined();

		ti = new HorizontalLayout();
		title = new Label("My Blood Bank");
		title.setSizeUndefined();
		ti.addComponent(title);
		ti.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

		root.addComponents(header, ti, server);
		root.setWidth("100%");
		root.setExpandRatio(header, 2);
		root.setExpandRatio(ti, 1);
		root.setExpandRatio(server, 2);

		root.setComponentAlignment(header, Alignment.MIDDLE_LEFT);
		root.setComponentAlignment(ti, Alignment.MIDDLE_CENTER);
		root.setComponentAlignment(server, Alignment.MIDDLE_RIGHT);
		root.addStyleName(ValoTheme.MENU_ROOT);

		if (VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()) == null && db.isvalid()<1) {
			loginH.setEnabled(false);
			signupH.setEnabled(false);

		}

		setCompositionRoot(root);
		// super.beforeClientResponse(true);
		/*
		 * VaadinSession.getCurrent().lock(); markAsDirtyRecursive();
		 * VaadinSession.getCurrent().unlock();
		 */
		if (VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()) != null) {
			update();
		}
	}

	@SuppressWarnings("unused")
	private void toggleVisible(Component component) {
		component.setVisible(!component.isVisible());
	}

	public void update() {
		user.removeAllComponents();
		userH = new Image(null, Utils.getUserImage(Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()))));

		userH.setHeight(Page.getCurrent().getBrowserWindowHeight() / 12, Unit.PIXELS);
		userH.addStyleNames("bloodSum", ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		username = new Button("Welcome, " + VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()));
		username.setEnabled(false);
		username.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED, "clearDisabled");
		user.addComponents(userH, username);
		user.setComponentAlignment(userH, Alignment.MIDDLE_CENTER);
		user.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
		user.setSizeUndefined();

		header.addComponents(user);
		header.setComponentAlignment(user, Alignment.MIDDLE_LEFT);

	}

}
