package com.bloodbank.windows;

import com.bloodbank.database.BloodBankDatabase;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ChangePassword extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Binder<ChangePassword> binder = null;
	private VerticalLayout root = null;
	private HorizontalLayout titleLayout = null;
	private Button title = null;
	private HorizontalLayout newPassLayout = null;
	private PasswordField newPassword = null;
	private HorizontalLayout newPassCheckLayout = null;
	private PasswordField newPasswordCheck = null;
	private HorizontalLayout currPassLayout = null;
	private PasswordField currPassword = null;
	private HorizontalLayout buttonsLayout = null;
	private Button changePass = null;
	private Button cancel = null;

	public ChangePassword(BloodBankDatabase db) {

		binder = new Binder<>(ChangePassword.class);
		root = new VerticalLayout();
		titleLayout = new HorizontalLayout();
		title = new Button("Password Change", VaadinIcons.LOCK);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.MENU_ROOT, "clearDisabled");
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		root.addComponent(titleLayout);
		root.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		newPassLayout = new HorizontalLayout();
		newPassword = new PasswordField("Type your New Password");
		newPassword.setPlaceholder("******");
		newPassLayout.addComponent(newPassword);
		newPassLayout.setComponentAlignment(newPassword, Alignment.MIDDLE_CENTER);
		root.addComponent(newPassLayout);
		root.setComponentAlignment(newPassLayout, Alignment.MIDDLE_CENTER);

		newPassCheckLayout = new HorizontalLayout();
		newPasswordCheck = new PasswordField("Re-type your new password");
		newPasswordCheck.setPlaceholder("******");
		newPassCheckLayout.addComponent(newPasswordCheck);
		newPassCheckLayout.setComponentAlignment(newPasswordCheck, Alignment.MIDDLE_CENTER);
		root.addComponent(newPassCheckLayout);
		root.setComponentAlignment(newPassCheckLayout, Alignment.MIDDLE_CENTER);

		currPassLayout = new HorizontalLayout();
		currPassword = new PasswordField("Type your current password");
		currPassword.setPlaceholder("******");
		currPassLayout.addComponent(currPassword);
		currPassLayout.setComponentAlignment(currPassword, Alignment.MIDDLE_CENTER);
		root.addComponent(currPassLayout);
		root.setComponentAlignment(currPassLayout, Alignment.MIDDLE_CENTER);

		buttonsLayout = new HorizontalLayout();
		changePass = new Button("Change Password", VaadinIcons.LOCK);
		changePass.setEnabled(false);
		changePass.addClickListener(event -> {
			int result = db.updatePassword(newPassword.getValue(), currPassword.getValue(),
					VaadinSession.getCurrent().getAttribute("userid").toString());
			Notification notif = new Notification("Password update success !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			if (result > 0) {
				notif.show(Page.getCurrent());
				this.close();
			} else {
				notif.setCaption("Password update failed!");
				notif.show(Page.getCurrent());
				this.close();
			}

		});
		cancel = new Button("Cancel", VaadinIcons.EXIT);
		cancel.addClickListener(event -> {
			this.close();
		});
		buttonsLayout.addComponents(changePass, cancel);
		buttonsLayout.setComponentAlignment(changePass, Alignment.BOTTOM_RIGHT);
		buttonsLayout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);
		root.addComponent(buttonsLayout);
		root.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);

		binder.forField(newPassword).withValidator(str -> str.length() > 5, "Password Must be at least 6 characters !")
				.bind(new ValueProvider<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(ChangePassword source) {
						return null;
					}
				}, new Setter<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(ChangePassword bean, String fieldvalue) {

					}
				});
		binder.forField(newPasswordCheck)
				.withValidator(str -> str.length() > 5, "Password Must be at least 6 characters !")
				.withValidator(pass -> newPassword.getValue().equals(newPasswordCheck.getValue()),
						"Passwords don't match !")
				.bind(new ValueProvider<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(ChangePassword source) {
						return null;
					}
				}, new Setter<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(ChangePassword bean, String fieldvalue) {

					}
				});

		binder.forField(currPassword).withValidator(str -> str.length() > 5, "Password Must be at least 6 characters !")
				.bind(new ValueProvider<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(ChangePassword source) {
						return null;
					}
				}, new Setter<ChangePassword, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(ChangePassword bean, String fieldvalue) {

					}
				});
		binder.addStatusChangeListener(event -> {
			if (binder.isValid())
				changePass.setEnabled(true);
			else
				changePass.setEnabled(false);
		});

		setContent(root);
		setWindowMode(WindowMode.NORMAL);
		setResizable(false);

		setModal(true);
		setClosable(false);
		setWidth(Page.getCurrent().getBrowserWindowWidth() / 2, Unit.PIXELS);
		setHeight(Page.getCurrent().getBrowserWindowHeight() / 1.5f, Unit.PIXELS);

	}
}
