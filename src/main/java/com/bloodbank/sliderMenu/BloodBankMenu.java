package com.bloodbank.sliderMenu;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.utils.AttNames;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class BloodBankMenu extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CssLayout menu = null;
	private Button cp = null;
	private Button myinfo = null;
	private Button admin = null;
	private Button logout = null;
	private Label title = null;
	private HorizontalLayout languagelayout = null;
	private Button romanian = null;
	private Button english = null;
	private Button home = null;

	public BloodBankMenu(MyUI ui, ResourceBundle bundle) {

		title = new Label("Menu");
		title.addStyleName(ValoTheme.MENU_TITLE);

		/*
		 * Image userIcon = new Image(null,new ThemeResource("user2.png"));
		 * userIcon.setEnabled(false); userIcon.setWidth("30%");
		 * userIcon.addStyleNames(ValoTheme.BUTTON_BORDERLESS,
		 * ValoTheme.MENU_TITLE,ValoTheme.ACCORDION_BORDERLESS,"userIcon");
		 */

		/*
		 * Button userIcon = new Button("",new ThemeResource("user2.png")); ///Button
		 * userIcon = new Button("",VaadinIcons.USER); userIcon.setEnabled(false);
		 * userIcon.addStyleNames(ValoTheme.BUTTON_BORDERLESS,
		 * ValoTheme.MENU_TITLE,ValoTheme.ACCORDION_BORDERLESS,"userIcon",
		 * "clearDisabled");
		 */
		languagelayout = new HorizontalLayout();
		romanian = new Button(new ThemeResource("Flag_of_Romania.png"));
		romanian.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_TITLE, ValoTheme.ACCORDION_BORDERLESS,
				"userIcon");
		
		english = new Button(new ThemeResource("Flag_of_the_United_Kingdom.png"));
		english.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_TITLE, ValoTheme.ACCORDION_BORDERLESS,
				"userIcon");
		
		languagelayout.addComponents(romanian, english);
		languagelayout.setSpacing(false);
		languagelayout.setMargin(false);
		languagelayout.setWidth("100%");
		if (UI.getCurrent().getSession().getLocale().equals(Locale.US) || UI.getCurrent().getSession().getLocale().equals(Locale.ENGLISH)
				|| UI.getCurrent().getSession().getLocale().equals(Locale.UK)) {
			romanian.addStyleName("inacctiveButton");
			
		}else
	/*	if (!UI.getCurrent().getSession().getLocale().equals(Locale.US) || !UI.getCurrent().getSession().getLocale().equals(Locale.ENGLISH)
				|| !UI.getCurrent().getSession().getLocale().equals(Locale.UK))*/
			english.addStyleName("inacctiveButton");

		romanian.addClickListener(event -> {
			if (!english.getStyleName().contains("inacctiveButton"))
				english.addStyleName("inacctiveButton");

			if (romanian.getStyleName().contains("inacctiveButton")) {

				Locale l = new Locale.Builder().setLanguage("ro").build();
				/*
				 * ResourceBundle b = ResourceBundle.getBundle("local_RO",l); ui.setBundle(b);
				 */
				UI.getCurrent().getSession().setLocale(l);
				romanian.removeStyleName("inacctiveButton");

				Page.getCurrent().reload();// try with push

			}
		});
		english.addClickListener(event -> {
			if (!romanian.getStyleName().contains("inacctiveButton"))
				romanian.addStyleName("inacctiveButton");
			if (english.getStyleName().contains("inacctiveButton")) {
				// Locale l = new Locale.Builder().setLanguage("en").build();
				/*
				 * ResourceBundle b = ResourceBundle.getBundle("local_EN",l); ui.setBundle(b);
				 */
				UI.getCurrent().getSession().setLocale(Locale.US);
				english.removeStyleName("inacctiveButton");

				Page.getCurrent().reload();
			}
		});

		home = new Button("Home", e -> ui.getNavigator().navigateTo("Home"));
		home.setIcon(VaadinIcons.HOME);
		home.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM, ValoTheme.ACCORDION_BORDERLESS);

		menu = new CssLayout();
		menu.addComponent(title);
		menu.addComponent(languagelayout);
		menu.addComponent(home);

		// menu = new CssLayout(title, languagelayout, home, cp, myinfo,logout);

		menu.addStyleName(ValoTheme.MENU_ROOT);

		setCompositionRoot(menu);
		setSizeFull();

	}

	public Optional<Cookie> getCookie(String cookieName) {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		return Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName)).findFirst();
	}

	public void setLogged() {

		cp = new Button("CP", e -> UI.getCurrent().getNavigator().navigateTo("CP"));
		cp.setIcon(VaadinIcons.SAFE);
		cp.setVisible(false);
		cp.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM, ValoTheme.ACCORDION_BORDERLESS);
		if (VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()) != null
				&& Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()).toString()) > 0) {
			admin = new Button("Admin", e -> UI.getCurrent().getNavigator().navigateTo("Admin"));
			admin.setIcon(VaadinIcons.INFO_CIRCLE);
			admin.setVisible(false);
			admin.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM, ValoTheme.ACCORDION_BORDERLESS);
		} else {
			myinfo = new Button("My Info", e -> UI.getCurrent().getNavigator().navigateTo("myInfo"));
			myinfo.setIcon(VaadinIcons.INFO_CIRCLE);
			myinfo.setVisible(false);
			myinfo.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM, ValoTheme.ACCORDION_BORDERLESS);
		}
		logout = new Button("Logout", VaadinIcons.SIGN_OUT);
		logout.setVisible(false);
		logout.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
		logout.addClickListener(event -> {
			if (getCookie(AttNames.REMEMBERME.getAtt()).isPresent()) {
				Cookie cookie = new Cookie(AttNames.REMEMBERME.getAtt(), "");
				cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
				cookie.setMaxAge(0);
				VaadinService.getCurrentResponse().addCookie(cookie);
				
				
			} if (VaadinSession.getCurrent().getAttribute(AttNames.LOGGED.getAtt()) != null) {

				VaadinSession.getCurrent().close();
				VaadinService.getCurrentRequest().getWrappedSession().invalidate();
				Page.getCurrent().reload();// check with push
			}
		});

		menu.addComponent(cp);
		if (VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()) != null
				&& Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()).toString()) > 0)
			menu.addComponent(admin);
		else
			menu.addComponent(myinfo);
		menu.addComponent(logout);

		cp.setVisible(true);
		if (VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()) != null &&
				Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.RANK.getAtt()).toString()) > 0) {
			admin.setVisible(true);

		} else
			myinfo.setVisible(true);

		logout.setVisible(true);
	}

}