package com.bloodbank.front;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.AttNames;
import com.bloodbank.utils.GLobalCache;
import com.bloodbank.utils.Utils;
import com.bloodbank.windows.ChangeEmail;
import com.bloodbank.windows.ChangePassword;
import com.bloodbank.windows.ChangeProfileIcon;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class CP extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout page = null;
	private Image icon = null;
	private HorizontalLayout iconLayout = null;
	private Label def = null;
	private HorizontalLayout title = null;
	private HorizontalLayout changeIconLayout = null;
	private Button changeIcon = null;
	private HorizontalLayout changeEmailLayout = null;
	private Button changePassword = null;

	public CP(MyUI ui, BloodBankDatabase db) {
		page = new VerticalLayout();
		title = new HorizontalLayout();
		def = new Label("Control Panel");
		def.setEnabled(false);

		title.addComponent(def);
		title.setComponentAlignment(def, Alignment.TOP_CENTER);
		title.setWidth("30%");
		page.addComponent(title);
		page.setComponentAlignment(title, Alignment.TOP_CENTER);

		//
		iconLayout = new HorizontalLayout();
		// Utils.getUserImage(Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt())))
		String uid = Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()));
		
		FileResource defaultIcon = GLobalCache.getUserIcon(uid);
		
		
		icon = new Image(null, defaultIcon);
		icon.markAsDirty();
		icon.setWidth(Page.getCurrent().getBrowserWindowWidth() / 10, Unit.PIXELS);// test
		icon.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
		

		iconLayout.addComponents(icon);
		iconLayout.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
		iconLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() / 8, Unit.PIXELS);// test

		page.addComponent(iconLayout);
		page.setComponentAlignment(iconLayout, Alignment.MIDDLE_CENTER);

		changeIconLayout = new HorizontalLayout();
		changeIcon = new Button("Change Icon", VaadinIcons.USER);

		changeIcon.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		changeIcon.setSizeFull();
		changeIcon.addClickListener(event -> {
			Window winIcon = new ChangeProfileIcon(db, this, ui);
			winIcon.setModal(true);
			winIcon.center();
			UI.getCurrent().addWindow(winIcon);
		});
		changeIconLayout.addComponents(changeIcon);
		changeIconLayout.setComponentAlignment(changeIcon, Alignment.MIDDLE_CENTER);
		changeIconLayout.setWidth("30%");

		page.addComponent(changeIconLayout);
		page.setComponentAlignment(changeIconLayout, Alignment.MIDDLE_CENTER);

		//

		changeEmailLayout = new HorizontalLayout();
		Button changeEmail = new Button("Change Email", VaadinIcons.MAILBOX);
		changeEmail.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		changeEmail.setEnabled(Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.ISQUPDATED.getAtt()).toString())>0);
		changeEmail.setSizeFull();
		changeEmail.addClickListener(event -> {
			Window winE = new ChangeEmail(db);
			winE.setModal(true);
			winE.center();
			UI.getCurrent().addWindow(winE);
		});
		changeEmailLayout.addComponent(changeEmail);
		changeEmailLayout.setComponentAlignment(changeEmail, Alignment.MIDDLE_CENTER);
		changeEmailLayout.setWidth("30%");
		page.addComponent(changeEmailLayout);
		page.setComponentAlignment(changeEmailLayout, Alignment.MIDDLE_CENTER);

		//

		HorizontalLayout changePasswordLayout = new HorizontalLayout();
		changePassword = new Button("Change Password", VaadinIcons.LOCK);
		changePassword.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		changePassword.setSizeFull();
		changePassword.addClickListener(event -> {
			Window winP = new ChangePassword(db);
			winP.setModal(true);
			winP.center();
			UI.getCurrent().addWindow(winP);
		});
		changePasswordLayout.addComponent(changePassword);
		changePasswordLayout.setComponentAlignment(changePassword, Alignment.MIDDLE_CENTER);
		changePasswordLayout.setWidth("30%");
		page.addComponent(changePasswordLayout);
		page.setComponentAlignment(changePasswordLayout, Alignment.MIDDLE_CENTER);

		page.setMargin(false);

		page.addStyleName(ValoTheme.PANEL_WELL);

		setCompositionRoot(page);

	}

	public void setIconImg() {
		int index = page.getComponentIndex(iconLayout);

		page.removeComponent(iconLayout);
		// HorizontalLayout iconLayout = new HorizontalLayout();
		iconLayout = new HorizontalLayout();
		icon = new Image(null, Utils.getUserImage(Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()))));

		icon.setWidth(Page.getCurrent().getBrowserWindowWidth() / 10, Unit.PIXELS);// test
		icon.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
		icon.markAsDirty();

		iconLayout.addComponents(icon);
		iconLayout.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
		iconLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() / 8, Unit.PIXELS);// test
		page.addComponent(iconLayout, index);
		page.setComponentAlignment(iconLayout, Alignment.MIDDLE_CENTER);
	}

}
