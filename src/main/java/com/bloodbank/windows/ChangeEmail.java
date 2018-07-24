package com.bloodbank.windows;

import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.AttNames;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.validator.EmailValidator;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ChangeEmail extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Binder<ChangeEmail> binder = null;
	private VerticalLayout root = null;
	private HorizontalLayout titleLayout = null;
	private Button title = null;
	private HorizontalLayout currEmailLayout = null;
	private TextField currEmail = null;
	private HorizontalLayout newEmailLayout = null;
	private TextField newEmail = null;
	private HorizontalLayout newEmailCheckLayout = null;
	private TextField newEmailCheck = null;
	private HorizontalLayout buttonsLayout = null;
	private Button changeEmail = null;
	private Button cancel = null;

	public ChangeEmail(BloodBankDatabase db) {
		binder = new Binder<>(ChangeEmail.class);
		root = new VerticalLayout();
		titleLayout = new HorizontalLayout();
		title = new Button("E-Mail Change", VaadinIcons.MAILBOX);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.MENU_ROOT, "clearDisabled");
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		root.addComponent(titleLayout);
		root.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		currEmailLayout = new HorizontalLayout();
		currEmail = new TextField("Current E-Mail",
				db.getEmail(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString()));
		currEmail.setEnabled(false);
		currEmail.setSizeFull();
		currEmailLayout.addComponent(currEmail);
		currEmailLayout.setComponentAlignment(currEmail, Alignment.MIDDLE_CENTER);
		root.addComponent(currEmailLayout);
		root.setComponentAlignment(currEmailLayout, Alignment.MIDDLE_CENTER);

		newEmailLayout = new HorizontalLayout();
		newEmail = new TextField("Type your new E-mail");
		newEmail.setPlaceholder("New E-Mail");
		newEmail.setSizeFull();
		newEmailLayout.addComponent(newEmail);
		newEmailLayout.setComponentAlignment(newEmail, Alignment.MIDDLE_CENTER);
		root.addComponent(newEmailLayout);
		root.setComponentAlignment(newEmailLayout, Alignment.MIDDLE_CENTER);

		newEmailCheckLayout = new HorizontalLayout();
		newEmailCheck = new TextField("Re-type your E-mail");
		newEmailCheck.setPlaceholder("Re-type your New E-mail");
		newEmailCheck.setSizeFull();
		newEmailCheckLayout.addComponent(newEmailCheck);
		newEmailCheckLayout.setComponentAlignment(newEmailCheck, Alignment.MIDDLE_CENTER);
		root.addComponent(newEmailCheckLayout);
		root.setComponentAlignment(newEmailCheckLayout, Alignment.MIDDLE_CENTER);

		/*
		 * HorizontalLayout passwordlayout = new HorizontalLayout(); PasswordField
		 * password = new PasswordField(); password.setPlaceholder("******");
		 * passwordlayout.addComponent(password);
		 * passwordlayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		 * root.addComponent(passwordlayout); root.setComponentAlignment(passwordlayout,
		 * Alignment.MIDDLE_CENTER);
		 */

		buttonsLayout = new HorizontalLayout();
		changeEmail = new Button("Change E-Mail", VaadinIcons.MAILBOX);
		changeEmail.setEnabled(false);
		changeEmail.addClickListener(event -> {
			int re = db.updateEmail(newEmail.getValue().trim(),
					VaadinSession.getCurrent().getAttribute("userid").toString());
			Notification notif = new Notification("E-Mail update success !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			if (re > 0) {
				notif.show(Page.getCurrent());
				this.close();
			} else {
				notif.setCaption("E-Mail update failed!");
				notif.show(Page.getCurrent());
				this.close();
			}
		});
		cancel = new Button("Cancel", VaadinIcons.EXIT);
		cancel.addClickListener(event -> {
			this.close();
		});
		buttonsLayout.addComponent(changeEmail);
		buttonsLayout.setComponentAlignment(changeEmail, Alignment.BOTTOM_RIGHT);
		buttonsLayout.addComponent(cancel);
		buttonsLayout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);
		root.addComponent(buttonsLayout);
		root.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);

		binder.forField(newEmail).withValidator(new EmailValidator("E-Mail is not valid"))
				.bind(new ValueProvider<ChangeEmail, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(ChangeEmail source) {
						return null;
					}
				}, new Setter<ChangeEmail, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(ChangeEmail bean, String fieldvalue) {

					}
				});
		binder.forField(newEmailCheck).withValidator(new EmailValidator("E-Mail is not valid"))
				.withValidator(email -> newEmail.getValue().equals(newEmailCheck.getValue()), "Emails don't match !")
				.bind(new ValueProvider<ChangeEmail, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(ChangeEmail source) {
						return null;
					}
				}, new Setter<ChangeEmail, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(ChangeEmail bean, String fieldvalue) {

					}
				});
		/*
		 * binder.forField(password) .withValidator(str ->
		 * str.length()>5,"Password Must be at least 6 characters !") .bind(new
		 * ValueProvider<ChangeEmail, String>() {
		 * 
		 *//**
			* 
			*/
		/*
		 * private static final long serialVersionUID = 1L;
		 * 
		 * @Override public String apply(ChangeEmail source) { return null; } },new
		 * Setter<ChangeEmail, String>() {
		 * 
		 *//**
			* 
			*//*
				 * private static final long serialVersionUID = 1L;
				 * 
				 * @Override public void accept(ChangeEmail bean, String fieldvalue) {
				 * 
				 * } });
				 */

		binder.addStatusChangeListener(event -> {
			if (binder.isValid())
				changeEmail.setEnabled(true);
			else
				changeEmail.setEnabled(false);
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
